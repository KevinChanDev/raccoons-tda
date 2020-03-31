package com.raccoons.tda.auth.service.token;

import com.raccoons.tda.api.model.UserPrincipal;
import com.raccoons.tda.auth.model.OAuth2AccessTokenResponse;
import com.raccoons.tda.auth.model.UserBoundToken;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.model.token.ExpiringAccessToken;
import com.raccoons.tda.auth.service.AuthClientService;
import com.raccoons.tda.auth.service.RefreshingService;
import com.raccoons.tda.auth.service.data.AccessTokenCacheService;
import com.raccoons.tda.auth.service.data.AccessTokenRedisService;
import com.raccoons.tda.auth.service.data.AccessTokenRepositoryService;
import com.raccoons.tda.auth.service.security.AccessTokenSecurityService;
import com.raccoons.tda.auth.service.security.EncryptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccessTokenService {

    private static final Logger logger = LogManager.getLogger(AccessTokenService.class);

    @Autowired
    private AccessTokenEventService accessTokenEventService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private AccessTokenCacheService accessTokenCacheService;

    @Autowired
    private AccessTokenRedisService accessTokenRedisService;

    @Autowired
    private AccessTokenRepositoryService accessTokenRepositoryService;

    @Autowired
    private AccessTokenSecurityService accessTokenSecurityService;

    @Autowired
    private AuthClientService authClientService;

    @Autowired
    private RefreshingService refreshingService;

    private Map<String, ExpiringAccessToken> accessTokens;

    @PostConstruct
    public void init() {
        accessTokens = new ConcurrentHashMap<>();
    }

    public Optional<AccessToken> getStoredAccessToken(final long requestId, final String owner) {
        final ExpiringAccessToken expiringAccessToken = accessTokens.get(owner);
        if (expiringAccessToken != null) {
            final long expirationTime = expiringAccessToken.getExpirationTime();
            final long currentTime = System.currentTimeMillis();

            if (currentTime < expirationTime) {
                return Optional.of(expiringAccessToken.getAccessToken());
            } else {
                accessTokens.remove(owner);
            }
        }
        return Optional.empty();
    }

    /**
     * This goal of this method is to provide a continuous chain of method calls to provide
     * for the retrieval of a valid token. The following steps will be taken to ensure that a
     * token is retrieved:
     * - Search in memory cache for access token
     * - Search external redis cache for access token
     * - Search database for access token
     * - If access token exists in the database, use the refresh token to generate a new token
     * - Otherwise return empty
     * - If refreshed, then persist the new token in the database, in the cache and in memory
     *
     * @param owner The owner of the access token to be retrieved
     * @return Returns an AccessToken if it can be determined otherwise empty
     */
    public CompletableFuture<Optional<AccessToken>> fetchAccessToken(final long requestId, final String owner) {
        logger.trace("[{}] Fetching accessToken for {}.", requestId, owner);

        final Optional<AccessToken> storedAccessToken = getStoredAccessToken(requestId, owner);
        if (storedAccessToken.isPresent()) {
            logger.trace("[{}] Access token was present in memory.", requestId);
            return CompletableFuture.completedFuture(storedAccessToken);
        } else {
            return fetchRedisAccessToken(owner).thenCompose(redisAccessToken -> {
                if (redisAccessToken.isPresent()) {
                    logger.trace("[{}] Access token was present in redis.", requestId);
                    return CompletableFuture.completedFuture(redisAccessToken);
                } else {
                    logger.trace("[{}] Access token was NOT present in redis.", requestId);
                    return fetchPersistedAccessToken(requestId, owner).thenCompose(persistedAccessToken -> {
                        if (persistedAccessToken.isPresent()) {
                            logger.trace("[{}] Persisted access token was found in the repository.", requestId);
                        } else {
                            logger.trace("[{}] Persisted access token was NOT found in the repository.", requestId);
                        }
                        return CompletableFuture.completedFuture(persistedAccessToken);
                    });
                }
            });
        }
    }

    public CompletableFuture<Boolean> storeUserBoundToken(final long requestId, final UserBoundToken userBoundToken) {
        final UserPrincipal userPrincipal = userBoundToken.getUserPrincipal();
        final OAuth2AccessTokenResponse response = userBoundToken.getOAuth2AccessTokenResponse();

        final String userName = userPrincipal.getUserId();
        final String accessToken = response.getAccessToken();
        final String refreshToken = response.getRefreshToken();

        final long tokenExpiresIn = response.getExpiresIn();
        final long refreshTokenExpiresIn = response.getRefreshTokenExpiresIn();
        final long currentSystemTime = System.currentTimeMillis();

        final long tokenExpireTime = currentSystemTime + tokenExpiresIn;
        final long refreshTokenExpireTime = currentSystemTime + refreshTokenExpiresIn;

        final AccessToken.Builder builder = AccessToken.newBuilder()
                .owner(userName)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiration(tokenExpireTime)
                .refreshTokenExpiration(refreshTokenExpireTime);

        logger.trace("[{}] Persisting UserBoundToken for {}.", requestId, userName);

        return persistAccessToken(requestId, builder.build()).thenApply(at -> true);
    }

    public CompletableFuture<Optional<AccessToken>> fetchRedisAccessToken(final String owner) {
        return accessTokenRedisService.getAccessToken(owner);
    }

    public CompletableFuture<Optional<AccessToken>> fetchPersistedAccessToken(final long requestId, final String owner) {
        return accessTokenRepositoryService.getAccessToken(requestId, owner);
    }

    public CompletableFuture<String> storeAccessTokenRedis(final AccessToken accessToken) {
        final String owner = accessToken.getOwner();
        final String accessTokenValue = accessToken.getAccessToken();
        return accessTokenRedisService.storeAccessToken(owner, accessTokenValue);
    }

    public CompletableFuture<Long> removeAccessTokenRedis(final String owner) {
        return accessTokenRedisService.deleteAccessToken(owner);
    }

    public CompletableFuture<Boolean> removeAccessTokenDatabase(final long requestId, final String owner) {
        return accessTokenRepositoryService.deleteAccessToken(requestId, owner);
    }

    public CompletableFuture<String> storeAccessTokenDatabase(final long requestId, final AccessToken accessToken) {
        return accessTokenRepositoryService.storeAccessToken(requestId, accessToken);
    }

    public CompletableFuture<AccessToken> persistAccessToken(final long requestId, final AccessToken accessToken) {
        final CompletableFuture<String> redis = storeAccessTokenRedis(accessToken);
        final CompletableFuture<String> repository = storeAccessTokenDatabase(requestId, accessToken);
        return CompletableFuture.allOf(redis, repository).thenApply(a -> accessToken);
    }

    public CompletableFuture<Boolean> removeAccessToken(final long requestId, final String owner) {
        //final CompletableFuture<String> redis = storeAccessTokenRedis(accessToken);

        return null;
    }

    public CompletableFuture<Optional<AccessToken>> refreshAccessTokenByOwner(final long requestId, final String owner) {
        return accessTokenRepositoryService.getAccessToken(requestId, owner).thenCompose(repositoryAccessToken -> {
            if (repositoryAccessToken.isPresent()) {
                logger.trace("[{}] Access token for owner {} was found in repository.", requestId, owner);
                return refreshAccessToken(requestId, repositoryAccessToken.get()).thenCompose(a -> {
                    if (a.isPresent()) {
                        logger.trace("[{}] Persisting refreshed accessed token.", requestId);

                        return persistAccessToken(requestId, a.get()).thenApply(accessToken -> {
                            if (accessToken != null) {
                                accessTokenEventService.onAccessTokenUpdated(accessToken);
                            }
                            return Optional.ofNullable(accessToken);
                        });
                    } else {
                        logger.trace("[{}] Persisting refreshed accessed token.", requestId);
                        return CompletableFuture.completedFuture(Optional.empty());
                    }
                });
            } else {
                logger.trace("[{}] Access token for owner {} was NOT found in repository.", requestId, owner);
                return CompletableFuture.completedFuture(Optional.empty());
            }
        });
    }

//    public CompletableFuture<Optional<AccessToken>> refreshAndPersistToken(final AccessToken accessToken) {
//        return refreshAccessToken(accessToken).thenCompose(refreshed -> {
//            if (refreshed.isPresent()) {
//                return persistAccessToken(refreshed.get()).thenApply(o -> Optional.of(accessToken));
//            } else {
//                return CompletableFuture.completedFuture(Optional.empty());
//            }
//        });
//    }

    public CompletableFuture<Optional<AccessToken>> refreshAccessToken(final long requestId, final AccessToken accessToken) {
        final String owner = accessToken.getOwner();
        final String refreshToken = accessToken.getRefreshToken();

        if (owner != null && refreshToken != null) {
            return authClientService.refreshAccessToken(accessToken).thenApply(response -> {
                if (response.isValid()) {
                    refreshingService.updateRefreshTime(owner);

                    logger.trace("[{}] Building new AccessToken from valid refresh response.", requestId);

                    final String newAccessToken = response.getAccessToken();
                    final long newExpiresIn = response.getExpiresIn();
                    final long accessTokenExpiration = System.currentTimeMillis() + newExpiresIn;

                    final AccessToken.Builder builder = AccessToken.newBuilder()
                            .owner(accessToken.getOwner())
                            .accessToken(newAccessToken)
                            .refreshToken(accessToken.getRefreshToken())
                            .accessTokenExpiration(accessTokenExpiration)
                            .refreshTokenExpiration(accessToken.getRefreshTokenExpiration());

                    final AccessToken refreshedAccessToken = builder.build();
                    return Optional.of(refreshedAccessToken);
                } else {
                    logger.trace("[{}] Invalid refresh response was returned by the request.", requestId);
                    return Optional.empty();
                }
            });
        } else {
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }

}

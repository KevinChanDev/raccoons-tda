package com.raccoons.tda.auth.service;

import com.raccoons.tda.api.model.UserPrincipal;
import com.raccoons.tda.auth.model.OAuth2AccessTokenResponse;
import com.raccoons.tda.auth.model.UserBoundToken;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.model.token.ExpiringAccessToken;
import com.raccoons.tda.auth.service.data.AccessTokenCacheService;
import com.raccoons.tda.auth.service.data.AccessTokenRedisService;
import com.raccoons.tda.auth.service.data.AccessTokenRepositoryService;
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

    private static final Logger logger = LogManager.getLogger(HttpService.class);

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

    private Map<String, ExpiringAccessToken> accessTokens;

    @PostConstruct
    public void init() {
        accessTokens = new ConcurrentHashMap<>();
    }

    public Optional<AccessToken> getStoredAccessToken(final String owner) {
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
    public CompletableFuture<Optional<AccessToken>> fetchAccessToken(final String owner) {
        final Optional<AccessToken> storedAccessToken = getStoredAccessToken(owner);
        if (storedAccessToken.isPresent()) {
            return CompletableFuture.completedFuture(storedAccessToken);
        } else {
            return fetchRedisAccessToken(owner).thenCompose(redisAccessToken -> {
                if (redisAccessToken.isPresent()) {
                    return CompletableFuture.completedFuture(redisAccessToken);
                } else {
                    return fetchPersistedAccessToken(owner).thenCompose(persistedAccessToken -> {
                        System.out.println();
                        return null;
                    });
                }
            });
        }
    }

    public CompletableFuture<Boolean> storeUserBoundToken(final UserBoundToken userBoundToken) {
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

        final AccessToken a = new AccessToken();

        a.setOwner(userName);
        a.setAccessToken(accessToken);
        a.setRefreshToken(refreshToken);
        a.setAccessTokenExpiration(tokenExpireTime);
        a.setRefreshTokenExpiration(refreshTokenExpireTime);

        logger.info("Fake storing user bound token!!");

        return CompletableFuture.completedFuture(true);
    }

    public CompletableFuture<Void> storeAccessToken(final AccessToken accessToken) {
        return null;
    }

    public CompletableFuture<Optional<AccessToken>> fetchRedisAccessToken(final String owner) {
        return null;
    }

    public CompletableFuture<Optional<AccessToken>> fetchRedisToken(final String owner) {
        return null;
    }

    public CompletableFuture<Optional<AccessToken>> fetchPersistedAccessToken(final String owner) {
        return null;
    }

    public CompletableFuture<Optional<AccessToken>> refreshAndPersistToken(final AccessToken accessToken) {
        return null;
    }

    public Optional<AccessToken> storeAccessToken() {
        return Optional.empty();
    }

    public CompletableFuture<Optional<AccessToken>> storeAccessTokenRedis(final AccessToken accessToken) {
        return null;
    }

    public CompletableFuture<Optional<AccessToken>> storeAccessTokenDatabase(final AccessToken accessToken) {
        return null;
    }

    public CompletableFuture<Optional<AccessToken>> persistAccessToken(final AccessToken accessToken) {
        // Persist in Memory
        // Persist in Redis
        // Persist in Database

        final String owner = accessToken.getOwner();
        final String accessTokenValue = accessToken.getAccessToken();

        accessTokenCacheService.storeAccessToken(accessToken);

        final CompletableFuture<String> redis = accessTokenRedisService.storeAccessToken(owner, accessTokenValue);
        final CompletableFuture<String> repository = accessTokenRepositoryService.storeAccessToken(accessToken);

        //final CompletableFuture<Optional<AccessToken>> done = CompletableFuture.anyOf(memory, cache, database);

        return null;
    }

    public CompletableFuture<Optional<AccessToken>> refreshAccessToken(final AccessToken accessToken) {
        return null;
    }

}

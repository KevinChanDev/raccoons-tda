package com.raccoons.tda.auth.service.data;

import com.raccoons.tda.auth.configuration.properties.RedisProperties;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.service.security.EncryptionService;
import com.raccoons.tda.auth.util.Tokens;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AccessTokenRedisService {

    private static final Logger logger = LogManager.getLogger(AccessTokenRedisService.class);

    private static final String ACCESS_TOKEN_KEY_PREFIX = "ACCESS.TOKEN.";

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private RedisService redisService;

    @Autowired
    private EncryptionService encryptionService;

    public CompletableFuture<Optional<AccessToken>> getAccessToken(final String owner) {
        if (owner == null) {
            return CompletableFuture.completedFuture(Optional.empty());
        }
        logger.info("Fetching access token from Redis.");
        final String accessTokenKey = getAccessTokenKey(owner);
        final String encryptedKey = encryptionService.encryptValue(accessTokenKey);
        return redisService.getValue(encryptedKey).thenApply(s -> {
            if (s != null) {
                final String wrappedAccessToken = encryptionService.decryptValue(s);

                if (Tokens.isValidToken(wrappedAccessToken)) {
                    return Optional.of(AccessToken.newBuilder().owner(owner)
                            .accessToken(Tokens.extractToken(wrappedAccessToken)).build());
                }
            }
            return Optional.empty();
        });
    }

    public CompletableFuture<String> storeAccessToken(final String owner, final String accessToken) {
        if (owner == null || accessToken == null) {
            return CompletableFuture.completedFuture(null);
        }

        logger.info("Storing access token in Redis.");
        final String accessTokenKey = getAccessTokenKey(owner);
        final String encryptedKey = encryptionService.encryptValue(accessTokenKey);
        final String wrappedAccessToken = Tokens.wrapToken(accessToken);
        final String encryptedAccessToken = encryptionService.encryptValue(wrappedAccessToken);
        return redisService.setValue(encryptedKey, encryptedAccessToken, redisProperties.getTokenExpiration());
    }

    public CompletableFuture<Long> deleteAccessToken(final String owner) {
        if (owner == null) {
            return CompletableFuture.completedFuture(0L);
        }

        logger.info("Deleting access token from Redis.");
        final String accessTokenKey = getAccessTokenKey(owner);
        final String encryptedKey = encryptionService.encryptValue(accessTokenKey);
        return redisService.deleteValue(encryptedKey);
    }

    private static String getAccessTokenKey(final String owner) {
        return ACCESS_TOKEN_KEY_PREFIX + owner;
    }

}

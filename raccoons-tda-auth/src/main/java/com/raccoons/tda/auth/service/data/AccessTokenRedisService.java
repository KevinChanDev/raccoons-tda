package com.raccoons.tda.auth.service.data;

import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.service.RedisService;
import com.raccoons.tda.auth.service.EncryptionService;
import com.raccoons.tda.auth.util.AccessTokens;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AccessTokenRedisService {

    private static final Logger logger = LogManager.getLogger(AccessTokenRedisService.class);

    private static final String ACCESS_TOKEN_KEY_PREFIX = "ACCESS.TOKEN.";

    @Value("${tda.auth.redis.token.expiration}")
    private long tokenExpiration;

    @Autowired
    private RedisService redisService;

    @Autowired
    private EncryptionService encryptionService;

    public CompletableFuture<Optional<AccessToken>> getAccessToken(final String owner) {
        if (owner == null) {
            return CompletableFuture.completedFuture(Optional.empty());
        }

        logger.info("Getting access token from Redis.");
        final String accessTokenKey = getAccessTokenKey(owner);
        final String encryptedKey = encryptionService.encryptValue(accessTokenKey);
        return redisService.getValue(encryptedKey).thenApply(s -> {
            final String wrappedAccessToken = encryptionService.decryptValue(s);
            if (AccessTokens.isValidAccessToken(wrappedAccessToken)) {
                return Optional.of(new AccessToken(AccessTokens.extractToken(wrappedAccessToken)));
            } else {
                return Optional.empty();
            }
        });
    }

    public CompletableFuture<String> storeAccessToken(final String owner, final String accessToken) {
        if (owner == null || accessToken == null) {
            return CompletableFuture.completedFuture(null);
        }

        logger.info("Storing access token in Redis.");
        final String accessTokenKey = getAccessTokenKey(owner);
        final String encryptedKey = encryptionService.encryptValue(accessTokenKey);
        final String wrappedAccessToken = AccessTokens.wrapToken(accessToken);
        final String encryptedAccessToken = encryptionService.encryptValue(wrappedAccessToken);
        return redisService.setValue(encryptedKey, encryptedAccessToken, tokenExpiration);
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

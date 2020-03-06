package com.raccoons.tda.auth.service.data;

import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.model.token.ExpiringAccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AccessTokenCacheService {

    private static final Logger logger = LogManager.getLogger(AccessTokenCacheService.class);

    @Value("${tda.auth.token.expiration}")
    private long tokenExpiration;

    private Map<String, ExpiringAccessToken> accessTokens;

    @PostConstruct
    public void init() {
        accessTokens = new ConcurrentHashMap<>();
    }

    public void clearExpiredAccessTokens() {
        logger.trace("Running access token cache expiration sweep.");
        final Iterator<Map.Entry<String, ExpiringAccessToken>> entryIterator = accessTokens.entrySet().iterator();

        if (entryIterator.hasNext()) {
            final long currentTime = System.currentTimeMillis();
            final AtomicInteger entryCount = new AtomicInteger();

            while (entryIterator.hasNext()) {
                final Map.Entry<String, ExpiringAccessToken> entry = entryIterator.next();
                final long expirationTime = entry.getValue().getExpirationTime();
                if (currentTime > expirationTime) {
                    entryIterator.remove();
                    entryCount.incrementAndGet();
                }
            }
            if (entryCount.get() > 0) {
                logger.trace("Removed {} expired access tokens from cache.", entryCount.get());
            }
        }
    }

    public Optional<AccessToken> getStoredAccessToken(final String owner) {
        logger.trace("Retrieving stored access token for {} from cache.", owner);

        if (owner == null) {
            return Optional.empty();
        }

        final ExpiringAccessToken expiringAccessToken = accessTokens.get(owner);

        if (expiringAccessToken != null) {
            final long expirationTime = expiringAccessToken.getExpirationTime();
            final long currentTime = System.currentTimeMillis();

            if (currentTime < expirationTime) {
                logger.trace("Returning stored access token for {} from cache.", owner);
                return Optional.of(expiringAccessToken.getAccessToken());
            } else {
                logger.trace("Stored access token for {} has expired {} (Current Time - {}), removing from cache.",
                        owner, expirationTime, currentTime);
                accessTokens.remove(owner);
            }
        } else {
            logger.trace("Stored access token for {} could not be found in cache.", owner);
        }
        return Optional.empty();
    }

    public void storeAccessToken(final AccessToken accessToken) {
        final String owner = accessToken.getOwner();
        if (owner != null) {
            logger.info("Storing access token for {} in cache.", owner);
            final long tokenExpirationTime = System.currentTimeMillis() + tokenExpiration;
            final ExpiringAccessToken expiringAccessToken = new ExpiringAccessToken(tokenExpirationTime, accessToken);
            accessTokens.put(accessToken.getOwner(), expiringAccessToken);
        }

    }

}

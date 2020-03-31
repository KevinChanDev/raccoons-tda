package com.raccoons.tda.auth.service.data;

import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.model.token.SecureAccessToken;
import com.raccoons.tda.auth.repository.SecureAccessTokenRepository;
import com.raccoons.tda.auth.service.security.AccessTokenSecurityService;
import com.raccoons.tda.auth.service.security.EncryptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Service
public class AccessTokenRepositoryService {

    private static final Logger logger = LogManager.getLogger(AccessTokenRepositoryService.class);

    @Autowired
    private SecureAccessTokenRepository secureAccessTokenRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private AccessTokenSecurityService accessTokenSecurityService;

    public CompletableFuture<Optional<AccessToken>> getAccessToken(final long requestId, final String owner) {
        final String ownerEntry = accessTokenSecurityService.encryptAndWrapOwner(owner);
        logger.trace("[{}] Fetching access token from repository.", requestId);
        try {
            return secureAccessTokenRepository.findTopByOwner(ownerEntry).thenApply(secureAccessToken -> {
                logger.trace("[{}] Decrypting SecureAccessToken.", requestId);
                return secureAccessToken.map(accessToken -> accessTokenSecurityService.decrypt(accessToken));
            });
        } catch (Exception e) {
            logger.error(e);
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }

    public CompletableFuture<String> storeAccessToken(final long requestId, final AccessToken accessToken) {
        logger.trace("[{}] Starting access token storage in repository.", requestId);
        final SecureAccessToken secureAccessToken = accessTokenSecurityService.encrypt(accessToken);
        return CompletableFuture.supplyAsync(() -> {
            try {
                final SecureAccessToken s = secureAccessTokenRepository.save(secureAccessToken);
                logger.info("[{}] Secured access token was successfully stored in the repository.", requestId);
                return "";
            } catch (Exception e) {
                logger.error("[{}] Error occurred while trying to store secured access token in the repository.",requestId, e);
                return null;
            }
        }, threadPoolTaskScheduler);
    }

    public CompletableFuture<Boolean> deleteAccessToken(final long requestId, final String owner) {
        final AccessToken accessToken = AccessToken.newBuilder().owner(owner).build();
        final SecureAccessToken secureAccessToken = accessTokenSecurityService.encrypt(accessToken);

        return CompletableFuture.supplyAsync(() -> {
            try {
                logger.trace("[{}] Deleting access token from repository.", requestId);
                secureAccessTokenRepository.delete(secureAccessToken);
                return true;
            } catch (Exception e) {
                logger.error("[{}] Error occurred while attempting to delete access token from repository", requestId, e);
                return false;
            }
        }, threadPoolTaskScheduler);
    }
}

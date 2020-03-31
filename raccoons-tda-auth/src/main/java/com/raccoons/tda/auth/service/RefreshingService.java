package com.raccoons.tda.auth.service;

import com.raccoons.tda.auth.component.RequestId;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.service.token.AccessTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class RefreshingService {

    private static final Logger logger = LogManager.getLogger(RefreshingService.class);

    @Value("${tda.auth.refresh.enable}")
    private boolean refreshEnable;

    @Value("${tda.auth.refresh.frequency}")
    private long refreshFrequency;

    @Value("${tda.auth.refresh.threshold}")
    private double refreshThreshold;

    @Autowired
    private AuthClientService authClientService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private RequestId requestId;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> scheduledFuture;

    private Map<String, Long> refreshedTime;

    @PostConstruct
    private void init() {
        logger.trace("Initializing RefreshService instance.");
    }

    public void start() {
        if (refreshEnable) {
            scheduledFuture = threadPoolTaskScheduler.getScheduledExecutor().scheduleWithFixedDelay(() -> {
                System.out.println();
            }, 0, refreshFrequency, TimeUnit.MILLISECONDS);
        }
    }

    public void stop() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
    }

    public boolean expiration(final String owner) {
        return false;
    }

    public void refreshIfExpired(final AccessToken accessToken) {

    }

    public void updateRefreshTime(final String owner) {
        logger.trace("Updating refresh time for {}", owner);
    }

    public long nextRefresh(final String owner) {
        return -1;
    }

    private CompletableFuture<Optional<AccessToken>> refreshByOwner(final String owner) {
        if (owner != null) {
            final long id = requestId.getId();
            logger.trace("[{}] Refreshing access token for owner {}.", id, owner);
            return accessTokenService.refreshAccessTokenByOwner(id, owner).thenApply(accessToken -> {
                logger.trace("[{}] Completed", id);
                return accessToken;
            });
        }
        return CompletableFuture.completedFuture(Optional.empty());
    }

}

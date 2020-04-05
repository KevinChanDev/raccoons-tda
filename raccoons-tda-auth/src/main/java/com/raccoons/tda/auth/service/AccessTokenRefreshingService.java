package com.raccoons.tda.auth.service;

import com.raccoons.tda.auth.component.RequestId;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.service.subscription.AccessTokenSubscriptionService;
import com.raccoons.tda.auth.service.token.AccessTokenService;
import com.raccoons.tda.auth.util.DelayedModificationQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AccessTokenRefreshingService {

    private static final Logger logger = LogManager.getLogger(AccessTokenRefreshingService.class);

    @Value("${tda.auth.refresh.enable}")
    private boolean refreshEnable;

    @Value("${tda.auth.refresh.frequency}")
    private long refreshFrequency;

    @Value("${tda.auth.refresh.threshold}")
    private double refreshThreshold;

    @Value("${tda.auth.refresh.time.threshold}")
    private long timeThreshold;

    @Autowired
    private AuthClientService authClientService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private AccessTokenSubscriptionService accessTokenSubscriptionService;

    @Autowired
    private RequestId requestId;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> scheduledFuture;

    private Map<String, Long> refreshingTime;

    private DelayedModificationQueue<String> refreshableQueue;

    // Task states
    private AtomicBoolean running;

    @PostConstruct
    private void init() {
        logger.trace("Initializing RefreshService instance.");
        refreshingTime = new ConcurrentHashMap<>();
        refreshableQueue = new DelayedModificationQueue<>();
        running = new AtomicBoolean();
    }

    @Scheduled(initialDelay = 30000, fixedDelay = 60000)
    private void refreshingTask() {
        if (!running.get()) {
            running.set(true);
        }
        try {
            while (running.get()) {
                final String owner = refreshableQueue.take();
                if (owner != null) {
                    final int numSubscribers = accessTokenSubscriptionService.countSubscribers(owner);
                    if (numSubscribers > 0) {
                        logger.trace("Processing queued owner {}.", owner);
                        refreshByOwner(owner);
                    } else {
                        logger.trace("Skipping queued owner {} no subscribers.", owner);
                    }
                }
            }
        } catch (Exception e) {
            running.set(false);
        }
    }

    private void processQueue() throws InterruptedException {
        final String owner = refreshableQueue.take();
        logger.trace("Processing queued owner {}.", owner);
        refreshByOwner(owner);
    }

    private void enqueueRefreshable(final long requestId, final String owner, final long delay) {
        logger.trace("[{}] Enqueueing refresh for {} with a a delay of {}", requestId, owner, delay);
        long expiration = refreshableQueue.put(owner, delay);

        if (expiration > 0) {
            logger.trace("[{}] Enqueueing refresh for {} with a a delay of {}", requestId, owner, delay);
            refreshingTime.put(owner, expiration);
        }
    }

    public void updateRefreshTime(final String owner) {
        logger.trace("Updating refresh time for {}", owner);
    }

    public void registerRefresh(final long requestId, final String owner, final long expiration) {
        logger.trace("Registering refresh event for {} in refreshing service.", owner);
        final long currentTime = System.currentTimeMillis();

        if (currentTime > expiration) {
            enqueueRefreshable(requestId, owner, 1L);
        } else {
            final long delay = Math.max(1L, expiration - currentTime - timeThreshold);
            enqueueRefreshable(requestId, owner, delay);
        }
    }

    @Async
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

package com.raccoons.tda.auth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class RefreshService {

    private static final Logger logger = LogManager.getLogger(RefreshService.class);

    @Value("${tda.auth.refresh.enable}")
    private boolean refreshEnable;

    @Value("${tda.auth.refresh.frequency}")
    private long refreshFrequency;

    @Value("${tda.auth.refresh.threshold}")
    private double refreshThreshold;

    @Autowired
    private AuthService authService;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> scheduledFuture;

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

}

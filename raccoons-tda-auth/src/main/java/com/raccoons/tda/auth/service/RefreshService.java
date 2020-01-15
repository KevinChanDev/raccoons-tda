package com.raccoons.tda.auth.service;

import com.raccoons.tda.auth.model.AccessToken;
import com.raccoons.tda.auth.util.TDAAuthConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class RefreshService {

    @Value("${tda.auth.refresh.enable}")
    private boolean refreshEnable;

    @Value("${tda.auth.refresh.frequency}")
    private long refreshFrequency;

    @Value("${tda.auth.refresh.threshold}")
    private double refreshThreshold;

    @Autowired
    private HttpService httpService;

    @Autowired
    private TDAAuthConfiguration authConfiguration;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> scheduledFuture;


    public RefreshService() {
    }

    @PostConstruct
    private void init() {
    }

    private AccessToken get(AccessToken accessToken) {
        return null;
    }

    public void start() {
        if (refreshEnable) {
            scheduledFuture = threadPoolTaskScheduler.getScheduledExecutor().scheduleWithFixedDelay(() -> {
                System.out.println();
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

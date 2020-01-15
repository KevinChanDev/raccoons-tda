package com.raccoons.tda.auth.service;

import com.raccoons.tda.net.AsyncTDAHttpClient;
import com.raccoons.tda.net.TDAHttpClient;
import com.raccoons.tda.net.TDAHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class HttpService {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private TDAHttpClient tdaHttpClient;

    @PostConstruct
    public void init() {
        tdaHttpClient = new AsyncTDAHttpClient(threadPoolTaskScheduler.getScheduledExecutor());
    }

    public CompletableFuture<TDAHttpResponse> post(String endpoint, Map<String, String> headers, Map<String, Object> data) {
        return tdaHttpClient.post(endpoint, headers, data);
    }

    public TDAHttpClient getTdaHttpClient() {
        return tdaHttpClient;
    }
}

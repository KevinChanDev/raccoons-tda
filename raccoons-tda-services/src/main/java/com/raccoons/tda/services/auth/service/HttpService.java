package com.raccoons.tda.services.auth.service;

import com.raccoons.tda.net.AsyncTDAHttpClient;
import com.raccoons.tda.net.TDAHttpClient;
import com.raccoons.tda.net.TDAHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class HttpService {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private TDAHttpClient tdaHttpClient;

    @PostConstruct
    public void init() {
        tdaHttpClient = new AsyncTDAHttpClient(threadPoolTaskScheduler.getScheduledExecutor());
    }

    public CompletableFuture<TDAHttpResponse> post(String endpoint, Map<String, String> headers, byte[] data) {
        return tdaHttpClient.post(endpoint, headers, data);
    }
}

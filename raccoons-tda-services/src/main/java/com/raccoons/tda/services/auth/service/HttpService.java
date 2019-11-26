package com.raccoons.tda.services.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class HttpService {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private HttpClient httpClient;

    @PostConstruct
    public void init() {
        httpClient = HttpClient.newBuilder().executor(threadPoolTaskScheduler.getScheduledExecutor()).build();
    }

    public CompletableFuture<String> post() {
        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        return httpClient.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(new Function<HttpResponse<byte[]>, String>() {
                    @Override
                    public String apply(HttpResponse<byte[]> httpResponse) {
                        return null;
                    }
                });
    }
}

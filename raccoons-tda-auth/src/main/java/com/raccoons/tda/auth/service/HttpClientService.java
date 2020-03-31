package com.raccoons.tda.auth.service;

import com.raccoons.tda.context.TDAContext;
import com.raccoons.tda.net.TDAHttpClient;
import com.raccoons.tda.net.TDAHttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class HttpClientService {

    private static final Logger logger = LogManager.getLogger(HttpClientService.class);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private TDAContext tdaContext;

    private TDAHttpClient tdaHttpClient;

    @PostConstruct
    public void init() {
        tdaHttpClient = tdaContext.getClientProvider().getTDAHttpClient();
    }

    public CompletableFuture<TDAHttpResponse> post(String endpoint, Map<String, String> headers, Map<String, Object> data) {
        logger.info("Making POST request to endpoint {}.", endpoint);
        return tdaHttpClient.post(endpoint, headers, data);
    }

    public TDAHttpClient getTDAHttpClient() {
        return tdaHttpClient;
    }
}

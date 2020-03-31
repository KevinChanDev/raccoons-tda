package com.raccoons.tda.auth.service.client;

import com.raccoons.auth.lib.message.ServiceMessage;
import com.raccoons.tda.auth.model.token.AccessToken;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MessageBuilderService {

    private AtomicLong messageIdCounter;

    @PostConstruct
    public void init() {
        messageIdCounter = new AtomicLong();
    }

    public ServiceMessage buildMessage(final AccessToken accessToken) {
        final Map<String, Object> messagePayload = new HashMap<>();
        final long timestamp = System.currentTimeMillis();

        messagePayload.put("owner", accessToken.getOwner());
        messagePayload.put("access_token", accessToken.getAccessToken());

        return ServiceMessage.newBuilder()
                .id(messageIdCounter.incrementAndGet())
                .timestamp(timestamp)
                .payload(messagePayload)
                .build();
    }

}

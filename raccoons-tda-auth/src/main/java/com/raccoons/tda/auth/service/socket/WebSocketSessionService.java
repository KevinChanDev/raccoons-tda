package com.raccoons.tda.auth.service.socket;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebSocketSessionService {

    private Map<String, String> webSocketSessions;

    @PostConstruct
    public void init() {
        webSocketSessions = new ConcurrentHashMap<>();
    }

    public void temporary() {

    }

}

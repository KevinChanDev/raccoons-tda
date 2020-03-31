package com.raccoons.tda.auth.service.socket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WebSocketAuthorizer {

    public boolean isAuthorized(final WebSocketSession webSocketSession) {
        final String sessionId = webSocketSession.getId();

        return false;
    }

}

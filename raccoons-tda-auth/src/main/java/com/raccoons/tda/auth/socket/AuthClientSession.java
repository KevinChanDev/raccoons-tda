package com.raccoons.tda.auth.socket;

import org.springframework.web.reactive.socket.WebSocketSession;

public class AuthClientSession {

    private WebSocketSession webSocketSession;

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

}

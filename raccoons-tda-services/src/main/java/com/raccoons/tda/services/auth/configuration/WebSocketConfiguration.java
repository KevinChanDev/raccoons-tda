package com.raccoons.tda.services.auth.configuration;

import com.raccoons.tda.services.auth.socket.ConnectionSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


//@Configuration
//@EnableWebSocket

public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ConnectionSocketHandler(), "/connection").setAllowedOrigins("*");
    }
}
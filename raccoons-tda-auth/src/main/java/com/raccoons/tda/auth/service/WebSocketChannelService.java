package com.raccoons.tda.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.auth.lib.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.function.Consumer;

@Service
public class WebSocketChannelService {

    @Autowired
    private MessageSerializationService messageSerializationService;

    @Autowired
    private WebSocketSessionService webSocketSessionService;

    @Autowired
    private ObjectMapper objectMapper;

    public void onSocketOpen(WebSocketSession session) {
        System.out.println("The session has been OPENED: " + session.getId());
    }

    public void onTextMessage(WebSocketSession session, TextMessage message) {
        final String address = session.getRemoteAddress().toString();
        System.out.println(String.format("%s (%s) ---> %s", session.getId(), address, message.getPayload()));

        final AccessTokenResponse response = new AccessTokenResponse(1, "owner1", "test-token");

        sendAccessTokenResponse(session, response);
    }

    public void onSocketClosed(WebSocketSession session) {
        System.out.println("The session has been CLOSED: " + session.getId());
    }

    public void sendAccessTokenResponse(WebSocketSession session, AccessTokenResponse accessTokenResponse) {
        messageSerializationService.writeResponse(accessTokenResponse).ifPresent(s -> {
            try {
                session.sendMessage(new TextMessage(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}

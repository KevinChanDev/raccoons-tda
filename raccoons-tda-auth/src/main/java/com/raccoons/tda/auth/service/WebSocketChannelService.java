package com.raccoons.tda.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WebSocketChannelService {

    @Autowired
    private WebSocketSessionService webSocketSessionService;

    public void onSocketOpen(WebSocketSession session) {

    }

    public void onTextMessage(WebSocketSession session, TextMessage message) {
        final String address = session.getRemoteAddress().toString();
        System.out.println(String.format("%s (%s) ---> %s", session.getId(), address, message.getPayload()));
    }

    public void onSocketClosed(WebSocketSession session) {

    }

}

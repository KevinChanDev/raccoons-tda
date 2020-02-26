package com.raccoons.tda.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.auth.lib.AccessTokenResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Service
public class WebSocketChannelService {

    private static final Logger logger = LogManager.getLogger(AuthService.class);

    @Autowired
    private MessageSerializationService messageSerializationService;

    @Autowired
    private WebSocketSessionService webSocketSessionService;

    @Autowired
    private ObjectMapper objectMapper;

    public void onSocketOpen(final WebSocketSession session) {
        final String sessionId = session.getId();
        logger.info("A WebSocketSession has been opened {} ", sessionId);
    }

    public void onTextMessage(final WebSocketSession session, final TextMessage message) {
        final String sessionId = session.getId();
        logger.info("Received a message from {}.", sessionId);
        logger.info(message.asBytes());
//        final AccessTokenResponse response = new AccessTokenResponse(1, "owner1", "test-token");
//        sendAccessTokenResponse(session, response);
    }

    public void onSocketClosed(final WebSocketSession session) {
        final String sessionId = session.getId();
        logger.info("A WebSocketSession has been closed {}.", sessionId);
    }

    public void sendAccessTokenResponse(final WebSocketSession session, final AccessTokenResponse accessTokenResponse) {
        messageSerializationService.writeResponse(accessTokenResponse).ifPresent(s -> {
            final String sessionId = session.getId();
            final int messageSize = s.length();
            try {
                logger.info("Sending message to of size {} {}", messageSize, sessionId);
                session.sendMessage(new TextMessage(s));
            } catch (IOException e) {
                logger.error("Unable to send a message of size {} to {}. ", messageSize, sessionId);
                logger.error(e);
            }
        });
    }

}

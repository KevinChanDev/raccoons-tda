package com.raccoons.tda.auth.service.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.auth.lib.message.ServiceMessage;
import com.raccoons.tda.auth.client.ClientSession;
import com.raccoons.tda.auth.component.ServiceMessageSerializer;
import com.raccoons.tda.auth.service.client.ClientMessageExchangeService;
import com.raccoons.tda.auth.service.client.ClientSessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Service
public class ClientChannelService {

    private static final Logger logger = LogManager.getLogger(ClientChannelService.class);

    @Autowired
    private ServiceMessageSerializer serviceMessageSerializer;

    @Autowired
    private ClientSessionService clientSessionService;

    @Autowired
    private ClientMessageExchangeService clientMessageExchangeService;

    @Autowired
    private ObjectMapper objectMapper;

    public void onSocketOpen(final WebSocketSession session) {
        final String sessionId = session.getId();
        logger.info("A WebSocketSession has been opened {} ", sessionId);
        final ClientSession clientSession = ClientSession.newBuilder().webSocketSession(session).build();
        clientSessionService.register(clientSession);
    }

    public void onSocketClosed(final WebSocketSession session) {
        final String sessionId = session.getId();
        logger.info("A WebSocketSession has been closed {}.", sessionId);
        clientSessionService.unregister(sessionId);
    }

    public void onTextMessage(final WebSocketSession session, final TextMessage message) {
        final String sessionId = session.getId();
        logger.info("Received a message from {}.", sessionId);
        clientMessageExchangeService.messageReceived(session, message);
    }

    public void sendAccessTokenResponse(final ClientSession session, final ServiceMessage serviceMessage) {
        serviceMessageSerializer.writeMessage(serviceMessage).ifPresent(s -> {
            final String sessionId = session.getSessionId();
            final int messageSize = s.length();
            try {
                logger.info("Sending message to of size {} {}", messageSize, sessionId);
                session.getWebSocketSession().sendMessage(new TextMessage(s));
            } catch (IOException e) {
                logger.error("Unable to send a message of size {} to {}. ", messageSize, sessionId);
                logger.error(e);
            }
        });
    }

}

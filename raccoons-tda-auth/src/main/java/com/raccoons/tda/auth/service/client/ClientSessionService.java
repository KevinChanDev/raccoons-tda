package com.raccoons.tda.auth.service.client;

import com.raccoons.auth.lib.message.ServiceMessage;
import com.raccoons.tda.auth.client.ClientSession;
import com.raccoons.tda.auth.component.ServiceMessageSerializer;
import com.raccoons.tda.auth.service.subscription.AccessTokenSubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientSessionService {

    private static final Logger logger = LogManager.getLogger(ClientSessionService.class);

    @Autowired
    private AccessTokenSubscriptionService accessTokenSubscriptionService;

    @Autowired
    private ServiceMessageSerializer serviceMessageSerializer;

    private Map<String, ClientSession> clientSessions;

    @PostConstruct
    public void init() {
        clientSessions = new ConcurrentHashMap<>();
    }

    public void register(final ClientSession clientSession) {
        logger.info("Adding client session {}.", clientSession.getSessionId());
        clientSession.connect();
        clientSessions.put(clientSession.getSessionId(), clientSession);
    }

    public void unregister(final String sessionId) {
        if (sessionId != null) {
            logger.info("Removing client session {}", sessionId);

            final ClientSession clientSession = clientSessions.get(sessionId);
            if (clientSession != null) {
                clientSession.disconnect();
                clientSessions.remove(sessionId);
                accessTokenSubscriptionService.removeSession(clientSession);
            }
        }
    }

    public boolean isRegistered(final String sessionId) {
        return clientSessions.containsKey(sessionId);
    }

    public Optional<ClientSession> getClientSession(final String sessionId) {
        return Optional.ofNullable(clientSessions.get(sessionId));
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

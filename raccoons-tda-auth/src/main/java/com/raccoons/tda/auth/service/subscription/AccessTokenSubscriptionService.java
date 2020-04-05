package com.raccoons.tda.auth.service.subscription;

import com.raccoons.tda.auth.client.ClientSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccessTokenSubscriptionService {

    private static final Logger logger = LogManager.getLogger(AccessTokenSubscriptionService.class);

    private Map<String, Set<ClientSession>> subscriptionClients;

    @PostConstruct
    public void init() {
        subscriptionClients = new ConcurrentHashMap<>();
    }

    public ClientSession[] getSubscribers(final String subscription) {
        final Set<ClientSession> clientSessions = subscriptionClients.get(subscription);
        if (clientSessions != null) {
            return clientSessions.toArray(new ClientSession[0]);
        }
        return new ClientSession[0];
    }

    public int countSubscribers(final String subscription) {
        final Set<ClientSession> clientSessions = subscriptionClients.get(subscription);
        if (clientSessions != null) {
            return clientSessions.size();
        }
        return 0;
    }

    public boolean isSubscribed(final ClientSession clientSession, final String subscription) {
        final Set<ClientSession> sessions = subscriptionClients.get(subscription);
        return sessions != null && sessions.contains(clientSession);
    }

    public void subscribe(final ClientSession clientSession, final String subscription) {
        subscriptionClients.computeIfAbsent(subscription, s -> ConcurrentHashMap.newKeySet()).add(clientSession);
        clientSession.subscribe(subscription);
        logger.trace("{} subscribed to {}.", clientSession.getSessionId(), subscription);
    }

    public void removeSession(final ClientSession session) {
        if (session != null) {
            logger.trace("Removing {} from subscription service.", session.getSessionId());
            final String[] subscriptions = session.getSubscriptions();
            logger.trace("Removing {} subscriptions from {}.", subscriptions.length, session.getSessionId());

            Set<ClientSession> clientSessions;
            for (String s : subscriptions) {
                clientSessions = subscriptionClients.get(s);
                if (clientSessions != null) {
                    clientSessions.remove(session);
                }
            }
        }
    }
}

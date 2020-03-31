package com.raccoons.tda.auth.client;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientSession {

    private final AtomicBoolean disconnected;
    private final AtomicBoolean connected;
    private final String sessionId;
    private final WebSocketSession webSocketSession;
    private final Map<String, ClientTokenState> clientTokenStates;
    private final Set<String> subscriptions;

    private ClientSession(final WebSocketSession webSocketSession) {
        this.disconnected = new AtomicBoolean();
        this.connected = new AtomicBoolean();
        this.sessionId = webSocketSession.getId();
        this.webSocketSession = webSocketSession;
        this.clientTokenStates = new ConcurrentHashMap<>();
        this.subscriptions = new ConcurrentSkipListSet<>();
    }

    public boolean isConnected() {
        return connected.get();
    }

    public void connect() {
        if (!disconnected.get()) {
            connected.set(true);
        }
    }

    public void disconnect() {
        if (!disconnected.get()) {
            connected.set(false);
            disconnected.set(true);
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public Map<String, ClientTokenState> getClientTokenStates() {
        return clientTokenStates;
    }

    public Set<String> getTokens() {
        return clientTokenStates.keySet();
    }

    public void subscribe(final String subscription) {
        subscriptions.add(subscription);
    }

    public void unsubscribe(final String subscription) {
        subscriptions.remove(subscription);
    }

    public String[] getSubscriptions() {
        return subscriptions.toArray(new String[0]);
    }

    public boolean isSubscribed(final String subscription) {
        return subscriptions.contains(subscription);
    }

    public void addClientTokenState() {

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private WebSocketSession webSocketSession;

        public Builder webSocketSession(WebSocketSession webSocketSession) {
            this.webSocketSession = webSocketSession;
            return this;
        }

        public ClientSession build() {
            return new ClientSession(webSocketSession);
        }
    }

}

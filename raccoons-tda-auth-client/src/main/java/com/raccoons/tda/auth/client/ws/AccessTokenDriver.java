package com.raccoons.tda.auth.client.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.auth.lib.ServiceRequest;
import com.raccoons.auth.lib.message.ServiceMessage;
import com.raccoons.auth.lib.ServiceMessageType;

import java.net.http.WebSocket;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;

public class AccessTokenDriver implements WebSocket.Listener {

    private static final TypeReference<ServiceMessage> SERVICE_MESSAGE_REFERENCE = new TypeReference<>() {
    };

    private AtomicBoolean connected;
    private WebSocket webSocket;
    private Listener listener;
    private ObjectMapper objectMapper;

    public AccessTokenDriver(Listener listener) {
        this.connected = new AtomicBoolean();
        this.listener = listener;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        this.webSocket = webSocket;

        if (listener != null) {
            listener.onOpen();
        }
        webSocket.request(1);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        if (listener != null) {
            final String jsonData = data.toString();
            try {
                final ServiceMessage response = objectMapper.readValue(jsonData, SERVICE_MESSAGE_REFERENCE);
                final int responseType = response.getMessageType();
                System.out.println("Service Message: " + ServiceMessageType.ACCESS_TOKEN);
                if (responseType == ServiceMessageType.ACCESS_TOKEN || responseType == ServiceMessageType.REFRESH_RESPONSE) {
                    final Map<String, Object> payload = response.getPayload();

                    final String owner = (String) payload.get("owner");
                    final String accessToken = (String) payload.get("access_token");
                    final Object expiration = payload.get("access_token_expiration");

                    listener.onAccessToken(owner, accessToken);
                } else if (responseType == ServiceMessageType.REVOKE_RESPONSE) {
                    final Map<String, Object> payload = response.getPayload();
                    final String owner = (String) payload.get("owner");
                    listener.onAccessTokenRevoked(owner);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        webSocket.request(1L);
        return null;
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        connected.set(false);
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        error.printStackTrace();
    }

    public CompletableFuture<WebSocket> sendRequest(ServiceRequest request) {
        try {
            final String requestString = objectMapper.writeValueAsString(request);
            return webSocket.sendText(requestString, true);
        } catch (JsonProcessingException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public boolean isConnected() {
        return connected.get();
    }

    public void close() {
        webSocket.abort();
        connected.set(false);

        if (listener != null) {
            listener.onClose();
        }
    }

    public interface Listener {
        void onOpen();

        void onAccessToken(String owner, String accessToken);

        void onAccessTokenRevoked(String owner);

        void onClose();
    }

}

package com.raccoons.tda.auth.client.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.auth.lib.AccessTokenRequest;
import com.raccoons.auth.lib.AccessTokenResponse;
import com.raccoons.auth.lib.AccessTokenResponseType;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;

public class AccessTokenDriver implements WebSocket.Listener {

    private static TypeReference<AccessTokenResponse> RESPONSE_TYPE_REFERENCE = new TypeReference<>() {
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
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        if (listener != null) {
            final String jsonData = data.toString();
            try {
                final AccessTokenResponse response = objectMapper.readValue(jsonData, RESPONSE_TYPE_REFERENCE);
                final int responseType = response.getResponseType();
                if (responseType == AccessTokenResponseType.ACCESS_TOKEN) {
                    final String owner = response.getOwner();
                    final String accessToken = response.getAccessToken();

                    listener.onAccessToken("", "");

                } else {

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        webSocket.request(1L);
        return CompletableFuture.completedFuture(data);
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        connected.set(false);
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {

    }

    public CompletableFuture<WebSocket> sendRequest(AccessTokenRequest request) {
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
    }

    public interface Listener {
        void onOpen();

        void onAccessToken(String owner, String accessToken);

        void onAccessTokenRevoked(String owner);

        void onClose();
    }

}

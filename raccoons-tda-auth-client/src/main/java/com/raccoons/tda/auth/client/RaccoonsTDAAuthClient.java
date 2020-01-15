package com.raccoons.tda.auth.client;

import com.raccoons.auth.lib.AccessTokenRequest;
import com.raccoons.tda.auth.AccessTokenProvider;
import com.raccoons.tda.auth.client.ws.AccessTokenDriver;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class RaccoonsTDAAuthClient implements AccessTokenProvider, AccessTokenDriver.Listener {

    public static final int CONNECTED = 0;
    public static final int DISCONNECTED = 1;

    private static final String EMPTY_TOKEN = "";

    private final AccessTokenDriver accessTokenDriver;
    private final AtomicReference<String> accessToken;
    private final Map<String, String> accessTokens;

    private AtomicInteger status;

    public RaccoonsTDAAuthClient(final String tdaAuthServiceEndpoint) {
        final HttpClient client = HttpClient.newHttpClient();

        this.accessTokenDriver = new AccessTokenDriver(this);
        this.accessToken = new AtomicReference<>(EMPTY_TOKEN);
        this.status = new AtomicInteger();
        this.accessTokens = new ConcurrentHashMap<>();

        client.newWebSocketBuilder().buildAsync(URI.create(tdaAuthServiceEndpoint), accessTokenDriver).join();
    }

    @Override
    public String getAccessToken(String owner) {
        return accessTokens.computeIfAbsent(owner, s -> {
            final AccessTokenRequest request = AccessTokenRequest.request(s);
            accessTokenDriver.sendRequest(request);
            return null;
        });
    }

    @Override
    public CompletableFuture<String> getAccessTokenAsync(String owner) {
        return null;
    }

    @Override
    public void refreshAccessToken(String owner) {
        final AccessTokenRequest request = AccessTokenRequest.refresh(owner);
        accessTokenDriver.sendRequest(request);
    }

    @Override
    public String refreshAndGetAccessToken(String owner) {
        final AccessTokenRequest request = AccessTokenRequest.refresh(owner);
        accessTokenDriver.sendRequest(null);
        return null;
    }

    @Override
    public boolean revokeToken(String owner) {
        accessTokens.remove(owner);
        return false;
    }

    @Override
    public boolean invalidate(String ownerKey) {
        return false;
    }

    @Override
    public double availability() {
        return status() == CONNECTED ? 1.0 : 0.0;
    }

    @Override
    public int status() {
        return status.get();
    }

    @Override
    public void close() throws IOException {
        accessTokenDriver.close();
    }

    @Override
    public void onOpen() {
        status.set(CONNECTED);
    }

    @Override
    public void onAccessToken(String owner, String accessToken) {
        accessTokens.put(owner, accessToken);
    }

    @Override
    public void onAccessTokenRevoked(String owner) {
        accessTokens.remove(owner);
    }

    @Override
    public void onClose() {
        status.set(DISCONNECTED);
    }
}

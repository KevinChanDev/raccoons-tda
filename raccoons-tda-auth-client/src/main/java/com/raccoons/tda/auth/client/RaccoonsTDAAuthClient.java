package com.raccoons.tda.auth.client;

import com.raccoons.auth.lib.ServiceRequest;
import com.raccoons.tda.auth.TDAAccessTokenProvider;
import com.raccoons.tda.auth.client.util.BlockingConcurrentMap;
import com.raccoons.tda.auth.client.ws.AccessTokenDriver;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RaccoonsTDAAuthClient implements TDAAccessTokenProvider, AccessTokenDriver.Listener {

    public static final int CONNECTED = 0;
    public static final int DISCONNECTED = 1;

    private final AccessTokenDriver accessTokenDriver;
    private BlockingConcurrentMap<String, String> blockingConcurrentMap;
    private final Map<String, String> accessTokens;
    private final Map<String, Object> accessTokenLocks;
    private final AtomicInteger status;
    private final AtomicLong requestId;

    public RaccoonsTDAAuthClient(final String tdaAuthServiceEndpoint) {
        this(tdaAuthServiceEndpoint, new String[0]);
    }

    public RaccoonsTDAAuthClient(final String tdaAuthServiceEndpoint, final String[] initialOwners) {
        final HttpClient client = HttpClient.newHttpClient();

        this.accessTokenDriver = new AccessTokenDriver(this);
        this.status = new AtomicInteger();
        this.accessTokens = new ConcurrentHashMap<>();
        this.accessTokenLocks = new ConcurrentHashMap<>();
        this.requestId = new AtomicLong();

        client.newWebSocketBuilder().buildAsync(URI.create(tdaAuthServiceEndpoint), accessTokenDriver).join();
    }

    @Override
    public String getAccessToken(String owner) {
        return accessTokens.computeIfAbsent(owner, s -> {
            if (s != null && !s.isEmpty()) {
                final ServiceRequest request = ServiceRequest.request(requestId.incrementAndGet(), s);
                accessTokenDriver.sendRequest(request);
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<String> getAccessTokenAsync(String owner) {
        return null;
    }

    @Override
    public void refreshAccessToken(String owner) {
        final ServiceRequest request = ServiceRequest.refresh(requestId.incrementAndGet(), owner);
        accessTokenDriver.sendRequest(request);
    }

    @Override
    public String refreshAndGetAccessToken(String owner) {
        final ServiceRequest request = ServiceRequest.refresh(requestId.incrementAndGet(), owner);
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
        System.out.println(String.format("%s -> %s", owner, accessToken));
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

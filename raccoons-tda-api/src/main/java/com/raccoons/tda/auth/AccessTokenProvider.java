package com.raccoons.tda.auth;

import java.io.Closeable;
import java.util.concurrent.CompletableFuture;

public interface AccessTokenProvider extends Closeable {

    String getAccessToken(String owner);

    CompletableFuture<String> getAccessTokenAsync(final String owner);

    void refreshAccessToken(String owner);

    String refreshAndGetAccessToken(String owner);

    boolean revokeToken(String owner);

    boolean invalidate(String owner);

    double availability();

    int status();

}

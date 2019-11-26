package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;

import java.util.concurrent.CompletableFuture;

public class UserInfoClient extends RequestOperation {

    public UserInfoClient(RequestClient requestClient) {
        super(requestClient);
    }

    public CompletableFuture<TDAResponse> getPreferences() {
        return null;
    }

    public CompletableFuture<TDAResponse> getStreamerSubscriptionKeys() {
        return null;
    }

    public CompletableFuture<TDAResponse> getUserPrincipals() {
        return null;
    }

    public CompletableFuture<TDAResponse> updatePreferences() {
        return null;
    }
}

package com.raccoons.tda.auth.model.token;

public class ExpiringAccessToken {

    private long expirationTime;
    private AccessToken accessToken;

    public ExpiringAccessToken(long expirationTime, AccessToken accessToken) {
        this.expirationTime = expirationTime;
        this.accessToken = accessToken;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

}

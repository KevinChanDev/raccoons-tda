package com.raccoons.tda.services.auth.model;

public class AccessTokenResponse {

    private char[] accessToken;

    public AccessTokenResponse(char[] accessToken) {
        this.accessToken = accessToken;
    }

    public char[] getAccessToken() {
        return accessToken;
    }
}

package com.raccoons.tda.auth.model;

import java.time.ZonedDateTime;

public class AccessToken {

    private char[] accessToken;
    private char[] refreshToken;
    private ZonedDateTime accessTokenExpiration;
    private ZonedDateTime refreshTokenExpiration;

    public char[] getAccessToken() {
        return accessToken;
    }

    public char[] getRefreshToken() {
        return refreshToken;
    }

    public void setAccessToken(char[] accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(char[] refreshToken) {
        this.refreshToken = refreshToken;
    }
}

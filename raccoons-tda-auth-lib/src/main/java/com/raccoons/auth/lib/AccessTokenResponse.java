package com.raccoons.auth.lib;

public class AccessTokenResponse {

    private int responseType;
    private String owner;
    private String accessToken;

    public int getResponseType() {
        return responseType;
    }

    public String getOwner() {
        return owner;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

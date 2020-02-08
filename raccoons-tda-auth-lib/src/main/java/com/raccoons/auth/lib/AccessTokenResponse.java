package com.raccoons.auth.lib;

public class AccessTokenResponse {

    private int responseType;
    private String owner;
    private String accessToken;

    public AccessTokenResponse() {
    }

    public AccessTokenResponse(int responseType, String owner, String accessToken) {
        this.responseType = responseType;
        this.owner = owner;
        this.accessToken = accessToken;
    }

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

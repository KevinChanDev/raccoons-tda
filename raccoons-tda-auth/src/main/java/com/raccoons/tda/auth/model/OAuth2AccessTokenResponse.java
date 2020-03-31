package com.raccoons.tda.auth.model;

import java.util.Map;

public class OAuth2AccessTokenResponse {

    private boolean valid;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresIn;
    private long refreshTokenExpiresIn;

    private OAuth2AccessTokenResponse(boolean valid) {
        this.valid = valid;
    }

    private OAuth2AccessTokenResponse(final Map<String, Object> values) {
        try {
            final String accessToken = (String) values.get("access_token");
            final String refreshToken = (String) values.get("refresh_token");
            final String tokenType = (String) values.get("token_type");

            final Object refreshTokenExpiresIn = values.get("refresh_token_expires_in");
            final Object expiresIn = values.get("expires_in");

            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.tokenType = tokenType;

            if (expiresIn instanceof Integer) {
                this.expiresIn = ((Integer) expiresIn).longValue() * 1000;
            } else if (expiresIn instanceof Long) {
                this.expiresIn = (long) expiresIn * 1000;
            } else {
                valid = false;
            }

            if (refreshTokenExpiresIn instanceof Integer) {
                this.refreshTokenExpiresIn = ((Integer) refreshTokenExpiresIn).longValue() * 1000;
            } else if (refreshTokenExpiresIn instanceof Long) {
                this.refreshTokenExpiresIn = (long) refreshTokenExpiresIn * 1000;
            } else {
                valid = false;
            }

            valid = true;
        } catch (Exception e) {
            valid = false;
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public boolean isValid() {
        return valid;
    }

    public static OAuth2AccessTokenResponse of(Map<String, Object> values) {
        return new OAuth2AccessTokenResponse(values);
    }

    public static OAuth2AccessTokenResponse failed() {
        return new OAuth2AccessTokenResponse(false);
    }
}

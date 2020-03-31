package com.raccoons.tda.auth.model;

import java.util.Map;

public class OAuth2RefreshAccessTokenResponse {

    private boolean valid;
    private String accessToken;
    private long expiresIn;

    private OAuth2RefreshAccessTokenResponse(boolean valid) {
        this.valid = valid;
    }

    private OAuth2RefreshAccessTokenResponse(final Map<String, Object> values) {
        try {
            final String accessToken = (String) values.get("access_token");
            if (accessToken != null) {
                this.accessToken = accessToken;
            } else {
                valid = false;
            }

            final Object expiresInValue = values.get("expires_in");
            if (expiresInValue instanceof Integer) {
                this.expiresIn = ((Integer) expiresInValue).longValue() * 1000;
            } else if (expiresInValue instanceof Long) {
                this.expiresIn = (long) expiresInValue * 1000;
            } else {
                valid = false;
            }
            valid = true;
        } catch (Exception e) {
            valid = false;
        }
    }

    public boolean isValid() {
        return valid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public static OAuth2RefreshAccessTokenResponse of(final Map<String, Object> values) {
        return new OAuth2RefreshAccessTokenResponse(values);
    }

    public static OAuth2RefreshAccessTokenResponse failed() {
        return new OAuth2RefreshAccessTokenResponse(false);
    }

}

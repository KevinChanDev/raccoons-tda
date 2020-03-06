package com.raccoons.tda.auth.model;

import java.util.Map;

public class OAuth2RefreshAccessTokenResponse {

    private boolean valid;

    public OAuth2RefreshAccessTokenResponse(boolean valid) {
        this.valid = valid;
    }

    public OAuth2RefreshAccessTokenResponse(final Map<String, Object> values){

    }

    public boolean isValid() {
        return valid;
    }

    public static OAuth2RefreshAccessTokenResponse of(final Map<String, Object> values) {
        return new OAuth2RefreshAccessTokenResponse(values);
    }

    public static OAuth2RefreshAccessTokenResponse failed() {
        return new OAuth2RefreshAccessTokenResponse(false);
    }

}

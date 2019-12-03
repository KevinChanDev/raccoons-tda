package com.raccoons.tda.services.auth.model;

import com.raccoons.tda.api.model.UserPrincipal;

public class UserBoundToken {

    private OAuth2AccessTokenResponse oAuth2AccessTokenResponse;
    private UserPrincipal userPrincipal;

    public UserBoundToken(OAuth2AccessTokenResponse oAuth2AccessTokenResponse, UserPrincipal userPrincipal) {
        this.oAuth2AccessTokenResponse = oAuth2AccessTokenResponse;
        this.userPrincipal = userPrincipal;
    }

    public OAuth2AccessTokenResponse getOAuth2AccessTokenResponse() {
        return oAuth2AccessTokenResponse;
    }

    public UserPrincipal getUserPrincipal() {
        return userPrincipal;
    }
}

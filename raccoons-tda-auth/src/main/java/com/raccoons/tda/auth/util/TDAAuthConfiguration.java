package com.raccoons.tda.auth.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TDAAuthConfiguration {

    @Value("${tda.auth.client.id}")
    private String clientId;

    @Value("${tda.auth.redirect.uri}")
    private String redirectUri;

    @Value("${tda.auth.endpoint.token}")
    private String tokenEndpoint;

    @Value("${tda.auth.endpoint.auth}")
    private String authEndpoint;

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public String getAuthEndpoint() {
        return authEndpoint;
    }
}

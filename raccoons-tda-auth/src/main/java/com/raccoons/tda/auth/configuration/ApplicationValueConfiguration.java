package com.raccoons.tda.auth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationValueConfiguration {

    @Value("${tda.auth.redirect.uri}")
    private String redirectUri;

    @Value("${tda.auth.client.id}")
    private String clientId;

    @Value("${tda.auth.endpoint.auth}")
    private String endpointAuth;

    @Value("${tda.auth.endpoint.token}")
    private String endpointToken;

    @Value("${tda.auth.redis.uri}")
    private String redisUri;

    @Value("${tda.auth.redis.cluster.uri}")
    private String redisClusterUri;

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public String getEndpointAuth() {
        return endpointAuth;
    }

    public String getEndpointToken() {
        return endpointToken;
    }
}

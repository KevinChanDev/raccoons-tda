package com.raccoons.tda.auth.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "tda.auth.redis")
@Validated
public class RedisProperties {

    private String uri;
    private String clusterUri;
    private long tokenExpiration;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getClusterUri() {
        return clusterUri;
    }

    public void setClusterUri(String clusterUri) {
        this.clusterUri = clusterUri;
    }

    public long getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(long tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

}

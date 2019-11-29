package com.raccoons.tda.services.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.net.TDAHttpResponse;
import com.raccoons.tda.services.auth.model.GrantRequest;
import com.raccoons.tda.services.auth.util.TDAAuthConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.naming.ConfigurationException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class AuthService {

    // Configuration
    @Autowired
    private TDAAuthConfiguration authConfiguration;

    // Services
    @Autowired
    private HttpService httpService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RefreshService refreshService;

    private ObjectMapper objectMapper;

    private static final TypeReference<Map<String, String>> MAP_TYPE_REFERENCE = new TypeReference<Map<String, String>>() {
    };

    @PostConstruct
    public void init() throws ConfigurationException {
        objectMapper = new ObjectMapper();

        if (authConfiguration.getClientId() == null) {
            throw new ConfigurationException("Client ID not provided");
        }

        if (authConfiguration.getRedirectUri() == null) {
            throw new ConfigurationException("Redirect URI not provided");
        }

        if (authConfiguration.getTokenEndpoint() == null) {
            throw new ConfigurationException("Token Endpoint not provided");
        }

        if (authConfiguration.getAuthEndpoint() == null) {
            throw new ConfigurationException("Auth Endpoint not provided");
        }
    }

    public CompletableFuture<Map<String, String>> authorize(String authCode) {
        final String endpoint = authConfiguration.getTokenEndpoint();

        // Json Data
        final String grantType = "authorization_code";
        final String accessType = "offline";

        // Post grant type requests
        final GrantRequest grantRequest = new GrantRequest();

        grantRequest.setGrantType(grantType);
        grantRequest.setCode(authCode);
        grantRequest.setAccessType(accessType);
        grantRequest.setClientId(authConfiguration.getClientId());
        grantRequest.setRedirectUri(authConfiguration.getRedirectUri());

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        uriComponentsBuilder.queryParam("grant_type", "authorization_code");
        uriComponentsBuilder.queryParam("access_type", "offline");
        uriComponentsBuilder.queryParam("code", authCode);
        uriComponentsBuilder.queryParam("client_id", authConfiguration.getClientId());
        uriComponentsBuilder.queryParam("redirect_uri", authConfiguration.getRedirectUri());

        final String queryString = uriComponentsBuilder.build().encode().toUriString().substring(1);

        System.out.println(queryString);

        final Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/x-www-form-urlencoded");

        return httpService.post(endpoint, headers, queryString.getBytes()).thenApply(tdaHttpResponse -> {
            final String data = new String(tdaHttpResponse.getBody());
            try {
                return objectMapper.readValue(data, MAP_TYPE_REFERENCE);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new HashMap<>();
            }
        });
    }

}

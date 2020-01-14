package com.raccoons.tda.services.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.client.AccountContext;
import com.raccoons.tda.services.auth.model.OAuth2AccessTokenResponse;
import com.raccoons.tda.services.auth.model.UserBoundToken;
import com.raccoons.tda.services.auth.util.TDAAuthConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.naming.ConfigurationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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

    @Autowired
    private TDAClientService tdaClientService;

    private ObjectMapper objectMapper;

    private static final TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
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

    public CompletableFuture<OAuth2AccessTokenResponse> authorize(String authCode) {
        final String endpoint = authConfiguration.getTokenEndpoint();

        // Form Data
        final Map<String, Object> formData = new HashMap<>();

        formData.put("grant_type", "authorization_code");
        formData.put("access_type", "offline");
        formData.put("code", authCode);
        formData.put("client_id", authConfiguration.getClientId());
        formData.put("redirect_uri", authConfiguration.getRedirectUri());

        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        return httpService.post(endpoint, headers, formData).thenApply(tdaHttpResponse -> {
            final String data = new String(tdaHttpResponse.getBody());
            try {
                return OAuth2AccessTokenResponse.of(objectMapper.readValue(data, MAP_TYPE_REFERENCE));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return OAuth2AccessTokenResponse.failed();
            }
        });
    }

    public CompletableFuture<UserBoundToken> processUserOAuthToken(OAuth2AccessTokenResponse response) {
        if (response != null && response.isValid()) {
            final String accessToken = response.getAccessToken();
            final AccountContext accountContext = new AccountContext(accessToken);
            return tdaClientService.getUserInfoClient().getUserPrincipals(accountContext, "preferences", "surrogateIds")
                    .thenApply(userPrincipleResponse -> new UserBoundToken(response, userPrincipleResponse.getData()));
        }
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<String> storeResponse(UserBoundToken userBoundToken) {
        return null;
    }

    public CompletableFuture<String> userResponse() {
        return null;
    }

}

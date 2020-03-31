package com.raccoons.tda.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.client.AccountContext;
import com.raccoons.tda.api.client.UserInfoClient;
import com.raccoons.tda.auth.component.TDAClients;
import com.raccoons.tda.auth.model.OAuth2AccessTokenResponse;
import com.raccoons.tda.auth.model.OAuth2RefreshAccessTokenResponse;
import com.raccoons.tda.auth.model.UserBoundToken;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.util.Digest;
import com.raccoons.tda.auth.configuration.TDAAuthConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.naming.ConfigurationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthClientService {

    private static final Logger logger = LogManager.getLogger(AuthClientService.class);

    private static final int OK = 200;
    private static final String[] USER_PRINCIPLE_FIELDS = {"preferences", "surrogateIds"};

    @Autowired
    private TDAAuthConfiguration authConfiguration;

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private TDAClients tdaClients;

    @Autowired
    private ObjectMapper objectMapper;

    private static final TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };

    @PostConstruct
    public void init() throws ConfigurationException {
        logger.trace("Initializing AuthService instance.");
        if (authConfiguration.getClientId() == null) {
            throw new ConfigurationException("Failed to initialize AuthService: Client ID not provided.");
        }

        if (authConfiguration.getRedirectUri() == null) {
            throw new ConfigurationException("Failed to initialize AuthService: Redirect URI not provided.");
        }

        if (authConfiguration.getTokenEndpoint() == null) {
            throw new ConfigurationException("Failed to initialize AuthService: Token Endpoint not provided.");
        }

        if (authConfiguration.getAuthEndpoint() == null) {
            throw new ConfigurationException("Failed to initialize AuthService: Auth Endpoint not provided.");
        }
        logger.trace("Initialized AuthService instance.");
    }

    /**
     * @param code
     * @return
     */
    public CompletableFuture<OAuth2AccessTokenResponse> authorize(final String code) {
        if (code == null) {
            logger.info("Unable to perform access token request, the provided authorization code is null.");
            return CompletableFuture.completedFuture(OAuth2AccessTokenResponse.failed());
        }

        if (logger.isInfoEnabled()) {
            final String codeSignature = Digest.sha256Hex(code);
            logger.info("Authorizing with Auth Code Signature: {}", codeSignature);
        }

        final String endpoint = authConfiguration.getTokenEndpoint();

        // Form Data
        final Map<String, Object> formData = new HashMap<>();

        formData.put("grant_type", "authorization_code");
        formData.put("access_type", "offline");
        formData.put("code", code);
        formData.put("client_id", authConfiguration.getClientId());
        formData.put("redirect_uri", authConfiguration.getRedirectUri());

        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        logger.trace("Authorizing request headers: {}, form data: {}", headers, formData);
        return httpClientService.post(endpoint, headers, formData).thenApply(tdaHttpResponse -> {
            final int statusCode = tdaHttpResponse.getStatusCode();
            if (statusCode == OK) {
                final String data = new String(tdaHttpResponse.getBody());
                try {
                    final Map<String, Object> values = objectMapper.readValue(data, MAP_TYPE_REFERENCE);

                    if (logger.isTraceEnabled()) {
                        logger.trace("Response from access token operation: " + values);
                    }

                    final OAuth2AccessTokenResponse r = OAuth2AccessTokenResponse.of(values);

                    if (r.isValid()){
                        logger.trace("OAuth2AccessTokenResponse created successfully.");
                    } else {
                        logger.trace("OAuth2AccessTokenResponse was not created.");
                    }

                    return r;
                } catch (JsonProcessingException e) {
                    logger.error("Error occurred while trying to create a OAuth2AccessTokenResponse.", e);
                }
            }
            return OAuth2AccessTokenResponse.failed();
        });
    }

    /**
     * @param accessToken
     * @return Returns an OAuth2RefreshAccessTokenResponse
     */
    public CompletableFuture<OAuth2RefreshAccessTokenResponse> refreshAccessToken(final AccessToken accessToken) {
        if (accessToken == null) {
            logger.info("Unable to refresh access token, the provided token is null.");
            return CompletableFuture.completedFuture(OAuth2RefreshAccessTokenResponse.failed());
        }

        logger.info("Request to refresh access token initialized.");
        final String endpoint = authConfiguration.getTokenEndpoint();
        final String refreshToken = accessToken.getRefreshToken();

        if (refreshToken != null) {
            final Map<String, Object> formData = new HashMap<>();
            formData.put("grant_type", "refresh_token");
            formData.put("refresh_token", refreshToken);
            formData.put("client_id", authConfiguration.getClientId());

            final Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");

            return httpClientService.post(endpoint, headers, formData).thenApply(tdaHttpResponse -> {
                final int statusCode = tdaHttpResponse.getStatusCode();
                if (statusCode == OK) {
                    final String data = new String(tdaHttpResponse.getBody());

                    try {
                        final Map<String, Object> values = objectMapper.readValue(data, MAP_TYPE_REFERENCE);

                        if (logger.isTraceEnabled()) {
                            logger.trace("Response from refresh operation: " + values);
                        }

                        final OAuth2RefreshAccessTokenResponse r = OAuth2RefreshAccessTokenResponse.of(values);

                        if (r.isValid()){
                            logger.trace("OAuth2RefreshAccessTokenResponse created successfully.");
                        } else {
                            logger.trace("OAuth2RefreshAccessTokenResponse was not created.");
                        }

                        return r;
                    } catch (JsonProcessingException e) {
                        logger.error("Error occurred while trying to create a OAuth2RefreshAccessTokenResponse.", e);
                    }
                }
                return OAuth2RefreshAccessTokenResponse.failed();
            });
        } else {
            logger.info("Unable to refresh access token, provided refresh token was null.");
        }
        return CompletableFuture.completedFuture(OAuth2RefreshAccessTokenResponse.failed());
    }

    /**
     * This method will return a CompletableFuture provided a valid OAuth2AccessTokenResponse object
     *
     * @param response An OAuth2AccessTokenResponse instance
     * @return Returns a UserBoundToken if the response was valid and the operation was successful otherwise null.
     */
    public CompletableFuture<UserBoundToken> processUserOAuthToken(final OAuth2AccessTokenResponse response) {
        if (response != null && response.isValid()) {
            logger.info("The OAuth2AccessToken is VALID. Attempting to create a UserBoundToken.");

            final String accessToken = response.getAccessToken();
            final AccountContext accountContext = new AccountContext(accessToken);
            final UserInfoClient userInfoClient = tdaClients.getUserInfoClient();

            return userInfoClient.getUserPrincipals(accountContext, USER_PRINCIPLE_FIELDS)
                    .thenApply(userPrincipleResponse -> new UserBoundToken(response, userPrincipleResponse.getData()));
        } else {
            logger.info("The OAuth2AccessToken is INVALID. Returning a null UserBoundToken.");
            return CompletableFuture.completedFuture(null);
        }
    }

}

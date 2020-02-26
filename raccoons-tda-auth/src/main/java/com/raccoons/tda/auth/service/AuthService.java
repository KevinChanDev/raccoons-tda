package com.raccoons.tda.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.client.AccountContext;
import com.raccoons.tda.api.client.UserInfoClient;
import com.raccoons.tda.auth.model.OAuth2AccessTokenResponse;
import com.raccoons.tda.auth.model.UserBoundToken;
import com.raccoons.tda.auth.util.Digest;
import com.raccoons.tda.auth.util.TDAAuthConfiguration;
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
public class AuthService {

    private static final Logger logger = LogManager.getLogger(AuthService.class);

    private static final String[] USER_PRINCIPLE_FIELDS = {"preferences", "surrogateIds"};

    @Autowired
    private TDAAuthConfiguration authConfiguration;

    @Autowired
    private HttpService httpService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RefreshService refreshService;

    @Autowired
    private TDAClientService tdaClientService;

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

    public CompletableFuture<OAuth2AccessTokenResponse> authorize(final String code) {
        if (logger.isInfoEnabled()){
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

        return httpService.post(endpoint, headers, formData).thenApply(tdaHttpResponse -> {
            final String data = new String(tdaHttpResponse.getBody());
            try {
                return OAuth2AccessTokenResponse.of(objectMapper.readValue(data, MAP_TYPE_REFERENCE));
            } catch (JsonProcessingException e) {
                logger.error("Error occurred while trying to create a OAuth2AccessToken.", e);
                return OAuth2AccessTokenResponse.failed();
            }
        });
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
            final UserInfoClient userInfoClient = tdaClientService.getUserInfoClient();

            return userInfoClient.getUserPrincipals(accountContext, USER_PRINCIPLE_FIELDS)
                    .thenApply(userPrincipleResponse -> new UserBoundToken(response, userPrincipleResponse.getData()));
        } else {
            logger.info("The OAuth2AccessToken is INVALID. Returning a null UserBoundToken.");
            return CompletableFuture.completedFuture(null);
        }
    }

    public CompletableFuture<UserBoundToken> storeUserBoundToken(final UserBoundToken userBoundToken) {
        return null;
    }

    public CompletableFuture<String> userResponse() {
        return null;
    }

}

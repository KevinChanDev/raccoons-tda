package com.raccoons.tda.auth.controller;

import com.raccoons.tda.api.model.UserPrincipal;
import com.raccoons.tda.auth.component.RequestId;
import com.raccoons.tda.auth.model.OAuth2AccessTokenResponse;
import com.raccoons.tda.auth.service.token.AccessTokenService;
import com.raccoons.tda.auth.service.AuthClientService;
import com.raccoons.tda.auth.util.Digest;
import com.raccoons.tda.auth.util.RequestUserInfo;
import com.raccoons.tda.auth.configuration.TDAAuthConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@Controller
public class OAuthController {

    private static final Logger logger = LogManager.getLogger(OAuthController.class);
    private static final int FAILED_STATUS = 400;

    @Autowired
    private AuthClientService authClientService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private TDAAuthConfiguration tdaAuthConfiguration;

    @Autowired
    private RequestId requestId;

    @RequestMapping(value = "/${tda.auth.mapping.login}", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<Object>> login(final HttpServletRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            if (logger.isInfoEnabled()) {
                final String remoteAddress = RequestUserInfo.getRequestIpAddress(request);
                final String userAgent = RequestUserInfo.getUserAgent(request);
                logger.info("{} Login request made \"{}\"", remoteAddress, userAgent);
            }

            final UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(tdaAuthConfiguration.getAuthEndpoint()).encode();

            componentsBuilder.queryParam("response_type", "code");
            componentsBuilder.queryParam("client_id", tdaAuthConfiguration.getClientId() + "@AMER.OAUTHAP");
            componentsBuilder.queryParam("redirect_uri", tdaAuthConfiguration.getRedirectUri());

            final HttpHeaders headers = new HttpHeaders();
            final String locationURI = componentsBuilder.build().encode().toUriString();
            headers.add("Location", locationURI);

            if (logger.isInfoEnabled()) {
                final String remoteAddress = RequestUserInfo.getRequestIpAddress(request);
                final String userAgent = RequestUserInfo.getUserAgent(request);
                logger.info("{} Redirecting to \"{}\" \"{}\".", remoteAddress, locationURI, userAgent);
            }
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        });
    }

    @RequestMapping(value = "/${tda.auth.mapping.callback}", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<Object>> callback(@RequestParam String code, final HttpServletRequest request) {
        if (logger.isInfoEnabled()) {
            final String remoteAddress = request.getRemoteAddr();
            final String userAgent = RequestUserInfo.getUserAgent(request);
            final String codeSignature = Digest.sha256Hex(code);
            logger.info("{} Callback with code signature \"{}\" \"{}\".", remoteAddress, codeSignature, userAgent);
        }

        if (code != null && !code.isEmpty()) {
            return authClientService.authorize(code).thenCompose(r -> {
                if (logger.isInfoEnabled()) {
                    final String accessTokenSignature = Digest.sha256Hex(r.getAccessToken());
                    logger.info("Access Token Signature: {}", accessTokenSignature);
                }
                return authClientService.processUserOAuthToken(r);
            }).thenCompose(userBoundToken -> {
                if (userBoundToken != null) {
                    final UserPrincipal userPrincipal = userBoundToken.getUserPrincipal();
                    final OAuth2AccessTokenResponse response = userBoundToken.getOAuth2AccessTokenResponse();

                    final String userId = userPrincipal.getUserId();
                    final String primaryAccountId = userPrincipal.getPrimaryAccountId();
                    final String accessTokenSignature = Digest.sha256Hex(response.getAccessToken());

                    logger.info("User registered: {}, Account Id: {}, Access Token Signature: {}.",
                            userId, primaryAccountId, accessTokenSignature);

                    return accessTokenService.storeUserBoundToken(requestId.getId(), userBoundToken)
                            .thenApply(b -> b ? ResponseEntity.ok().build() : ResponseEntity.status(FAILED_STATUS).build());
                } else {
                    return CompletableFuture.completedFuture(ResponseEntity.status(FAILED_STATUS).build());
                }
            });
        }
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok().build());
    }
}

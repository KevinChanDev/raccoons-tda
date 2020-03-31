package com.raccoons.tda.auth.controller;

import com.raccoons.auth.lib.message.ServiceMessage;
import com.raccoons.tda.auth.service.token.AccessTokenDetailService;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.model.token.SecureAccessToken;
import com.raccoons.tda.auth.repository.SecureAccessTokenRepository;
import com.raccoons.tda.auth.service.security.AccessTokenSecurityService;
import com.raccoons.tda.auth.service.token.AccessTokenService;
import com.raccoons.tda.auth.service.AuthClientService;
import com.raccoons.tda.auth.service.data.AccessTokenRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class AccessTokenController {

    @Autowired
    private AuthClientService authClientService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private SecureAccessTokenRepository secureAccessTokenRepository;

    @Autowired
    private AccessTokenSecurityService accessTokenSecurityService;

    @Autowired
    private AccessTokenRedisService accessTokenRedisService;

    @Autowired
    private AccessTokenDetailService accessTokenDetailService;

    @ResponseBody
    @RequestMapping(value = "/access_token", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<AccessToken>>> accessToken(@RequestHeader Map<String, String> headers) {
        return CompletableFuture.supplyAsync(() -> {
            List<SecureAccessToken> secureAccessTokenList = secureAccessTokenRepository.findAll();
            List<AccessToken> accessTokens = secureAccessTokenList.stream().map(new Function<SecureAccessToken, AccessToken>() {
                @Override
                public AccessToken apply(SecureAccessToken secureAccessToken) {
                    return accessTokenSecurityService.decrypt(secureAccessToken);
                }
            }).collect(Collectors.toList());
            return new ResponseEntity<>(accessTokens, HttpStatus.OK);
        });
    }

    @ResponseBody
    @RequestMapping(value = "/access_token_redis", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<AccessToken>> accessTokenRedis(@RequestHeader Map<String, String> headers) {
        return accessTokenRedisService.getAccessToken("kevinwchan2").thenApply(new Function<Optional<AccessToken>, ResponseEntity<AccessToken>>() {
            @Override
            public ResponseEntity<AccessToken> apply(Optional<AccessToken> accessToken) {
                return accessToken.map(ResponseEntity::ok).orElse(null);
            }
        });
    }

    @ResponseBody
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<ServiceMessage>> refresh(@RequestParam String owner) {
        if (owner == null || owner.isEmpty()) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(null));
        }
        return accessTokenService.refreshAccessTokenByOwner(-1, owner).thenApply(accessToken -> {

            if (accessToken.isPresent()) {
                final AccessToken a = accessToken.get();
                final ServiceMessage.Builder builder = ServiceMessage.newBuilder();

                final Map<String, Object> payload = new HashMap<>();
                payload.put("owner", a.getOwner());
                payload.put("access_token", a.getAccessToken());

                builder.payload(payload);

                final ServiceMessage serviceMessage = builder.build();
                return ResponseEntity.ok(serviceMessage);
            } else {
                return ResponseEntity.of(Optional.empty());
            }
        });
    }

}

package com.raccoons.tda.services.auth.controller;

import com.raccoons.tda.services.auth.service.AuthService;
import com.raccoons.tda.services.auth.util.TDAAuthConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Controller
public class OAuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TDAAuthConfiguration tdaAuthConfiguration;

    @RequestMapping(value = "/${tda.auth.mapping.login}", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<Object>> login() {
        return CompletableFuture.supplyAsync(() -> {
            final UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(tdaAuthConfiguration.getAuthEndpoint()).encode();

            componentsBuilder.queryParam("response_type", "code");
            componentsBuilder.queryParam("client_id", tdaAuthConfiguration.getClientId() + "@AMER.OAUTHAP");
            componentsBuilder.queryParam("redirect_uri", tdaAuthConfiguration.getRedirectUri());

            final HttpHeaders headers = new HttpHeaders();
            headers.add("Location", componentsBuilder.build().encode().toUriString());
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        });
    }

    @RequestMapping(value = "/${tda.auth.mapping.callback}", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<Object>> callback(@RequestParam String code) {
        return CompletableFuture.supplyAsync(() -> {
            if (code != null) {
                authService.authorize(code).thenApply(new Function<Map<String, String>, Object>() {
                    @Override
                    public Object apply(Map<String, String> stringStringMap) {
                        System.out.println("response: " + stringStringMap);
                        return "returned";
                    }
                });
            }
            return ResponseEntity.badRequest().build();
        });
    }
}

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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;

@Controller
public class OAuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TDAAuthConfiguration tdaAuthConfiguration;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<Object>> login() {
        return CompletableFuture.supplyAsync(() -> {
            final UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(tdaAuthConfiguration.getAuthEndpoint());
            final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

            params.add("client_id", tdaAuthConfiguration.getClientId());
            params.add("redirect_uri", tdaAuthConfiguration.getRedirectUri());
            params.add("response_type", "code");

            componentsBuilder.queryParams(params);
            final HttpHeaders headers = new HttpHeaders();
            headers.add("Location", componentsBuilder.toUriString());
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        });
    }

}

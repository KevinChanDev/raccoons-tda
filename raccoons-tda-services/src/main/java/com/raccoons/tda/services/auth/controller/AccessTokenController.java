package com.raccoons.tda.services.auth.controller;

import com.raccoons.tda.services.auth.model.AccessTokenResponse;
import com.raccoons.tda.services.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
public class AccessTokenController {

    @Autowired
    private AuthService authService;

    @ResponseBody
    @RequestMapping(value = "/access_token", method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<AccessTokenResponse>> accessToken(@RequestHeader Map<String, String> headers) {
        return CompletableFuture.supplyAsync(() -> {
            final AccessTokenResponse response = new AccessTokenResponse(new char[0]);
            return new ResponseEntity<>(response, HttpStatus.OK);
        });
    }

}

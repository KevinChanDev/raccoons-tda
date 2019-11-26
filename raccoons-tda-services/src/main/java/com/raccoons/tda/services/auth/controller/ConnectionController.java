package com.raccoons.tda.services.auth.controller;

import com.raccoons.tda.services.auth.model.ConnectionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Controller
public class ConnectionController {

    @ResponseBody
    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<ConnectionResponse>> connect(){
        return CompletableFuture.supplyAsync(new Supplier<ResponseEntity<ConnectionResponse>>() {
            @Override
            public ResponseEntity<ConnectionResponse> get() {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        });
    }
}

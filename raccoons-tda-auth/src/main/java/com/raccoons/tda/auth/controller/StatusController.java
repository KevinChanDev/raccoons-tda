package com.raccoons.tda.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
public class StatusController {

    @ResponseBody
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<Object>> status(@RequestHeader Map<String, String> headers) {
        return CompletableFuture.supplyAsync(() -> {
            return new ResponseEntity<>(null, HttpStatus.OK);
        });
    }

}

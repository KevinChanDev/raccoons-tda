package com.raccoons.tda.services.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Service
public class AccessTokenService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SecurityService securityService;

    public CompletableFuture<String> getAccessToken(String owner) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        });
    }

    public CompletableFuture<String> storeAccessToken(String owner, String value) {
        final String key = "";
        final String encryptedValue = "";
        return redisService.storeToken(key, encryptedValue);
    }

    public CompletableFuture<String> storeAccessToken(String owner, String value, long expiration) {
        final String key = "";
        final String encryptedValue = "";
        return redisService.storeToken(key, encryptedValue, expiration);
    }

    public CompletableFuture<String> deleteAccessToken(String owner) {
        return null;
    }

    public CompletableFuture<String> getRefreshToken(String owner) {
        return null;
    }

    public CompletableFuture<String> storeRefreshToken(String owner, String value) {
        final String key = "";
        final String encryptedValue = "";
        return redisService.storeToken(key, encryptedValue);
    }

    public CompletableFuture<String> storeRefreshToken(String owner, String value, long expiration) {
        final String key = "";
        final String encryptedValue = "";
        return redisService.storeToken(key, encryptedValue, expiration);
    }

    public CompletableFuture<String> deleteRefreshToken(String owner) {
        return null;
    }

}

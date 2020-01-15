package com.raccoons.tda.auth.service;

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
        final String key = owner;
        final String encryptedValue = value;
        return redisService.storeToken(key, encryptedValue);
    }

    public CompletableFuture<String> storeAccessToken(String owner, String value, long expiration) {
        final String key = owner;
        final String encryptedValue = value;
        return redisService.storeToken(key, encryptedValue, expiration);
    }

    public CompletableFuture<Boolean> deleteAccessToken(String owner) {
        final String key = owner;
        return redisService.deleteToken(key).thenApply(l -> l > 0);
    }

    public CompletableFuture<String> getRefreshToken(String owner) {
        return redisService.getAccessToken(owner);
    }

    public CompletableFuture<String> storeRefreshToken(String owner, String value) {
        final String key = owner;
        final String encryptedValue = value;
        return redisService.storeToken(key, encryptedValue);
    }

    public CompletableFuture<String> storeRefreshToken(String owner, String value, long expiration) {
        final String key = owner;
        final String encryptedValue = value;
        return redisService.storeToken(key, encryptedValue, expiration);
    }

    public CompletableFuture<Boolean> deleteRefreshToken(String owner) {
        final String key = owner;
        return redisService.deleteToken(key).thenApply(l -> l > 0);
    }

}

package com.raccoons.tda.services.auth.service;

import com.raccoons.tda.services.auth.util.Values;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.async.RedisKeyAsyncCommands;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;

@Service
public class RedisService {

    private RedisStringAsyncCommands<String, String> stringCommands;
    private RedisKeyAsyncCommands<String, String> keyCommands;

    @Value("${tda.auth.redis.uri}")
    private String redisUri;

    @Value("${tda.auth.redis.cluster.uri}")
    private String redisClusterUri;

    @Value("${tda.auth.redis.token.expiration}")
    private long tokenExpiration;

    public RedisService() {
    }

    @PostConstruct
    private void init() {
        if (redisClusterUri != null) {
            final RedisClusterClient redisClusterClient = RedisClusterClient.create(redisClusterUri);
            final RedisAdvancedClusterAsyncCommands<String, String> commands = redisClusterClient.connect().async();
            stringCommands = commands;
            keyCommands = commands;
        }

        if (redisUri != null && stringCommands == null) {
            final RedisClient client = RedisClient.create(redisUri);
            final RedisAsyncCommands<String, String> commands = client.connect().async();
            stringCommands = commands;
            keyCommands = commands;
        }
    }

    public CompletableFuture<String> getAccessToken(String key) {
        return stringCommands.get(Values.AUTH_PREFIX + key).toCompletableFuture();
    }

    public CompletableFuture<String> storeToken(String key, String value) {
        return stringCommands.psetex(key, tokenExpiration, value).toCompletableFuture();
    }

    public CompletableFuture<String> storeToken(String key, String value, long expiration) {
        return stringCommands.psetex(key, expiration, value).toCompletableFuture();
    }

    public CompletableFuture<Long> deleteToken(String key) {
        return keyCommands.del(key).toCompletableFuture();
    }
}

package com.raccoons.tda.services.auth.service;

import com.raccoons.tda.services.auth.util.Values;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;

@Service
public class RedisService {

    private RedisStringAsyncCommands<String, String> stringCommands;

    @Value("${tda.auth.redis.uri}")
    private String redisUri;

    @Value("${tda.auth.redis.cluster.uri}")
    private String redisClusterUri;

    public RedisService() {
    }

    @PostConstruct
    private void init() {
        if (redisClusterUri != null){
            final RedisClusterClient redisClusterClient = RedisClusterClient.create(redisClusterUri);
            stringCommands = redisClusterClient.connect().async();
        }

        if (redisUri != null && stringCommands == null){
            final RedisClient client = RedisClient.create(redisUri);
            stringCommands = client.connect().async();
        }
    }

    public CompletableFuture<String> getAccessToken(String key) {
        return stringCommands.get(Values.AUTH_PREFIX + key).toCompletableFuture();
    }

    public CompletableFuture<String> storeAccessToken(String key, String value, long expiration) {
        return stringCommands.psetex(key, expiration, value).toCompletableFuture();
    }

}

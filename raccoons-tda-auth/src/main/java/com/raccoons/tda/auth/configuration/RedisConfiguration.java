package com.raccoons.tda.auth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConfiguration {

    @Value("${tda.auth.redis.uri}")
    private String redisUri;

    @Value("${tda.auth.redis.cluster.uri}")
    private String redisClusterUri;

    public String getRedisUri() {
        return redisUri;
    }

    public String getRedisClusterUri() {
        return redisClusterUri;
    }
}

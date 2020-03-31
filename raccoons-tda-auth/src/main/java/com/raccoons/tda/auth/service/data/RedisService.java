package com.raccoons.tda.auth.service.data;

import com.raccoons.tda.auth.configuration.RedisConfiguration;
import com.raccoons.tda.auth.util.Values;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.async.RedisKeyAsyncCommands;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.naming.ConfigurationException;
import java.util.concurrent.CompletableFuture;

@Service
public class RedisService {

    private static final Logger logger = LogManager.getLogger(RedisService.class);

    private RedisStringAsyncCommands<String, String> stringCommands;
    private RedisKeyAsyncCommands<String, String> keyCommands;

    @Autowired
    private RedisConfiguration redisConfiguration;

    @PostConstruct
    private void init() throws ConfigurationException {
        final String redisClusterUri = redisConfiguration.getRedisClusterUri();
        if (redisClusterUri != null) {
            try {
                logger.info("Redis Cluster URI provided, connecting...");
                final RedisClusterClient redisClusterClient = RedisClusterClient.create(redisClusterUri);
                final RedisAdvancedClusterAsyncCommands<String, String> commands = redisClusterClient.connect().async();
                logger.info("Successfully connected to redis cluster.");
                stringCommands = commands;
                keyCommands = commands;
            } catch (Exception e) {
                logger.error("Error occurred while trying to connect to redis cluster.");
            }
        }

        final String redisUri = redisConfiguration.getRedisUri();
        if (redisUri != null && stringCommands == null) {
            try {
                logger.info("Redis URI provided, connecting...");
                final RedisClient client = RedisClient.create(redisUri);
                final RedisAsyncCommands<String, String> commands = client.connect().async();
                logger.info("Successfully connected to redis.");
                stringCommands = commands;
                keyCommands = commands;
            } catch (Exception e) {
                logger.error("Error occurred while trying to connect to redis.");
            }
        }

        if (stringCommands == null) {
            throw new ConfigurationException("Unable to connect to redis.");
        }
    }

    public CompletableFuture<String> getValue(final String key) {
        final String redisKey = Values.AUTH_PREFIX + key;
        logger.info("Getting value for {}", redisKey);
        return stringCommands.get(redisKey).toCompletableFuture();
    }

    public CompletableFuture<String> setValue(final String key, final String value) {
        final String redisKey = Values.AUTH_PREFIX + key;
        logger.info("Setting value for {}", redisKey);
        return stringCommands.set(redisKey, value).toCompletableFuture();
    }

    public CompletableFuture<String> setValue(final String key, final String value, final long expiration) {
        final String redisKey = Values.AUTH_PREFIX + key;
        logger.info("Setting value for {} with expiration {}", redisKey, expiration);
        return stringCommands.psetex(redisKey, expiration, value).toCompletableFuture();
    }

    public CompletableFuture<Long> deleteValue(final String key) {
        final String redisKey = Values.AUTH_PREFIX + key;
        logger.info("Deleting value from {}", redisKey);
        return keyCommands.del(redisKey).toCompletableFuture();
    }
}

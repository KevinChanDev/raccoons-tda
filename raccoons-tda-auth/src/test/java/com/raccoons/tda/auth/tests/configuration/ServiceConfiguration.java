package com.raccoons.tda.auth.tests.configuration;

import com.raccoons.tda.auth.service.RedisService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ServiceConfiguration {

    @Bean
    public RedisService redisServiceMock() {
        return Mockito.mock(RedisService.class);
    }

}

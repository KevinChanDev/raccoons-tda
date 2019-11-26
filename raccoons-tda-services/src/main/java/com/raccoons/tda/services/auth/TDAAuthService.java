package com.raccoons.tda.services.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class TDAAuthService {

    public static void main(String[] args) {
        SpringApplication.run(TDAAuthService.class, args);
    }

}

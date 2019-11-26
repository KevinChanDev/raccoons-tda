package com.raccoons.tda.services.auth.service;

import com.raccoons.tda.services.auth.util.TDAAuthConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AuthService {

    // Configuration
    @Autowired
    private TDAAuthConfiguration authConfiguration;

    // Services
    @Autowired
    private HttpService httpService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RefreshService refreshService;

    @PostConstruct
    public void init() {
        System.out.println("Instantiated Auth Service!!");

        if (authConfiguration.getClientId() == null) {

        }

        if (authConfiguration.getRedirectUri() == null) {

        }

        if (authConfiguration.getTokenEndpoint() == null) {

        }

        if (authConfiguration.getAuthEndpoint() == null) {

        }
    }

}

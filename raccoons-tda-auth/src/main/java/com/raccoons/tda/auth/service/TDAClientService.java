package com.raccoons.tda.auth.service;

import com.raccoons.tda.api.client.TDAClient;
import com.raccoons.tda.api.client.UserInfoClient;
import com.raccoons.tda.auth.model.UserBoundToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TDAClientService {

    private static final Logger logger = LogManager.getLogger(UserBoundToken.class);

    @Autowired
    private HttpService httpService;

    @Autowired
    private TDAClient tdaClient;

    private UserInfoClient userInfoClient;

    @PostConstruct
    private void init() {
        logger.trace("Initializing TDAClientService instance.");
        userInfoClient = tdaClient.getUserInfoClient();
    }

    public TDAClient getTDAClient() {
        return tdaClient;
    }

    public UserInfoClient getUserInfoClient() {
        return userInfoClient;
    }
}

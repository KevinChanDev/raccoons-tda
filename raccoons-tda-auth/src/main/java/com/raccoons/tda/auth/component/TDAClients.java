package com.raccoons.tda.auth.component;

import com.raccoons.tda.api.client.TDAClient;
import com.raccoons.tda.api.client.UserInfoClient;
import com.raccoons.tda.auth.service.HttpClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TDAClients {

    private static final Logger logger = LogManager.getLogger(TDAClients.class);

    @Autowired
    private HttpClientService httpClientService;

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

package com.raccoons.tda.services.auth.service;

import com.raccoons.tda.api.client.RequestClient;
import com.raccoons.tda.api.client.TDAClient;
import com.raccoons.tda.api.client.UserInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TDAClientService {

    @Autowired
    private HttpService httpService;

    private RequestClient requestClient;
    private UserInfoClient userInfoClient;

    @PostConstruct
    private void init() {
        requestClient = new RequestClient(httpService.getTdaHttpClient());
        final TDAClient tdaClient = new TDAClient(requestClient);
        userInfoClient = tdaClient.userInfoClient();
    }

    public RequestClient getRequestClient() {
        return requestClient;
    }

    public UserInfoClient getUserInfoClient() {
        return userInfoClient;
    }
}

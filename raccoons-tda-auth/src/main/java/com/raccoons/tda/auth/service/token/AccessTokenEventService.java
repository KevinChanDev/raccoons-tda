package com.raccoons.tda.auth.service.token;

import com.raccoons.tda.auth.client.ClientSession;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.service.client.AccessTokenExchangeService;
import com.raccoons.tda.auth.service.subscription.AccessTokenSubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AccessTokenEventService {

    private static final Logger logger = LogManager.getLogger(AccessTokenEventService.class);

    @Autowired
    private AccessTokenSubscriptionService accessTokenSubscriptionService;

    @Autowired
    private AccessTokenExchangeService accessTokenExchangeService;

    @PostConstruct
    public void init() {
    }

    public void onAccessTokenUpdated(final AccessToken accessToken) {
        if (accessToken != null) {
            final String owner = accessToken.getOwner();
            final ClientSession[] subscribers = accessTokenSubscriptionService.getSubscribers(owner);

            for (ClientSession subscription : subscribers) {
                accessTokenExchangeService.sendAccessTokenMessage(subscription, owner, accessToken);
            }
        }
    }

}

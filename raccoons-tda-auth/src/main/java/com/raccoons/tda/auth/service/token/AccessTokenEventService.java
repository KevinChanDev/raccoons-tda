package com.raccoons.tda.auth.service.token;

import com.raccoons.tda.auth.client.ClientSession;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.service.AccessTokenRefreshingService;
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

    @Autowired
    private AccessTokenRefreshingService accessTokenRefreshingService;

    @Autowired
    private AccessTokenDetailService accessTokenDetailService;

    @PostConstruct
    public void init() {
    }

    public void onAccessTokenUpdated(final long requestId, final AccessToken accessToken) {
        if (accessToken != null) {

            final String owner = accessToken.getOwner();
            final long expiresIn = accessToken.getAccessTokenExpiration();

            logger.trace("[{}] Expiration event started for {}", requestId, owner);

            if (expiresIn > 0) {
                accessTokenRefreshingService.registerRefresh(requestId, owner, expiresIn);
                logger.trace("[{}] Registered event for {}", requestId, owner);
            }

            // Broadcast
            broadcastRefreshToSubscribers(requestId, accessToken, owner);
        }
    }

    private void broadcastRefreshToSubscribers(final long requestId, final AccessToken accessToken, final String owner) {
        logger.trace("[{}] Broadcasting refresh for {} to client subscribers.", requestId, owner);

        if (accessToken != null && owner != null) {
            final ClientSession[] subscribers = accessTokenSubscriptionService.getSubscribers(owner);

            for (ClientSession subscription : subscribers) {
                accessTokenExchangeService.sendAccessTokenMessage(subscription, owner, accessToken);
            }
        }

    }

}

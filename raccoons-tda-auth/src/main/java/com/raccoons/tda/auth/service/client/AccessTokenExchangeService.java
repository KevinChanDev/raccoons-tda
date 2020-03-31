package com.raccoons.tda.auth.service.client;

import com.raccoons.auth.lib.ServiceMessageType;
import com.raccoons.auth.lib.message.ServiceMessage;
import com.raccoons.tda.auth.action.request.AccessTokenServiceHandler;
import com.raccoons.tda.auth.client.ClientSession;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.service.subscription.AccessTokenSubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AccessTokenExchangeService {

    private static final Logger logger = LogManager.getLogger(AccessTokenServiceHandler.class);

    @Autowired
    private AccessTokenSubscriptionService accessTokenSubscriptionService;

    @Autowired
    private ClientMessageExchangeService clientMessageExchangeService;

    public CompletableFuture<String> sendAccessTokenMessage(final String sessionId, final String owner,
                                                            final AccessToken accessToken) {
        if (sessionId != null && accessToken != null) {
            logger.trace("Access token was located for owner {}.", owner);

            final Map<String, Object> messagePayload = new HashMap<>();

            messagePayload.put("owner", owner);
            messagePayload.put("access_token", accessToken.getAccessToken());
            messagePayload.put("access_token_expiration", accessToken.getAccessTokenExpiration());

            final ServiceMessage serviceMessage = ServiceMessage.newBuilder()
                    .messageType(ServiceMessageType.ACCESS_TOKEN)
                    .payload(messagePayload)
                    .build();

            logger.trace("Sending service message to {} for access_token service.", sessionId);
            return clientMessageExchangeService.sendMessage(sessionId, serviceMessage);
        }
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<String> sendAccessTokenMessage(final ClientSession clientSession, final String owner,
                                                            final AccessToken accessToken) {
        if (clientSession != null && accessToken != null) {
            logger.trace("Access token was located for owner {}.", owner);

            final Map<String, Object> messagePayload = new HashMap<>();

            messagePayload.put("owner", owner);
            messagePayload.put("access_token", accessToken.getAccessToken());
            messagePayload.put("access_token_expiration", accessToken.getAccessTokenExpiration());

            final ServiceMessage serviceMessage = ServiceMessage.newBuilder()
                    .messageType(ServiceMessageType.ACCESS_TOKEN)
                    .payload(messagePayload)
                    .build();

            logger.trace("Sending service message to {} for access_token service.", clientSession.getSessionId());
            return clientMessageExchangeService.sendMessage(clientSession, serviceMessage);
        }
        return CompletableFuture.completedFuture(null);
    }
}

package com.raccoons.tda.auth.action.request;

import com.raccoons.auth.lib.ServiceMessageType;
import com.raccoons.auth.lib.ServiceRequest;
import com.raccoons.auth.lib.message.ServiceMessage;
import com.raccoons.tda.auth.client.ClientSession;
import com.raccoons.tda.auth.component.RequestId;
import com.raccoons.tda.auth.model.request.RegisteredServiceRequest;
import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.service.token.AccessTokenService;
import com.raccoons.tda.auth.service.client.ClientMessageExchangeService;
import com.raccoons.tda.auth.service.client.ClientSessionService;
import com.raccoons.tda.auth.service.subscription.AccessTokenSubscriptionService;
import com.raccoons.tda.auth.value.ServiceRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public class AccessTokenServiceHandler implements ServiceRequestHandler {

    private static final Logger logger = LogManager.getLogger(AccessTokenServiceHandler.class);

    @Autowired
    private AccessTokenSubscriptionService accessTokenSubscriptionService;

    @Autowired
    private ClientSessionService clientSessionService;

    @Autowired
    private ClientMessageExchangeService clientMessageExchangeService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private RequestId requestId;

    @Override
    public void handle(RegisteredServiceRequest registeredServiceRequest) {
        if (registeredServiceRequest != null) {
            final long id = registeredServiceRequest.getId();
            final String requesterId = registeredServiceRequest.getRequesterId();

            final ServiceRequest serviceRequest = registeredServiceRequest.getServiceRequest();
            final Map<String, Object> payload = serviceRequest.getPayload();

            final String type = (String) payload.get(ServiceRequests.TYPE);

            if (type != null) {
                logger.info("Handling 'access_token' service request {} from {} of type {}.", id, requesterId, type);
                if (type.equals("request")) {
                    handleRequest(registeredServiceRequest.getRequesterId(), payload);
                } else if (type.equals("refresh")) {
                    handleRefresh(id, registeredServiceRequest.getRequesterId(), payload);
                } else if (type.equals("revoke")) {

                }
            } else {
                logger.trace("Handling 'access_token' service request {} from {} has an invalid type {}.", id, requesterId, null);
            }
        } else {
            logger.trace("RegisteredServiceRequest was null");
        }
    }

    private void handleRequest(final String requesterId, final Map<String, Object> payload) {
        final Optional<ClientSession> clientSessionOptional = clientSessionService.getClientSession(requesterId);

        if (clientSessionOptional.isPresent()) {
            final String owner = (String) payload.get("owner");

            if (owner != null) {
                final ClientSession clientSession = clientSessionOptional.get();
                accessTokenSubscriptionService.subscribe(clientSession, owner);

                accessTokenService.fetchAccessToken(requestId.getId(), owner).thenCompose(o -> {
                    if (o.isPresent()) {
                        logger.trace("Access token was located for owner {}.", owner);
                        final AccessToken accessToken = o.get();
                        return sendAccessTokenMessage(requesterId, owner, accessToken);
                    } else {
                        logger.trace("Access token was NOT located for owner {}.", owner);
                        return CompletableFuture.completedFuture(null);
                    }
                }).thenAccept(s -> {
                    if (s == null) {
                        logger.trace("No access token was found for requested owner. Service message was not sent to {}.", requesterId);
                    } else {
                        logger.trace("Service message was successfully sent to {} for access_token request.", requesterId);
                    }
                });
            }
        } else {
            logger.trace("Client session {} could not be found.", requesterId);
        }
    }

    private void handleRefresh(final long id, final String requesterId, final Map<String, Object> payload) {
        final Optional<ClientSession> clientSessionOptional = clientSessionService.getClientSession(requesterId);
        if (clientSessionOptional.isPresent()) {
            final String owner = (String) payload.get("owner");

            if (owner != null) {
                accessTokenService.refreshAccessTokenByOwner(requestId.getId(), owner).thenAccept(accessToken -> {
                    logger.trace("[{}] Access token refresh request from {} completed.", id, requesterId);
                });
//
//                accessTokenService.refreshAccessTokenByOwner(requestId.getId(), owner).thenCompose(accessToken -> {
//                    if (accessToken.isPresent()) {
//                        logger.trace("Access token provided after refresh operation. Sending service message to {}.", requesterId);
//                        return sendAccessTokenMessage(requesterId, owner, accessToken.get());
//                    } else {
//                        logger.trace("Access token was NOT provided after refresh operation. Service message was not sent to {}.", requesterId);
//                        return CompletableFuture.completedFuture(null);
//                    }
//                });
            }
        }
    }

    private void handleRevoke(final String requesterId, final Map<String, Object> payload) {
        final Optional<ClientSession> clientSessionOptional = clientSessionService.getClientSession(requesterId);
        if (clientSessionOptional.isPresent()) {
            final String owner = (String) payload.get("owner");

            if (owner != null) {
                final ClientSession clientSession = clientSessionOptional.get();
                accessTokenService.refreshAccessTokenByOwner(requestId.getId(), owner);

                final Map<String, Object> revokePayload = new HashMap<>();

                revokePayload.put("owner", "");

                final ServiceMessage serviceMessage = ServiceMessage.newBuilder()
                        .messageType(ServiceMessageType.ACCESS_TOKEN)
                        .payload(revokePayload)
                        .build();
            }
        }
    }

    private CompletableFuture<String> sendAccessTokenMessage(final String requesterId, final String owner,
                                                             final AccessToken accessToken) {
        if (requesterId != null && accessToken != null) {
            logger.trace("Access token was located for owner {}.", owner);

            final Map<String, Object> messagePayload = new HashMap<>();

            messagePayload.put("owner", owner);
            messagePayload.put("access_token", accessToken.getAccessToken());
            messagePayload.put("access_token_expiration", accessToken.getAccessTokenExpiration());

            final ServiceMessage serviceMessage = ServiceMessage.newBuilder()
                    .messageType(ServiceMessageType.ACCESS_TOKEN)
                    .payload(messagePayload)
                    .build();

            logger.trace("Sending service message to {} for access_token service.", requesterId);
            return clientMessageExchangeService.sendMessage(requesterId, serviceMessage);
        }
        return CompletableFuture.completedFuture(null);
    }
}


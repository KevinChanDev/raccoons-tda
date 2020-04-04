package com.raccoons.tda.auth.service.client;

import com.raccoons.auth.lib.ServiceRequest;
import com.raccoons.auth.lib.message.ServiceMessage;
import com.raccoons.tda.auth.client.ClientSession;
import com.raccoons.tda.auth.component.ServiceMessageSerializer;
import com.raccoons.tda.auth.model.request.RegisteredServiceRequest;
import com.raccoons.tda.auth.service.request.RequestQueueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClientMessageExchangeService {

    private static final Logger logger = LogManager.getLogger(ServiceMessageSerializer.class);

    @Autowired
    private ServiceMessageSerializer serviceMessageSerializer;

    @Autowired
    private ClientSessionService clientSessionService;

    @Autowired
    private RequestQueueService requestQueueService;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private AtomicLong requestIdCounter;

    @PostConstruct
    public void init() {
        requestIdCounter = new AtomicLong();
    }

    public void messageReceived(final WebSocketSession webSocketSession, final TextMessage textMessage) {
        if (webSocketSession != null && webSocketSession.isOpen() && textMessage.getPayloadLength() > 0) {
            final String sessionId = webSocketSession.getId();
            final String message = textMessage.getPayload();

            final Optional<ServiceRequest> serviceRequest = serviceMessageSerializer.readRequest(message);

            if (serviceRequest.isPresent()) {
                final ServiceRequest s = serviceRequest.get();
                final long requestId = requestIdCounter.incrementAndGet();
                requestQueueService.enqueue(new RegisteredServiceRequest(requestId, sessionId, s));
            } else {
                logger.trace("Could not serialize request from {}.", sessionId);
            }
        }
    }

    @Async
    public CompletableFuture<String> sendMessage(final ClientSession clientSession, final ServiceMessage serviceMessage) {
        if (clientSession == null) {
            logger.trace("Could not send message {}, the ClientSession is null.", serviceMessage.getId());
            return CompletableFuture.completedFuture(null);
        }

        if (clientSession.isConnected()) {
            final Optional<String> text = serviceMessageSerializer.writeMessage(serviceMessage);
            if (text.isPresent()) {
                return CompletableFuture.supplyAsync(() -> {
                    try {
                        clientSession.getWebSocketSession().sendMessage(new TextMessage(text.get()));
                        return "OK";
                    } catch (Exception e) {
                        logger.error(e);
                        return null;
                    }
                });
            } else {
                logger.trace("Could not send message to {}. No string message could be determined from the ServiceMessage.",
                        clientSession.getSessionId());
            }
        } else {
            logger.trace("Could not send message to {}. The ClientSession is null or not connected.",
                    clientSession.getSessionId());
        }
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<String> sendMessage(final String sessionId, final ServiceMessage serviceMessage) {
        if (sessionId == null) {
            logger.trace("Could not send message, sessionId is null.");
        }

        final Optional<ClientSession> clientSession = clientSessionService.getClientSession(sessionId);

        if (clientSession.isPresent()) {
            logger.trace("Client session for {} found, sending message.", sessionId);
            return sendMessage(clientSession.get(), serviceMessage);
        } else {
            logger.trace("Could not send message to {}. The ClientSession was not found.", sessionId);
            return CompletableFuture.completedFuture(null);
        }
    }
}

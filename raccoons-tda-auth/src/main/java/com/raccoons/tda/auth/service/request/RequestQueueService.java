package com.raccoons.tda.auth.service.request;

import com.raccoons.tda.auth.model.request.RegisteredServiceRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class RequestQueueService {

    private static final Logger logger = LogManager.getLogger(RequestQueueService.class);

    private Queue<RegisteredServiceRequest> registeredRequests;

    @Autowired
    private RequestProcessingService requestProcessingService;

    @PostConstruct
    public void init() {
        registeredRequests = new LinkedList<>();
    }

    public boolean isEmpty() {
        return registeredRequests.isEmpty();
    }

    public void enqueue(final RegisteredServiceRequest request) {
        logger.trace("Queueing request {} from {}.", request.getId(), request.getRequesterId());
        registeredRequests.offer(request);
        requestProcessingService.processRequest(request);
    }

    public RegisteredServiceRequest take() throws InterruptedException {
        final RegisteredServiceRequest request = registeredRequests.poll();
        if (request != null) {
            logger.trace("Taking request {}.", request.getId());
        }
        return request;
    }

}

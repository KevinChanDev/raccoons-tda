package com.raccoons.tda.auth.service.request;

import com.raccoons.auth.lib.ServiceRequest;
import com.raccoons.tda.auth.action.request.AccessTokenServiceHandler;
import com.raccoons.tda.auth.model.request.RegisteredServiceRequest;
import com.raccoons.tda.auth.value.ServiceRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutorService;

@Service
public class RequestProcessingService {

    private static final Logger logger = LogManager.getLogger(RequestProcessingService.class);

    @Autowired
    private RequestQueueService requestQueueService;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private AccessTokenServiceHandler accessTokenServiceHandler;

    /*
    private void process() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (!requestQueueService.isEmpty()) {
                    try {
                        final RegisteredServiceRequest registeredServiceRequest = requestQueueService.take();

                    } catch (Exception e) {
                    }
                }
            }
        });
    }
    */

    @Async
    public void processRequest(final RegisteredServiceRequest registeredServiceRequest) {
        logger.info("Processing request!");

        final long id = registeredServiceRequest.getId();
        final String requesterId = registeredServiceRequest.getRequesterId();
        final ServiceRequest serviceRequest = registeredServiceRequest.getServiceRequest();
        final Map<String, Object> payload = serviceRequest.getPayload();

        final String service = (String) payload.get(ServiceRequests.SERVICE);

        if (service != null) {
            if (service.equals("access_token")){
                accessTokenServiceHandler.handle(registeredServiceRequest);
            }
        }
    }
}

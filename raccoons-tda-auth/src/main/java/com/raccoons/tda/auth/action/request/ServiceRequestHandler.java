package com.raccoons.tda.auth.action.request;

import com.raccoons.tda.auth.model.request.RegisteredServiceRequest;

@FunctionalInterface
public interface ServiceRequestHandler {

    void handle(final RegisteredServiceRequest registeredServiceRequest);

}

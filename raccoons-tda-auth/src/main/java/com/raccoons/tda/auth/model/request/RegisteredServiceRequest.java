package com.raccoons.tda.auth.model.request;

import com.raccoons.auth.lib.ServiceRequest;

public class RegisteredServiceRequest {

    private final long id;
    private final String requesterId;
    private final ServiceRequest serviceRequest;

    public RegisteredServiceRequest(final long id, final String requesterId, final ServiceRequest serviceRequest) {
        this.id = id;
        this.requesterId = requesterId;
        this.serviceRequest = serviceRequest;
    }

    public long getId() {
        return id;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }
}

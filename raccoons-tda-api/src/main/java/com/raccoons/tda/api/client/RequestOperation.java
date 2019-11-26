package com.raccoons.tda.api.client;

public class RequestOperation {

    private RequestClient requestClient;

    public RequestOperation(RequestClient requestClient) {
        this.requestClient = requestClient;
    }

    public RequestClient getRequestClient() {
        return requestClient;
    }
}

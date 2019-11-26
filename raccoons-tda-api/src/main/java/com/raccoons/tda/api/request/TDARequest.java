package com.raccoons.tda.api.request;

public class TDARequest {

    private String requestId;
    private ResponseType responseType;

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getRequestId() {
        return requestId;
    }
}

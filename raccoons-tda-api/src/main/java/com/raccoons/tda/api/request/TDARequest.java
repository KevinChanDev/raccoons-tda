package com.raccoons.tda.api.request;

public class TDARequest {

    private String requestId;
    private TDAResponseType TDAResponseType;

    public TDAResponseType getTDAResponseType() {
        return TDAResponseType;
    }

    public String getRequestId() {
        return requestId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {



    }

}

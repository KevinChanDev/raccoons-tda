package com.raccoons.tda.api.response;

import com.raccoons.tda.api.request.ResponseType;
import com.raccoons.tda.api.request.TDARequest;
import com.raccoons.tda.net.TDAHttpResponse;

public class TDAResponse<T> {

    private TDARequest tdaRequest;
    private TDAHttpResponse tdaHttpResponse;

    private String requestId;
    private ResponseType responseType;
    private TDAResponseStatus responseStatus;
    private long startTime;
    private long endTime;
    private T data;

    public TDAResponse(TDAResponseStatus responseStatus, T data) {
        this.responseStatus = responseStatus;
        this.data = data;
    }

    public TDAResponse(ResponseType responseType, long startTime, long endTime, T data) {
        this.responseType = responseType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public TDAResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public T getData() {
        return data;
    }

}

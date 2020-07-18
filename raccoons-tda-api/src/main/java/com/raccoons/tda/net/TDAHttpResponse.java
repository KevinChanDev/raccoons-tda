package com.raccoons.tda.net;

import java.util.Map;

public class TDAHttpResponse {

    private final State state;
    private final int statusCode;
    private final Map<String, String> headers;
    private final byte[] body;
    private final long startTime;
    private final long endTime;
    private final long requestId;

    public TDAHttpResponse(State state, int statusCode, Map<String, String> headers, byte[] body, long requestId,
                           long startTime, long endTime) {
        this.state = state;
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
        this.requestId = requestId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getDuration() {
        return endTime - startTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }

    public long getRequestId() {
        return requestId;
    }

    public State getState() {
        return state;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public enum State {
        COMPLETED,
        TIMED_OUT,
        ERROR
    }

}

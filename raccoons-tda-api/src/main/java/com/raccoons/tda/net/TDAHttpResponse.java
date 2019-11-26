package com.raccoons.tda.net;

import java.util.Map;

public class TDAHttpResponse {

    private final int status;
    private final Map<String, String> headers;
    private final byte[] body;
    private final long startTime;
    private final long endTime;

    public TDAHttpResponse(int status, Map<String, String> headers, byte[] body, long startTime, long endTime) {
        this.status = status;
        this.headers = headers;
        this.body = body;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getDuration() {
        return endTime - startTime;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }
}

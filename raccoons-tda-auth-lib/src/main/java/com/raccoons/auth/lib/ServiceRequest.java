package com.raccoons.auth.lib;

import java.util.HashMap;
import java.util.Map;

public class ServiceRequest {

    private long id;
    private Map<String, Object> payload;

    private ServiceRequest() {
    }

    private ServiceRequest(long id, Map<String, Object> payload) {
        this.id = id;
        this.payload = payload;
    }

    public long getId() {
        return id;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public static ServiceRequest refresh(final long id, final String owner) {
        final Map<String, Object> payload = new HashMap<>();
        payload.put("owner", owner);
        payload.put("type", "refresh");
        payload.put("service", "access_token");
        return new ServiceRequest(id, payload);
    }

    public static ServiceRequest request(final long id, final String owner) {
        final Map<String, Object> payload = new HashMap<>();
        payload.put("owner", owner);
        payload.put("type", "request");
        payload.put("service", "access_token");
        return new ServiceRequest(id, payload);
    }

}

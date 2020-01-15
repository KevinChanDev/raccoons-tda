package com.raccoons.auth.lib;

public class AccessTokenRequest {

    private long id;
    private int requestType;
    private String owner;

    public AccessTokenRequest(long id, int requestType, String owner) {
        this.id = id;
        this.requestType = requestType;
        this.owner = owner;
    }

    public AccessTokenRequest(int requestType, String owner) {
        this.id = MessageCounter.MESSAGE_COUNTER.incrementAndGet();
        this.requestType = requestType;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public int getRequestType() {
        return requestType;
    }

    public String getOwner() {
        return owner;
    }

    public static AccessTokenRequest refresh(final String owner) {
        return new AccessTokenRequest(AccessTokenRequestType.REFRESH_TOKEN, owner);
    }

    public static AccessTokenRequest request(final String owner) {
        return new AccessTokenRequest(AccessTokenRequestType.REQUEST_TOKEN, owner);
    }

    public static AccessTokenRequest setRefreshThreshold(final String owner) {
        return new AccessTokenRequest(AccessTokenRequestType.SET_REFRESH_THRESHOLD, owner);
    }

}

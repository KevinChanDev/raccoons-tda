package com.raccoons.tda.api.request;

import com.raccoons.tda.api.value.Status;

public class OrderPositionRequest extends TDARequest {

    private String accountId;
    private int maxResults;
    private String fromEnteredTime;
    private String toEnteredTime;
    private Status status;

    public OrderPositionRequest() {
    }

    public OrderPositionRequest(String accountId, int maxResults, String fromEnteredTime, String toEnteredTime, Status status) {
        this.accountId = accountId;
        this.maxResults = maxResults;
        this.fromEnteredTime = fromEnteredTime;
        this.toEnteredTime = toEnteredTime;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public String getFromEnteredTime() {
        return fromEnteredTime;
    }

    public String getToEnteredTime() {
        return toEnteredTime;
    }

    public Status getStatus() {
        return status;
    }
}

package com.raccoons.tda.api.request;

public class CancelOrderRequest extends TDARequest {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }
}

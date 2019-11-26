package com.raccoons.tda.api.response;

import com.raccoons.tda.api.request.ResponseType;

public class OrderResponse extends TDAResponse<Void> {

    public OrderResponse(TDAResponseStatus responseStatus, Void data) {
        super(responseStatus, data);
    }

    public OrderResponse(ResponseType responseType, long startTime, long endTime, Void data) {
        super(responseType, startTime, endTime, data);
    }
}

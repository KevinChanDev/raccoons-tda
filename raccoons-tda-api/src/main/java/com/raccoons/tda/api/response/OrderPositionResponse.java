package com.raccoons.tda.api.response;

import com.raccoons.tda.api.model.OrderPosition;
import com.raccoons.tda.api.request.ResponseType;

public class OrderPositionResponse extends TDAResponse<OrderPosition> {

    public OrderPositionResponse(TDAResponseStatus responseStatus, OrderPosition data) {
        super(responseStatus, data);
    }

    public OrderPositionResponse(ResponseType responseType, long startTime, long endTime, OrderPosition data) {
        super(responseType, startTime, endTime, data);
    }
}

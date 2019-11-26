package com.raccoons.tda.api.request;

import com.raccoons.tda.api.value.*;


public class OrderRequest extends TDARequest {

    private Double quantity;
    private Double price;
    private Duration duration;
    private OrderType orderType;
    private ComplexOrderStrategy complexOrderStrategyType;
    private RequestedDestination requestedDestination;
    private OrderStrategyType orderStrategy;

}

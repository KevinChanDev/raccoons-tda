package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.model.order.Order;

public class SavedOrder extends Order {

    @JsonProperty("savedTime")
    private String savedTime;

    @JsonProperty("savedOrderId")
    private long savedOrderId;

    public String getSavedTime() {
        return savedTime;
    }

    public long getSavedOrderId() {
        return savedOrderId;
    }

}

package com.raccoons.tda.api.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecutionLeg {

    @JsonProperty("legId")
    private long legId;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("mismarkedQuantity")
    private Double mismarkedQuantity;

    @JsonProperty("quantity")
    private Double quantity;

    @JsonProperty("time")
    private String time;

    public long getLegId() {
        return legId;
    }

    public Double getPrice() {
        return price;
    }

    public Double getMismarkedQuantity() {
        return mismarkedQuantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getTime() {
        return time;
    }
}

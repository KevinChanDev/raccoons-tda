package com.raccoons.tda.api.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Execution extends OrderActivity {

    @JsonProperty("activityType")
    private ActivityType activityType;

    @JsonProperty("executionType")
    private ExecutionType executionType;

    @JsonProperty("quantity")
    private Double quantity;

    @JsonProperty("orderRemainingQuantity")
    private Double orderRemainingQuantity;

    @JsonProperty("executionLegs")
    private List<ExecutionLeg> executionLegs;

    public ActivityType getActivityType() {
        return activityType;
    }

    public ExecutionType getExecutionType() {
        return executionType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getOrderRemainingQuantity() {
        return orderRemainingQuantity;
    }

    public List<ExecutionLeg> getExecutionLegs() {
        return executionLegs;
    }

    public enum ActivityType {
        EXECUTION,
        ORDER_ACTION
    }

    public enum ExecutionType {
        FILL
    }

}

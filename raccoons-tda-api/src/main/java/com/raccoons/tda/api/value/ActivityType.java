package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum  ActivityType {

    @JsonProperty("EXECUTION")
    EXECUTION,

    @JsonProperty("ORDER_ACTION")
    ORDER_ACTION

}

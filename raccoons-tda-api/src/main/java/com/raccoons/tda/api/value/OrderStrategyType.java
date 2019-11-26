package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderStrategyType {

    @JsonProperty("SINGLE")
    SINGLE,

    @JsonProperty("OCO")
    OCO,

    @JsonProperty("TRIGGER")
    TRIGGER

}

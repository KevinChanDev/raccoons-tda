package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PriceLinkType {

    @JsonProperty("VALUE")
    VALUE,

    @JsonProperty("PERCENT")
    PERCENT,

    @JsonProperty("TICK")
    TICK
}

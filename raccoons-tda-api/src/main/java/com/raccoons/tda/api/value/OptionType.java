package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OptionType {

    @JsonProperty("VANILLA")
    VANILLA,

    @JsonProperty("BINARY")
    BINARY,

    @JsonProperty("BARRIER")
    BARRIER

}

package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum  PositionEffect {

    @JsonProperty("OPENING")
    OPENING,

    @JsonProperty("CLOSING")
    CLOSING,

    @JsonProperty("AUTOMATIC")
    AUTOMATIC

}

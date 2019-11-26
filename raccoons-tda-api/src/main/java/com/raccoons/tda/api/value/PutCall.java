package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PutCall {

    @JsonProperty("PUT")
    PUT,

    @JsonProperty("CALL")
    CALL

}

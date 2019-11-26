package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Duration {

    @JsonProperty("DAY")
    DAY,

    @JsonProperty("GOOD_TILL_CANCEL")
    GOOD_TILL_CANCEL,

    @JsonProperty("FILL_OR_KILL")
    FILL_OR_KILL
}

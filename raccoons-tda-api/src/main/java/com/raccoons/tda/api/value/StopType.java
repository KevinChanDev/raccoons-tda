package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StopType {

    @JsonProperty("STANDARD")
    STANDARD,

    @JsonProperty("BID")
    BID,

    @JsonProperty("ASK")
    ASK,

    @JsonProperty("LAST")
    LAST,

    @JsonProperty("MARK")
    MARK
}

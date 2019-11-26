package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PriceLinkBasis {

    @JsonProperty("MANUAL")
    MANUAL,

    @JsonProperty("BASE")
    BASE,

    @JsonProperty("TRIGGER")
    TRIGGER,

    @JsonProperty("LAST")
    LAST,

    @JsonProperty("BID")
    BID,

    @JsonProperty("ASK")
    ASK,

    @JsonProperty("ASK_BID")
    ASK_BID,

    @JsonProperty("MARK")
    MARK,

    @JsonProperty("AVERAGE")
    AVERAGE
}

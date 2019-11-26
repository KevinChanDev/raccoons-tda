package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestedDestination {

    @JsonProperty("INET")
    INET,

    @JsonProperty("ECN_ARCA")
    ECN_ARCA,

    @JsonProperty("CBOE")
    CBOE,

    @JsonProperty("AMEX")
    AMEX,

    @JsonProperty("PHLX")
    PHLX,

    @JsonProperty("ISE")
    ISE,

    @JsonProperty("BOX")
    BOX,

    @JsonProperty("NYSE")
    NYSE,

    @JsonProperty("NASDAQ")
    NASDAQ,

    @JsonProperty("BATS")
    BATS,

    @JsonProperty("C2")
    C2,

    @JsonProperty("AUTO")
    AUTO
}

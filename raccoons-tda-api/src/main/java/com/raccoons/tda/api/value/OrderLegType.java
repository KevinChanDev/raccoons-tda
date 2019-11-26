package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderLegType {

    @JsonProperty("EQUITY")
    EQUITY,

    @JsonProperty("OPTION")
    OPTION,

    @JsonProperty("INDEX")
    INDEX,

    @JsonProperty("MUTUAL_FUND")
    MUTUAL_FUND,

    @JsonProperty("CASH_EQUIVALENT")
    CASH_EQUIVALENT,

    @JsonProperty("FIXED_INCOME")
    FIXED_INCOME,

    @JsonProperty("CURRENCY")
    CURRENCY

}

package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AssetType {

    @JsonProperty("EQUITY")
    EQUITY,

    @JsonProperty("ETF")
    ETF,

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
    CURRENCY,

    @JsonProperty("BOND")
    BOND,

    @JsonProperty("FUTURE")
    FUTURE,

    @JsonProperty("FUTURE_OPTION")
    FUTURE_OPTION,

    @JsonProperty("INDICATOR")
    INDICATOR,

    @JsonProperty("UNKNOWN")
    UNKNOWN;

}

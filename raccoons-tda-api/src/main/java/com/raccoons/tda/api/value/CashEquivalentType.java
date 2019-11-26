package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CashEquivalentType {

    @JsonProperty("SAVINGS")
    SAVINGS,

    @JsonProperty("MONEY_MARKET_FUND")
    MONEY_MARKET_FUND

}

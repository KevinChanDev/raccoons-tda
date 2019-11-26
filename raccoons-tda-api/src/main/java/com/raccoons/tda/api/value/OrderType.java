package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderType {

    @JsonProperty("MARKET")
    MARKET,

    @JsonProperty("LIMIT")
    LIMIT,

    @JsonProperty("STOP")
    STOP,

    @JsonProperty("STOP_LIMIT")
    STOP_LIMIT,

    @JsonProperty("TRAILING_STOP")
    TRAILING_STOP,

    @JsonProperty("MARKET_ON_CLOSE")
    MARKET_ON_CLOSE,

    @JsonProperty("EXERCISE")
    EXERCISE,

    @JsonProperty("TRAILING_STOP_LIMIT")
    TRAILING_STOP_LIMIT,

    @JsonProperty("NET_DEBIT")
    NET_DEBIT,

    @JsonProperty("NET_CREDIT")
    NET_CREDIT,

    @JsonProperty("NET_ZERO")
    NET_ZERO
}

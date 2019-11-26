package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Instruction {

    @JsonProperty("BUY")
    BUY,

    @JsonProperty("SELL")
    SELL,

    @JsonProperty("BUY_TO_COVER")
    BUY_TO_COVER,

    @JsonProperty("SELL_SHORT")
    SELL_SHORT,

    @JsonProperty("BUY_TO_OPEN")
    BUY_TO_OPEN,

    @JsonProperty("BUY_TO_CLOSE")
    BUY_TO_CLOSE,

    @JsonProperty("SELL_TO_OPEN")
    SELL_TO_OPEN,

    @JsonProperty("SELL_TO_CLOSE")
    SELL_TO_CLOSE,

    @JsonProperty("EXCHANGE")
    EXCHANGE
}

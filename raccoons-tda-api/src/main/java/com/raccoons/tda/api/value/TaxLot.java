package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaxLot {

    @JsonProperty("FIFO")
    FIFO,

    @JsonProperty("LIFO")
    LIFO,

    @JsonProperty("HIGH_COST")
    HIGH_COST,

    @JsonProperty("LOW_COST")
    LOW_COST,

    @JsonProperty("AVERAGE_COST")
    AVERAGE_COST,

    @JsonProperty("SPECIFIC_LOT")
    SPECIFIC_LOT

}

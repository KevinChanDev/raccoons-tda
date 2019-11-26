package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CurrencyType {

    @JsonProperty("USD")
    USD,

    @JsonProperty("CAD")
    CAD,

    @JsonProperty("EUR")
    EUR,

    @JsonProperty("JPY")
    JPY
}

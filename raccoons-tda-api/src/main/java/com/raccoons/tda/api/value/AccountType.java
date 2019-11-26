package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccountType {

    @JsonProperty("CASH")
    CASH,

    @JsonProperty("MARGIN")
    MARGIN

}

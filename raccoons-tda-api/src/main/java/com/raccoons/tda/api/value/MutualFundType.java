package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MutualFundType {

    @JsonProperty("NOT_APPLICABLE")
    NOT_APPLICABLE,

    @JsonProperty("OPEN_END_NON_TAXABLE")
    OPEN_END_NON_TAXABLE,

    @JsonProperty("OPEN_END_TAXABLE")
    OPEN_END_TAXABLE,

    @JsonProperty("NO_LOAD_NON_TAXABLE")
    NO_LOAD_NON_TAXABLE,

    @JsonProperty("NO_LOAD_TAXABLE")
    NO_LOAD_TAXABLE

}


package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SpecialInstruction {

    @JsonProperty("ALL_OR_NONE")
    ALL_OR_NONE,

    @JsonProperty("DO_NOT_REDUCE")
    DO_NOT_REDUCE,

    @JsonProperty("ALL_OR_NONE_DO_NOT_REDUCE")
    ALL_OR_NONE_DO_NOT_REDUCE

}

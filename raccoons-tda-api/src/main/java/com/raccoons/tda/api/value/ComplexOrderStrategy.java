package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ComplexOrderStrategy {

    @JsonProperty("NONE")
    NONE,

    @JsonProperty("COVERED")
    COVERED,

    @JsonProperty("VERTICAL")
    VERTICAL,

    @JsonProperty("BACK_RATIO")
    BACK_RATIO,

    @JsonProperty("CALENDER")
    CALENDER,

    @JsonProperty("DIAGONAL")
    DIAGONAL,

    @JsonProperty("STRADDLE")
    STRADDLE,

    @JsonProperty("STRANGLE")
    STRANGLE,

    @JsonProperty("COLLAR_SYNTHETIC")
    COLLAR_SYNTHETIC,

    @JsonProperty("BUTTERFLY")
    BUTTERFLY,

    @JsonProperty("CONDOR")
    CONDOR,

    @JsonProperty("IRON_CONDOR")
    IRON_CONDOR,

    @JsonProperty("VERTICAL_ROLL")
    VERTICAL_ROLL,

    @JsonProperty("COLLAR_WITH_STOCK")
    COLLAR_WITH_STOCK,

    @JsonProperty("DOUBLE_DIAGONAL")
    DOUBLE_DIAGONAL,

    @JsonProperty("UNBALANCED_BUTTERFLY")
    UNBALANCED_BUTTERFLY,

    @JsonProperty("UNBALANCED_CONDOR")
    UNBALANCED_CONDOR,

    @JsonProperty("UNBALANCED_IRON_CONDOR")
    UNBALANCED_IRON_CONDOR,

    @JsonProperty("UNBALANCED_VERTICAL_ROLL")
    UNBALANCED_VERTICAL_ROLL,

    @JsonProperty("CUSTOM")
    CUSTOM
}

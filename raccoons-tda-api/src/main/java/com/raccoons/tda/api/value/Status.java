package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {

    @JsonProperty("AWAITING_PARENT_ORDER")
    AWAITING_PARENT_ORDER,

    @JsonProperty("AWAITING_CONDITION")
    AWAITING_CONDITION,

    @JsonProperty("AWAITING_MANUAL_REVIEW")
    AWAITING_MANUAL_REVIEW,

    @JsonProperty("ACCEPTED")
    ACCEPTED,

    @JsonProperty("AWAITING_UR_OUT")
    AWAITING_UR_OUT,

    @JsonProperty("PENDING_ACTIVATION")
    PENDING_ACTIVATION,

    @JsonProperty("QUEUED")
    QUEUED,

    @JsonProperty("WORKING")
    WORKING,

    @JsonProperty("REJECTED")
    REJECTED,

    @JsonProperty("PENDING_CANCEL")
    PENDING_CANCEL,

    @JsonProperty("CANCELED")
    CANCELED,

    @JsonProperty("PENDING_REPLACE")
    PENDING_REPLACE,

    @JsonProperty("REPLACED")
    REPLACED,

    @JsonProperty("FILLED")
    FILLED,

    @JsonProperty("EXPIRED")
    EXPIRED

}

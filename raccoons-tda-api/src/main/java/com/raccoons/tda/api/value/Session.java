package com.raccoons.tda.api.value;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Session {

    @JsonProperty("NORMAL")
    NORMAL,

    @JsonProperty("AM")
    AM,

    @JsonProperty("PM")
    PM,

    @JsonProperty("SEAMLESS")
    SEAMLESS

}

package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelTime {

    @JsonProperty("date")
    private String date;

    @JsonProperty("shortFormat")
    private boolean shortFormat;

    public String getDate() {
        return date;
    }

    public boolean isShortFormat() {
        return shortFormat;
    }
}

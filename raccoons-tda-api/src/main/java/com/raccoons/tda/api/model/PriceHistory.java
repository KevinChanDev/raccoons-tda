package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PriceHistory {

    @JsonProperty("candles")
    private List<Candle> candles;

    @JsonProperty("empty")
    private Boolean empty;

    @JsonProperty("symbol")
    private String symbol;

    public List<Candle> getCandles() {
        return candles;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public String getSymbol() {
        return symbol;
    }
}

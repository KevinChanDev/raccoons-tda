package com.raccoons.tda.api.model;

import java.util.List;

public class PriceHistory {

    private List<Candle> candles;
    private Boolean empty;
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

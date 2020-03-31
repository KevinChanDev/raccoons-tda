package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {

    @JsonProperty("shortQuantity")
    private Double shortQuantity;

    @JsonProperty("averagePrice")
    private Double averagePrice;

    @JsonProperty("currentDayProfitLoss")
    private Double currentDayProfitLoss;

    @JsonProperty("currentDayProfitLossPercentage")
    private Double currentDayProfitLossPercentage;

    @JsonProperty("longQuantity")
    private Double longQuantity;

    @JsonProperty("settledLongQuantity")
    private Double settledLongQuantity;

    @JsonProperty("settledShortQuantity")
    private Double settledShortQuantity;

    @JsonProperty("agedQuantity")
    private Double agedQuantity;

    @JsonProperty("marketValue")
    private Double marketValue;

    @JsonProperty("instrument")
    private Instrument instrument;

    public Double getShortQuantity() {
        return shortQuantity;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public Double getCurrentDayProfitLoss() {
        return currentDayProfitLoss;
    }

    public Double getCurrentDayProfitLossPercentage() {
        return currentDayProfitLossPercentage;
    }

    public Double getLongQuantity() {
        return longQuantity;
    }

    public Double getSettledLongQuantity() {
        return settledLongQuantity;
    }

    public Double getSettledShortQuantity() {
        return settledShortQuantity;
    }

    public Double getAgedQuantity() {
        return agedQuantity;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public Instrument getInstrument() {
        return instrument;
    }

}

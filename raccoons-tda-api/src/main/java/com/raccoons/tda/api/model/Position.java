package com.raccoons.tda.api.model;

public class Position {

    private double shortQuantity;
    private double averagePrice;
    private double currentDayProfitLoss;
    private double currentDayProfitLossPercentage;
    private double longQuantity;
    private double settledLongQuantity;
    private double settledShortQuantity;
    private double agedQuantity;
    private double marketValue;
    private Instrument instrument;

    public double getShortQuantity() {
        return shortQuantity;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public double getCurrentDayProfitLoss() {
        return currentDayProfitLoss;
    }

    public double getCurrentDayProfitLossPercentage() {
        return currentDayProfitLossPercentage;
    }

    public double getLongQuantity() {
        return longQuantity;
    }

    public double getSettledLongQuantity() {
        return settledLongQuantity;
    }

    public double getSettledShortQuantity() {
        return settledShortQuantity;
    }

    public double getAgedQuantity() {
        return agedQuantity;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public Instrument getInstrument() {
        return instrument;
    }
}

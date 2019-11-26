package com.raccoons.tda.api.model;

public class Candle {

    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Long volume;
    private Long datetime;

    public double getClose() {
        return close;
    }

    public long getDatetime() {
        return datetime;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpen() {
        return open;
    }

    public long getVolume() {
        return volume;
    }
}

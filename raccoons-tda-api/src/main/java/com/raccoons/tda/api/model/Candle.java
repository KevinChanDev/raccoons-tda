package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Candle {

    @JsonProperty("open")
    private Double open;

    @JsonProperty("close")
    private Double close;

    @JsonProperty("high")
    private Double high;

    @JsonProperty("low")
    private Double low;

    @JsonProperty("volume")
    private Long volume;

    @JsonProperty("datetime")
    private Long datetime;

    public Double getOpen() {
        return open;
    }

    public Double getClose() {
        return close;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Long getVolume() {
        return volume;
    }

    public Long getDatetime() {
        return datetime;
    }
}

package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class IndexQuote extends Quote {

    public IndexQuote(Map<String, Object> values) {
        super(values);
    }

    public Double getLastPrice() {
        return (Double) getValue(LAST_PRICE);
    }

    public Double getOpenPrice() {
        return (Double) getValue(OPEN_PRICE);
    }

    public Double getHighPrice() {
        return (Double) getValue(HIGH_PRICE);
    }

    public Double getLowPrice() {
        return (Double) getValue(LOW_PRICE);
    }

    public Double getClosePrice() {
        return (Double) getValue(CLOSE_PRICE);
    }

    public Double getNetChange() {
        return (Double) getValue(NET_CHANGE);
    }

    public Double getTotalVolume() {
        return (Double) getValue(TOTAL_VOLUME);
    }

    public Long getTradeTimeInLong() {
        return (Long) getValue(TRADE_TIME_IN_LONG);
    }

    public String getExchange() {
        return (String) getValue(EXCHANGE);
    }

    public Double getDigits() {
        return (Double) getValue(DIGITS);
    }

    public Double get52WeekHigh() {
        return (Double) getValue(_52_WK_HIGH);
    }

    public Double get52WeekLow() {
        return (Double) getValue(_52_WK_LOW);
    }

}

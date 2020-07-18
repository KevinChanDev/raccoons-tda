package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class MutualFundQuote extends Quote {

    public MutualFundQuote(Map<String, Object> values) {
        super(values);
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

    public Double getNav() {
        return (Double) getValue(NAV);
    }

    public Double getPeRatio() {
        return (Double) getValue(PE_RATIO);
    }

    public Double getDivAmount() {
        return (Double) getValue(DIV_AMOUNT);
    }

    public Double getDivYield() {
        return (Double) getValue(DIV_YIELD);
    }

    public String getDivDate() {
        return (String) getValue(DIV_DATE);
    }

}

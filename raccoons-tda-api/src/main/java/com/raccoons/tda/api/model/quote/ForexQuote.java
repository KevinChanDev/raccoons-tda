package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class ForexQuote extends Quote {

    public ForexQuote(Map<String, Object> values) {
        super(values);
    }

    public Double getBidPriceInDouble() {
        return (Double) getValue(BID_PRICE_IN_DOUBLE);
    }

    public Double getAskPriceInDouble() {
        return (Double) getValue(ASK_PRICE_IN_DOUBLE);
    }

    public Double getLastPriceInDouble() {
        return (Double) getValue(LAST_PRICE_IN_DOUBLE);
    }

    public Double getHighPriceInDouble() {
        return (Double) getValue(HIGH_PRICE_IN_DOUBLE);
    }

    public Double getLowPriceInDouble() {
        return (Double) getValue(LOW_PRICE_IN_DOUBLE);
    }

    public Double getClosePriceInDouble() {
        return (Double) getValue(CLOSE_PRICE_IN_DOUBLE);
    }

    public String getExchange() {
        return (String) getValue(EXCHANGE);
    }

    public Double getOpenPriceInDouble() {
        return (Double) getValue(OPEN_PRICE_IN_DOUBLE);
    }

    public Double getChangeInDouble() {
        return (Double) getValue(CHANGE_IN_DOUBLE);
    }

    public Double getPercentChange() {
        return (Double) getValue(PERCENT_CHANGE);
    }

    public Double getDigits() {
        return (Double) getValue(DIGITS);
    }

    public Double getTick() {
        return (Double) getValue(TICK);
    }

    public Double getTickAmount() {
        return (Double) getValue(TICK_AMOUNT);
    }

    public String getProduct() {
        return (String) getValue(PRODUCT);
    }

    public String getTradingHours() {
        return (String) getValue(TRADING_HOURS);
    }

    public boolean isTradable() {
        return (Boolean) getValue(IS_TRADABLE);
    }

    public String getMarketMaker() {
        return (String) getValue(MARKET_MAKER);
    }

    public Double get52WeekHighInDouble() {
        return (Double) getValue(ASK_PRICE_IN_DOUBLE);
    }

    public Double get52WeekLowInDouble() {
        return (Double) getValue(ASK_PRICE_IN_DOUBLE);
    }

    public Double getMark() {
        return (Double) getValue(MARK);
    }

}

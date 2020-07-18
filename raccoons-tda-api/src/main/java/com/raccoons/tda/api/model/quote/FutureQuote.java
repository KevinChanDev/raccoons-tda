package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class FutureQuote extends Quote {

    public FutureQuote(Map<String, Object> values) {
        super(values);
    }

    public String getExchange() {
        return (String) getValue(EXCHANGE);
    }

    public String getBidId() {
        return (String) getValue(BID_ID);
    }

    public String getAskId() {
        return (String) getValue(ASK_ID);
    }

    public String getLastId() {
        return (String) getValue(LAST_ID);
    }

    public Double getFuturePercentChange() {
        return (Double) getValue(FUTURE_PERCENT_CHANGE);
    }

    public Double getOpenInterest() {
        return (Double) getValue(OPEN_INTEREST);
    }

    public String getFuturePriceFormat() {
        return (String) getValue(FUTURE_PRICE_FORMAT);
    }

    public String getFutureTradingHours() {
        return (String) getValue(FUTURE_TRADING_HOURS);
    }

    public Double getFutureIsTradable() {
        return (Double) getValue(FUTURE_IS_TRADABLE);
    }

    public Double getFutureMultiplier() {
        return (Double) getValue(FUTURE_MULTIPLIER);
    }

    public Double getFutureIsActive() {
        return (Double) getValue(FUTURE_IS_ACTIVE);
    }

    public Double getFutureSettlementPrice() {
        return (Double) getValue(FUTURE_SETTLEMENT_PRICE);
    }

    public String getFutureActiveSymbol() {
        return (String) getValue(FUTURE_ACTIVE_SYMBOL);
    }

    public String getFutureExpirationDate() {
        return (String) getValue(FUTURE_EXPIRATION_DATE);
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

    public Double getOpenPriceInDouble() {
        return (Double) getValue(OPEN_PRICE_IN_DOUBLE);
    }

    public Double getChangeInDouble() {
        return (Double) getValue(CHANGE_IN_DOUBLE);
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

    public Double getMark() {
        return (Double) getValue(MARK);
    }

}

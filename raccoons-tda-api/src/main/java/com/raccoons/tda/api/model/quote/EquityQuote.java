package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class EquityQuote extends Quote {

    public EquityQuote(Map<String, Object> values) {
        super(values);
    }

    public Double getBidPrice() {
        return (Double) getValue(BID_PRICE);
    }

    public Double getBidSize() {
        return (Double) getValue(BID_SIZE);
    }

    public String getBidId() {
        return (String) getValue(BID_ID);
    }

    public Double getAskPrice() {
        return (Double) getValue(ASK_PRICE);
    }

    public Double getAskSize() {
        return (Double) getValue(ASK_SIZE);
    }

    public String getAskId() {
        return (String) getValue(ASK_ID);
    }

    public Double getLastPrice() {
        return (Double) getValue(LAST_PRICE);
    }

    public Double getLastSize() {
        return (Double) getValue(LAST_SIZE);
    }

    public String getLastId() {
        return (String) getValue(LAST_ID);
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

    public Long getQuoteTimeInLong() {
        return (Long) getValue(QUOTE_TIME_IN_LONG);
    }

    public Long getTradeTimeInLong() {
        return (Long) getValue(TRADE_TIME_IN_LONG);
    }

    public Double getMark() {
        return (Double) getValue(MARK);
    }

    public String getExchange() {
        return (String) getValue(EXCHANGE);
    }

    public boolean isMarginable() {
        return (Boolean) getValue(MARGINABLE);
    }

    public boolean isShortable() {
        return (Boolean) getValue(SHORTABLE);
    }

    public Double getVolatility() {
        return (Double) getValue(VOLATILITY);
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

    public Double getRegularMarketLastPrice() {
        return (Double) getValue(REGULAR_MARKET_LAST_PRICE);
    }

    public Double getRegularMarketLastSize() {
        return (Double) getValue(REGULAR_MARKET_LAST_SIZE);
    }

    public Double getRegularMarketNetChange() {
        return (Double) getValue(REGULAR_MARKET_NET_CHANGE);
    }

    public Long getRegularMarketTradeTimeInLong() {
        return (Long) getValue(REGULAR_MARKET_TRADE_TIME_IN_LONG);
    }

}

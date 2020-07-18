package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class OptionQuote extends Quote {

    public OptionQuote(Map<String, Object> values) {
        super(values);
    }

    public Double getOpenInterest() {
        return (Double) getValue(OPEN_INTEREST);
    }

    public Double getMoneyIntrinsicValue() {
        return (Double) getValue(MONEY_INTRINSIC_VALUE);
    }

    public Double getMultiplier() {
        return (Double) getValue(MULTIPLIER);
    }

    public Double getStrikePrice() {
        return (Double) getValue(STRIKE_PRICE);
    }

    public String getContractType() {
        return (String) getValue(CONTRACT_TYPE);
    }

    public String getUnderlying() {
        return (String) getValue(UNDERLYING);
    }

    public Double getTimeValue() {
        return (Double) getValue(TIME_VALUE);
    }

    public String getDeliverables() {
        return (String) getValue(DELIVERABLES);
    }

    public Double getDelta() {
        return (Double) getValue(DELTA);
    }

    public Double getGamma() {
        return (Double) getValue(GAMMA);
    }

    public Double getTheta() {
        return (Double) getValue(THETA);
    }

    public Double getVega() {
        return (Double) getValue(VEGA);
    }

    public Double getRho() {
        return (Double) getValue(RHO);
    }

    public Double getTheoreticalOptionValue() {
        return (Double) getValue(THEORETICAL_OPTION_VALUE);
    }

    public Double getUnderlyingPrice() {
        return (Double) getValue(UNDERLYING_PRICE);
    }

    public String getUvExpirationType() {
        return (String) getValue(UV_EXPIRATION_TYPE);
    }

    public String getSettlementType() {
        return (String) getValue(SETTLEMENT_TYPE);
    }

    public Double getBidPrice() {
        return (Double) getValue(BID_PRICE);
    }

    public Double getBidSize() {
        return (Double) getValue(BID_SIZE);
    }

    public Double getAskPrice() {
        return (Double) getValue(ASK_PRICE);
    }

    public Double getAskSize() {
        return (Double) getValue(ASK_SIZE);
    }

    public Double getLastPrice() {
        return (Double) getValue(LAST_PRICE);
    }

    public Double getLastSize() {
        return (Double) getValue(LAST_SIZE);
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

    public Double getVolatility() {
        return (Double) getValue(VOLATILITY);
    }

}

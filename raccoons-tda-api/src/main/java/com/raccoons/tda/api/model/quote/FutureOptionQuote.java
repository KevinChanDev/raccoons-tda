package com.raccoons.tda.api.model.quote;

import javax.management.monitor.GaugeMonitor;
import java.util.Map;

public class FutureOptionQuote extends Quote {

    public FutureOptionQuote(Map<String, Object> values) {
        super(values);
    }

    public Double getNetChangeInDouble() {
        return (Double) getValue(NET_CHANGE_IN_DOUBLE);
    }

    public Double getVolatility() {
        return (Double) getValue(VOLATILITY);
    }

    public Double getMoneyIntrinsicValueInDouble() {
        return (Double) getValue(MONEY_INTRINSIC_VALUE_IN_DOUBLE);
    }

    public Double getMultiplierInDouble() {
        return (Double) getValue(MULTIPLIER_IN_DOUBLE);
    }

    public Double getDigits() {
        return (Double) getValue(DIGITS);
    }

    public Double getStrikePriceInDouble() {
        return (Double) getValue(STRIKE_PRICE_IN_DOUBLE);
    }

    public String getContractType() {
        return (String) getValue(CONTRACT_TYPE);
    }

    public String getUnderlying() {
        return (String) getValue(UNDERLYING);
    }

    public Double getTimeValueInDouble() {
        return (Double) getValue(TIME_VALUE_IN_DOUBLE);
    }

    public Double getDeltaInDouble() {
        return (Double) getValue(DELTA_IN_DOUBLE);
    }

    public Double getGammaInDouble() {
        return (Double) getValue(GAMMA_IN_DOUBLE);
    }

    public Double getThetaInDouble() {
        return (Double) getValue(THETA_IN_DOUBLE);
    }

    public Double getVegaInDouble() {
        return (Double) getValue(VEGA_IN_DOUBLE);
    }

    public Double getRhoInDouble() {
        return (Double) getValue(RHO_IN_DOUBLE);
    }

    public String getExpirationType() {
        return (String) getValue(EXPIRATION_TYPE);
    }

    public String getExerciseType() {
        return (String) getValue(EXERCISE_TYPE);
    }

    public Double getInTheMoney() {
        return (Double) getValue(IN_THE_MONEY);
    }

    public Double getFuturePercentChange() {
        return (Double) getValue(FUTURE_PERCENT_CHANGE);
    }

    public Double getOpenInterest() {
        return (Double) getValue(OPEN_INTEREST);
    }

    public String getFutureTradingHours() {
        return (String) getValue(FUTURE_TRADING_HOURS);
    }

    public Double getFutureIsTradable() {
        return (Double) getValue(FUTURE_IS_TRADABLE);
    }

    public Double getFutureIsActive() {
        return (Double) getValue(FUTURE_IS_ACTIVE);
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

    public Double getTick() {
        return (Double) getValue(TICK);
    }

    public Double getTickAmount() {
        return (Double) getValue(TICK_AMOUNT);
    }

    public Double getMark() {
        return (Double) getValue(MARK);
    }
}

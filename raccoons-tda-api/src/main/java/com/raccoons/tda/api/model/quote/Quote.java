package com.raccoons.tda.api.model.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.model.ApiModel;

import java.util.Map;

public class Quote extends ApiModel {

    public static final String SYMBOL = "symbol";
    public static final String DESCRIPTION = "description";
    public static final String CLOSE_PRICE = "closePrice";
    public static final String NET_CHANGE = "netChange";
    public static final String TOTAL_VOLUME = "totalVolume";
    public static final String TRADE_TIME_IN_LONG = "tradeTimeInLong";
    public static final String EXCHANGE = "exchange";
    public static final String EXCHANGE_NAME = "exchangeName";
    public static final String DIGITS = "digits";
    public static final String _52_WK_HIGH = "52WkHigh";
    public static final String _52_WK_LOW = "52WkLow";
    public static final String NAV = "nAV";
    public static final String PE_RATIO = "peRatio";
    public static final String DIV_AMOUNT = "divAmount";
    public static final String DIV_YIELD = "divYield";
    public static final String DIV_DATE = "divDate";
    public static final String SECURITY_STATUS = "securityStatus";
    public static final String BID_PRICE_IN_DOUBLE = "bidPriceInDouble";
    public static final String ASK_PRICE_IN_DOUBLE = "askPriceInDouble";
    public static final String LAST_PRICE_IN_DOUBLE = "lastPriceInDouble";
    public static final String BID_ID = "bidId";
    public static final String ASK_ID = "askId";
    public static final String HIGH_PRICE_IN_DOUBLE = "highPriceInDouble";
    public static final String LOW_PRICE_IN_DOUBLE = "lowPriceInDouble";
    public static final String CLOSE_PRICE_IN_DOUBLE = "closePriceInDouble";
    public static final String LAST_ID = "lastId";
    public static final String OPEN_PRICE_IN_DOUBLE = "openPriceInDouble";
    public static final String CHANGE_IN_DOUBLE = "changeInDouble";
    public static final String FUTURE_PERCENT_CHANGE = "futurePercentChange";
    public static final String OPEN_INTEREST = "openInterest";
    public static final String MARK = "mark";
    public static final String TICK = "tick";
    public static final String TICK_AMOUNT = "tickAmount";
    public static final String PRODUCT = "product";
    public static final String FUTURE_PRICE_FORMAT = "futurePriceFormat";
    public static final String FUTURE_TRADING_HOURS = "futureTradingHours";
    public static final String FUTURE_IS_TRADABLE = "futureIsTradable";
    public static final String FUTURE_MULTIPLIER = "futureMultiplier";
    public static final String FUTURE_IS_ACTIVE = "futureIsActive";
    public static final String FUTURE_SETTLEMENT_PRICE = "futureSettlementPrice";
    public static final String FUTURE_ACTIVE_SYMBOL = "futureActiveSymbol";
    public static final String FUTURE_EXPIRATION_DATE = "futureExpirationDate";
    public static final String NET_CHANGE_IN_DOUBLE = "netChangeInDouble";
    public static final String VOLATILITY = "volatility";
    public static final String MONEY_INTRINSIC_VALUE_IN_DOUBLE = "moneyIntrinsicValueInDouble";
    public static final String MULTIPLIER_IN_DOUBLE = "multiplierInDouble";
    public static final String STRIKE_PRICE_IN_DOUBLE = "strikePriceInDouble";
    public static final String CONTRACT_TYPE = "contractType";
    public static final String UNDERLYING = "underlying";
    public static final String TIME_VALUE_IN_DOUBLE = "timeValueInDouble";
    public static final String DELTA_IN_DOUBLE = "deltaInDouble";
    public static final String GAMMA_IN_DOUBLE = "gammaInDouble";
    public static final String THETA_IN_DOUBLE = "thetaInDouble";
    public static final String VEGA_IN_DOUBLE = "vegaInDouble";
    public static final String RHO_IN_DOUBLE = "rhoInDouble";
    public static final String EXPIRATION_TYPE = "expirationType";
    public static final String EXERCISE_TYPE = "exerciseType";
    public static final String IN_THE_MONEY = "inTheMoney";
    public static final String LAST_PRICE = "lastPrice";
    public static final String OPEN_PRICE = "openPrice";
    public static final String HIGH_PRICE = "highPrice";
    public static final String LOW_PRICE = "lowPrice";
    public static final String BID_PRICE = "bidPrice";
    public static final String BID_SIZE = "bidSize";
    public static final String ASK_PRICE = "askPrice";
    public static final String ASK_SIZE = "askSize";
    public static final String LAST_SIZE = "lastSize";
    public static final String QUOTE_TIME_IN_LONG = "quoteTimeInLong";
    public static final String MONEY_INTRINSIC_VALUE = "moneyIntrinsicValue";
    public static final String MULTIPLIER = "multiplier";
    public static final String STRIKE_PRICE = "strikePrice";
    public static final String TIME_VALUE = "timeValue";
    public static final String DELIVERABLES = "deliverables";
    public static final String DELTA = "delta";
    public static final String GAMMA = "gamma";
    public static final String THETA = "theta";
    public static final String VEGA = "vega";
    public static final String RHO = "rho";
    public static final String THEORETICAL_OPTION_VALUE = "theoreticalOptionValue";
    public static final String UNDERLYING_PRICE = "underlyingPrice";
    public static final String UV_EXPIRATION_TYPE = "uvExpirationType";
    public static final String SETTLEMENT_TYPE = "settlementType";
    public static final String PERCENT_CHANGE = "percentChange";
    public static final String TRADING_HOURS = "tradingHours";
    public static final String IS_TRADABLE = "isTradable";
    public static final String MARKET_MAKER = "marketMaker";
    public static final String _52WK_HIGH_IN_DOUBLE = "52WkHighInDouble";
    public static final String _52WK_LOW_IN_DOUBLE = "52WkLowInDouble";
    public static final String MARGINABLE = "marginable";
    public static final String SHORTABLE = "shortable";
    public static final String REGULAR_MARKET_LAST_PRICE = "regularMarketLastPrice";
    public static final String REGULAR_MARKET_LAST_SIZE = "regularMarketLastSize";
    public static final String REGULAR_MARKET_NET_CHANGE = "regularMarketNetChange";
    public static final String REGULAR_MARKET_TRADE_TIME_IN_LONG = "regularMarketTradeTimeInLong";

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("description")
    private String description;

    @JsonProperty("exchangeName")
    private String exchangeName;

    @JsonProperty("securityStatus")
    private String securityStatus;

    public Quote(Map<String, Object> values) {
        this.values = values;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getSecurityStatus() {
        return securityStatus;
    }

}

package com.raccoons.tda.serialization;

import com.raccoons.tda.api.model.quote.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QuoteTemplate {

    private static final Map<String, Class<?>> EQUITY_QUOTE = new HashMap<String, Class<?>>() {{
        put("symbol", String.class);
        put("description", String.class);
        put("bidPrice", String.class);
        put("bidSize", Integer.class);
        put("bidId", String.class);
        put("askPrice", String.class);
        put("askSize", Integer.class);
        put("askId", String.class);
        put("lastPrice", String.class);
        put("lastSize", Integer.class);
        put("lastId", String.class);
        put("openPrice", Double.class);
        put("highPrice", Double.class);
        put("lowPrice", Double.class);
        put("netChange", Double.class);
        put("quoteTimeInLong", Long.class);
        put("tradeTimeInLong", Long.class);
        put("mark", Double.class);
        put("exchange", String.class);
        put("exchangeName", String.class);
        put("marginable", Boolean.class);
        put("shortable", Boolean.class);
        put("volatility", Double.class);
        put("digits", Double.class);
        put("52WkHigh", Double.class);
        put("52WkLow", Double.class);
        put("peRatio", Double.class);
        put("divAmount", Double.class);
        put("divYield", Double.class);
        put("divDate", String.class);
        put("securityStatus", String.class);
        put("regularMarketLastPrice", Double.class);
        put("regularMarketLastSize", Long.class);
        put("regularMarketNetChange", Double.class);
        put("regularMarketTradeTimeInLong", Long.class);
    }};

    private static final Map<String, Class<?>> ETF_QUOTE = new HashMap<String, Class<?>>() {{
        put("symbol", String.class);
        put("description", String.class);
        put("bidPrice", String.class);
        put("bidSize", Integer.class);
        put("bidId", String.class);
        put("askPrice", String.class);
        put("askSize", Integer.class);
        put("askId", String.class);
        put("lastPrice", String.class);
        put("lastSize", Integer.class);
        put("lastId", String.class);
        put("openPrice", Double.class);
        put("highPrice", Double.class);
        put("lowPrice", Double.class);
        put("netChange", Double.class);
        put("quoteTimeInLong", Long.class);
        put("tradeTimeInLong", Long.class);
        put("mark", Double.class);
        put("exchange", String.class);
        put("exchangeName", String.class);
        put("marginable", Boolean.class);
        put("shortable", Boolean.class);
        put("volatility", Double.class);
        put("digits", Double.class);
        put("52WkHigh", Double.class);
        put("52WkLow", Double.class);
        put("peRatio", Double.class);
        put("divAmount", Double.class);
        put("divYield", Double.class);
        put("divDate", String.class);
        put("securityStatus", String.class);
        put("regularMarketLastPrice", Double.class);
        put("regularMarketLastSize", Long.class);
        put("regularMarketNetChange", Double.class);
        put("regularMarketTradeTimeInLong", Long.class);
    }};

    private static final Map<String, Class<?>> FOREX_QUOTE = new HashMap<String, Class<?>>() {{
        put("symbol", String.class);
        put("bidPriceInDouble", Double.class);
        put("askPriceInDouble", Double.class);
        put("lastPriceInDouble", Double.class);
        put("highPriceInDouble", Double.class);
        put("lowPriceInDouble", Double.class);
        put("closePriceInDouble", Double.class);
        put("openPriceInDouble", Double.class);
        put("changeInDouble", Double.class);
        put("percentChange", Double.class);
        put("digits", Double.class);
        put("exchange", String.class);
        put("description", String.class);
        put("exchangeName", String.class);
        put("securityStatus", String.class);
        put("tick", Double.class);
        put("tickAmount", Double.class);
        put("product", String.class);
        put("tradingHours", String.class);
        put("isTradable", Boolean.class);
        put("marketMaker", String.class);
        put("52WkHighInDouble", Double.class);
        put("52WkLowInDouble", Double.class);
        put("mark", Double.class);
    }};

    private static final Map<String, Class<?>> FUTURE_OPTION_QUOTE = new HashMap<String, Class<?>>() {{
        put("symbol", String.class);
        put("bidPriceInDouble", Double.class);
        put("askPriceInDouble", Double.class);
        put("lastPriceInDouble", Double.class);
        put("highPriceInDouble", Double.class);
        put("lowPriceInDouble", Double.class);
        put("closePriceInDouble", Double.class);
        put("description", String.class);
        put("openPriceInDouble", Double.class);
        put("netChangeInDouble", Double.class);
        put("openInterest", Double.class);
        put("exchangeName", String.class);
        put("securityStatus", String.class);
        put("volatility", Double.class);
        put("moneyIntrinsicValueInDouble", Double.class);
        put("multiplierInDouble", Double.class);
        put("digits", Double.class);
        put("strikePriceInDouble", Double.class);
        put("contractType", String.class);
        put("underlying", String.class);
        put("timeValueInDouble", Double.class);
        put("deltaInDouble", Double.class);
        put("gammaInDouble", Double.class);
        put("thetaInDouble", Double.class);
        put("vegaInDouble", Double.class);
        put("rhoInDouble", Double.class);
        put("mark", Double.class);
        put("tick", Double.class);
        put("tickAmount", Double.class);
        put("futureIsTradable", Boolean.class);
        put("futureTradingHours", String.class);
        put("futurePercentChange", Double.class);
        put("futureIsActive", Boolean.class);
        put("futureExpirationDate", Double.class);
        put("expirationType", String.class);
        put("exerciseType", String.class);
        put("inTheMoney", Boolean.class);
    }};

    private static final Map<String, Class<?>> FUTURE_QUOTE = new HashMap<String, Class<?>>() {{
        put("symbol", String.class);
        put("bidPriceInDouble", Double.class);
        put("askPriceInDouble", Double.class);
        put("lastPriceInDouble", Double.class);
        put("bidId", String.class);
        put("askId", String.class);
        put("highPriceInDouble", Double.class);
        put("lowPriceInDouble", Double.class);
        put("closePriceInDouble", Double.class);
        put("exchange", String.class);
        put("description", String.class);
        put("lastId", String.class);
        put("openPriceInDouble", Double.class);
        put("changeInDouble", Double.class);
        put("futurePercentChange", Double.class);
        put("exchangeName", String.class);
        put("securityStatus", String.class);
        put("mark", Double.class);
        put("openInterest", Double.class);
        put("tick", Double.class);
        put("tickAmount", Double.class);
        put("product", String.class);
        put("futurePriceFormat", String.class);
        put("futureTradingHours", String.class);
        put("futureIsTradable", Boolean.class);
        put("futureMultiplier", Double.class);
        put("futureIsActive", Boolean.class);
        put("futureSettlementPrice", Double.class);
        put("futureActiveSymbol", String.class);
        put("futureExpirationDate", String.class);
    }};

    private static final Map<String, Class<?>> INDEX_QUOTE = new HashMap<String, Class<?>>() {{
        put("symbol", String.class);
        put("description", String.class);
        put("lastPrice", Double.class);
        put("openPrice", Double.class);
        put("highPrice", Double.class);
        put("lowPrice", Double.class);
        put("closePrice", Double.class);
        put("netChange", Double.class);
        put("totalVolume", Long.class);
        put("tradeTimeInLong", Long.class);
        put("exchange", String.class);
        put("exchangeName", String.class);
        put("digits", Double.class);
        put("52WkHigh", Double.class);
        put("52WkLow", Double.class);
        put("securityStatus", String.class);
    }};

    private static final Map<String, Class<?>> MUTUAL_FUND_QUOTE = new HashMap<String, Class<?>>() {{
        put("symbol", String.class);
        put("description", String.class);
        put("closePrice", Double.class);
        put("netChange", Double.class);
        put("totalVolume", Long.class);
        put("exchange", String.class);
        put("exchangeName", String.class);
        put("digits", Double.class);
        put("52WkHigh", Double.class);
        put("52WkLow", Double.class);
        put("nAV", Double.class);
        put("peRatio", Double.class);
        put("divAmount", Double.class);
        put("divYield", Double.class);
        put("divDate", String.class);
        put("securityStatus", String.class);
    }};

    private static final Map<String, Class<?>> OPTION_QUOTE = new HashMap<String, Class<?>>() {{
        put("symbol", String.class);
        put("description", String.class);
        put("bidPrice", Double.class);
        put("bidSize", Integer.class);
        put("askPrice", Double.class);
        put("askSize", Integer.class);
        put("lastPrice", Double.class);
        put("lastSize", Integer.class);
        put("openPrice", Double.class);
        put("highPrice", Double.class);
        put("lowPrice", Double.class);
        put("closePrice", Double.class);
        put("netChange", Double.class);
        put("totalVolume", Long.class);
        put("quoteTimeInLong", Long.class);
        put("tradeTimeInLong", Long.class);
        put("mark", Double.class);
        put("openInterest", Long.class);
        put("volatility", Double.class);
        put("moneyIntrinsicValue", Double.class);
        put("multiplier", Double.class);
        put("strikePrice", Double.class);
        put("contractType", String.class);
        put("underlying", String.class);
        put("timeValue", Double.class);
        put("deliverables", String.class);
        put("delta", Double.class);
        put("gamma", Double.class);
        put("theta", Double.class);
        put("vega", Double.class);
        put("rho", Double.class);
        put("securityStatus", String.class);
        put("theoreticalOptionValue", Double.class);
        put("underlyingPrice", Double.class);
        put("uvExpirationType", String.class);
        put("exchange", String.class);
        put("exchangeName", String.class);
        put("settlementType", String.class);
    }};

    public static Class<? extends Quote> getQuoteType(final Set<String> keys) {
        if (false) {
            return EquityQuote.class;
        } else if (false) {
            return ETFQuote.class;
        } else if (false) {
            return ForexQuote.class;
        } else if (false) {
            return FutureOptionQuote.class;
        } else if (false) {
            return FutureQuote.class;
        } else if (false) {
            return IndexQuote.class;
        } else if (false) {
            return MutualFundQuote.class;
        } else if (false) {
            return OptionQuote.class;
        }
        return Quote.class;
    }

    public static Void getTemplates(final Class<? extends Quote> quoteType) {
        return null;
    }

}

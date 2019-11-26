package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class MutualFundQuote extends Quote {

    public MutualFundQuote(Map<String, Object> values) {
        super(values);
    }

//    {
//            "symbol": "string",
//            "description": "string",
//            "closePrice": 0,
//            "netChange": 0,
//            "totalVolume": 0,
//            "tradeTimeInLong": 0,
//            "exchange": "string",
//            "exchangeName": "string",
//            "digits": 0,
//            "52WkHigh": 0,
//            "52WkLow": 0,
//            "nAV": 0,
//            "peRatio": 0,
//            "divAmount": 0,
//            "divYield": 0,
//            "divDate": "string",
//            "securityStatus": "string"
//    }

    public Double getNetChange() {
        return null;
    }

    public Long getTotalVolume() {
        return null;
    }

    public Long getTradeTimeInLong() {
        return null;
    }

    public Integer getDigits() {
        return null;
    }

    public Double get52WeekHigh() {
        return null;
    }

    public Double get52WeekLow() {
        return null;
    }

    public Double getNAV() {
        return null;
    }

    public Double getPeRatio() {
        return null;
    }

    public Double getDividendAmount() {
        return null;
    }

    public Double getDividendYield() {
        return null;
    }

    public String getDividendDate() {
        return null;
    }
}

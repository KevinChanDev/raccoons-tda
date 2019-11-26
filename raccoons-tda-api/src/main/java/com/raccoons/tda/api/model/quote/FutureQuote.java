package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class FutureQuote extends Quote {

    public FutureQuote(Map<String, Object> values) {
        super(values);
    }

//    {
//            "symbol": "string",
//            "bidPriceInDouble": 0,
//            "askPriceInDouble": 0,
//            "lastPriceInDouble": 0,
//            "bidId": "string",
//            "askId": "string",
//            "highPriceInDouble": 0,
//            "lowPriceInDouble": 0,
//            "closePriceInDouble": 0,
//            "exchange": "string",
//            "description": "string",
//            "lastId": "string",
//            "openPriceInDouble": 0,
//            "changeInDouble": 0,
//            "futurePercentChange": 0,
//            "exchangeName": "string",
//            "securityStatus": "string",
//            "openInterest": 0,
//            "mark": 0,
//            "tick": 0,
//            "tickAmount": 0,
//            "product": "string",
//            "futurePriceFormat": "string",
//            "futureTradingHours": "string",
//            "futureIsTradable": false,
//            "futureMultiplier": 0,
//            "futureIsActive": false,
//            "futureSettlementPrice": 0,
//            "futureActiveSymbol": "string",
//            "futureExpirationDate": "string"
//    }

    public Double getAskPrice() {
        return null;
    }

    public Double getBidPrice() {
        return null;
    }

    public String getBidId() {
        return null;
    }

    public String getAskId() {
        return null;
    }

    public Double getHighPrice() {
        return null;
    }

    public Double getLowPrice() {
        return null;
    }

    public String getLastId() {
        return null;
    }

    public Double getOpenPrice() {
        return null;
    }

    public Double getFuturePercentageChange() {
        return null;
    }

    public Double getOpenInterest() {
        return null;
    }

    public Double getMark() {
        return null;
    }

    public Double getTick() {
        return null;
    }

    public Double getTickAmount() {
        return null;
    }

    public String getProduct() {
        return null;
    }

    public String getFuturePriceFormat() {
        return null;
    }

    public String getFutureTradingHours() {
        return null;
    }

    public boolean isFutureTradable() {
        return false;
    }

    public boolean isFutureActive() {
        return false;
    }

    public String getFutureMultiplier() {
        return null;
    }

    public Double getFutureSettlementPrice() {
        return null;
    }

    public String getFutureActiveSymbol() {
        return null;
    }

    public String getFutureExpirationDate() {
        return null;
    }
}

package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class Quotes {

    private final Map<String, Quote> quotes;

    public Quotes(Map<String, Quote> quotes) {
        this.quotes = quotes;
    }

    public Map<String, Quote> getQuotes() {
        return quotes;
    }
}

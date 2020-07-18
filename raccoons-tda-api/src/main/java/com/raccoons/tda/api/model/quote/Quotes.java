package com.raccoons.tda.api.model.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.model.ApiModel;

import java.util.Map;

public class Quotes extends ApiModel {

    @JsonProperty("quotes")
    private final Map<String, Quote> quotes;

    public Quotes(Map<String, Quote> quotes) {
        this.quotes = quotes;
    }

    public Map<String, Quote> getQuotes() {
        return quotes;
    }

}

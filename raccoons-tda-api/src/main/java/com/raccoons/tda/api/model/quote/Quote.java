package com.raccoons.tda.api.model.quote;

import java.util.Map;

public class Quote {

    private Map<String, Object> values;

    public Quote(Map<String, Object> values) {
        this.values = values;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public String getSymbol() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    public Double getClosePrice() {
        return null;
    }

    public String getExchange() {
        return null;
    }

    public String getExchangeName() {
        return null;
    }

    public String getSecurityStatus() {
        return null;
    }
}

package com.raccoons.tda.api.request;

import java.util.ArrayList;
import java.util.List;

public class QuoteRequest extends TDARequest {

    private final List<String> symbols;

    public QuoteRequest() {
        this.symbols = new ArrayList<>();
    }

    public QuoteRequest(List<String> symbols) {
        this.symbols = symbols;
    }

    public boolean isEmpty() {
        return symbols.isEmpty();
    }

    public List<String> getSymbols() {
        return symbols;
    }
}

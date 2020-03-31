package com.raccoons.tda.api.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.model.quote.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuoteMapper {

    private static final TypeReference<List<Map<String, Object>>> LIST_MAP_TYPE_REF = new TypeReference<List<Map<String, Object>>>() {
    };

    private static final TypeReference<Map<String, Object>> MAP_TYPE_REF = new TypeReference<Map<String, Object>>() {
    };

    public static Quote mapSingle(final ObjectMapper objectMapper, final String content) throws IOException {
        final Map<String, Object> values = objectMapper.readValue(content, MAP_TYPE_REF);
        return buildQuote(values);
    }

    public static Quote[] mapMultiple(final ObjectMapper objectMapper, final String content) throws IOException {
        final List<Map<String, Object>> listOfValues = objectMapper.readValue(content, LIST_MAP_TYPE_REF);
        final List<Quote> quotes = new ArrayList<>();
        for (Map<String, Object> values : listOfValues) {
            final Quote q = buildQuote(values);
            if (q != null) {
                quotes.add(q);
            }
        }
        return quotes.toArray(new Quote[0]);
    }

    private static Quote buildQuote(final Map<String, Object> values) {
        if (false) {
            return new EquityQuote(values);
        } else if (false) {
            return new ETFQuote(values);
        } else if (false) {
            return new ForexQuote(values);
        } else if (false) {
            return new FutureOptionQuote(values);
        } else if (false) {
            return new FutureQuote(values);
        } else if (false) {
            return new IndexQuote(values);
        } else if (false) {
            return new MutualFundQuote(values);
        } else if (false) {
            return new OptionQuote(values);
        }
        return null;
    }

}

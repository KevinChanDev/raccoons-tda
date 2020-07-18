package com.raccoons.tda.api.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.model.quote.*;
import com.raccoons.tda.api.response.QuoteResponse;
import com.raccoons.tda.api.response.QuotesResponse;
import com.raccoons.tda.api.response.TDAResponseStatus;
import com.raccoons.tda.api.value.QuoteAssetType;
import com.raccoons.tda.net.TDAHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QuoteResponses extends ResponseParser {

    private static final TypeReference<Map<String, Map<String, Object>>> MAP_REFERENCE
            = new TypeReference<Map<String, Map<String, Object>>>() {
    };

    public static QuoteResponse quoteResponse(final TDAHttpResponse tdaHttpResponse) {
        final String data = new String(tdaHttpResponse.getBody());
        try {
            final Map<String, Map<String, Object>> dataMap = getObjectMapper().readValue(data, MAP_REFERENCE);

            if (dataMap.size() > 0) {
                final Quote q = getQuoteFromMap(dataMap.values().iterator().next());
                return new QuoteResponse(TDAResponseStatus.SUCCESSFUL, q);
            }
        } catch (IOException ignore) {
        }
        return failedQuoteResponse();
    }

    public static QuotesResponse quotesResponse(final TDAHttpResponse tdaHttpResponse) {
        final String data = new String(tdaHttpResponse.getBody());
        try {
            final Map<String, Quote> quoteMap = new HashMap<>();
            final Map<String, Map<String, Object>> dataMap = getObjectMapper().readValue(data, MAP_REFERENCE);

            for (Map.Entry<String, Map<String, Object>> entry : dataMap.entrySet()) {
                final String key = entry.getKey();
                final Quote q = getQuoteFromMap(entry.getValue());
                quoteMap.put(key, q);
            }

            final Quotes quotes = new Quotes(quoteMap);
            return new QuotesResponse(TDAResponseStatus.SUCCESSFUL, quotes);
        } catch (IOException ignore) {
            return failedQuotesResponse();
        }
    }

    public static QuoteResponse failedQuoteResponse() {
        return new QuoteResponse(TDAResponseStatus.FAILED, null);
    }

    public static QuotesResponse failedQuotesResponse() {
        return new QuotesResponse(TDAResponseStatus.FAILED, null);
    }

    private static Quote getQuoteFromMap(final Map<String, Object> values) {
        final String assetType = (String) values.get("assetType");
        if (assetType != null) {
            switch (assetType) {
                case QuoteAssetType.ETF:
                    return new ETFQuote(values);

                case QuoteAssetType.EQUITY:
                    return new EquityQuote(values);

                case QuoteAssetType.FOREX:
                    return new ForexQuote(values);

                case QuoteAssetType.FUTURE:
                    return new FutureQuote(values);

                case QuoteAssetType.FUTURE_OPTION:
                    return new FutureOptionQuote(values);

                case QuoteAssetType.INDEX:
                    return new IndexQuote(values);

                case QuoteAssetType.MUTUAL_FUND:
                    return new MutualFundQuote(values);

                case QuoteAssetType.OPTION:
                    return new OptionQuote(values);
            }
        }
        return new Quote(values);
    }

}

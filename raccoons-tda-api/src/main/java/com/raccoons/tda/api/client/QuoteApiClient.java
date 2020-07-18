package com.raccoons.tda.api.client;

import com.raccoons.tda.api.domain.AccountContext;
import com.raccoons.tda.api.parser.QuoteResponses;
import com.raccoons.tda.api.request.QuoteRequest;
import com.raccoons.tda.api.request.RequestEndpoints;
import com.raccoons.tda.api.response.QuoteResponse;
import com.raccoons.tda.api.response.QuotesResponse;
import com.raccoons.tda.context.TDAContext;
import com.raccoons.tda.net.TDAHttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class QuoteApiClient extends BaseApiClient {

    public QuoteApiClient(TDAContext tdaContext) {
        super(tdaContext);
    }

    public QuotesResponse getQuotes(final AccountContext accountContext, final QuoteRequest quoteRequest) {
        final String endpoint = RequestEndpoints.getQuotes();
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();

        if (!quoteRequest.isEmpty()) {
            final Map<String, String> headers = accountContext.getAuthorizationHeader();
            final String joined = quoteRequest.getSymbols().stream().map(String::toUpperCase).collect(Collectors.joining(","));
            final Map<String, String> queryParameters = new HashMap<>();
            queryParameters.put("symbol", joined);
            return QuoteResponses.quotesResponse(tdaHttpClient.get(endpoint, headers, queryParameters));
        } else {
            return QuoteResponses.failedQuotesResponse();
        }
    }

    public QuoteResponse getQuote(final AccountContext accountContext, final QuoteRequest quoteRequest) {
        if (!quoteRequest.isEmpty()) {
            final String symbol = quoteRequest.getSymbols().get(0).toUpperCase();
            final String endpoint = RequestEndpoints.getQuote(symbol);
            final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();
            return QuoteResponses.quoteResponse(tdaHttpClient.get(endpoint, accountContext.getAuthorizationHeader()));
        } else {
            return QuoteResponses.failedQuoteResponse();
        }
    }

}

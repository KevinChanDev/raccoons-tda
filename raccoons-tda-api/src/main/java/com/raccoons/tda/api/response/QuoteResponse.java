package com.raccoons.tda.api.response;

import com.raccoons.tda.api.model.quote.Quote;
import com.raccoons.tda.api.request.ResponseType;

public class QuoteResponse extends TDAResponse<Quote> {

    public QuoteResponse(TDAResponseStatus responseStatus, Quote data) {
        super(responseStatus, data);
    }

    public QuoteResponse(ResponseType responseType, long startTime, long endTime, Quote data) {
        super(responseType, startTime, endTime, data);
    }

}

package com.raccoons.tda.api.response;

import com.raccoons.tda.api.model.quote.Quotes;
import com.raccoons.tda.api.request.ResponseType;

public class QuotesResponse extends TDAResponse<Quotes> {

    public QuotesResponse(TDAResponseStatus responseStatus, Quotes data) {
        super(responseStatus, data);
    }

    public QuotesResponse(ResponseType responseType, long startTime, long endTime, Quotes data) {
        super(responseType, startTime, endTime, data);
    }
}

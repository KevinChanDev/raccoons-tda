package com.raccoons.api.tests;

import com.raccoons.tda.api.model.quote.Quote;
import com.raccoons.tda.api.parser.ResponseParser;
import com.raccoons.tda.api.response.QuotesResponse;
import com.raccoons.tda.api.response.TDAResponse;
import org.junit.Test;

public class ApiTests {

    @Test
    public void test() {
        ResponseParser rp = new ResponseParser();
        final QuotesResponse response = rp.parse(null);

        TDAResponse<Quote> quoteResponse = TDAResponse.<Quote>newBuilder().data(null)
                .build();

    }

}

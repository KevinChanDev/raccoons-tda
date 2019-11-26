package com.raccoons.tda.api.response;

import com.raccoons.tda.net.TDAHttpResponse;

public class ResponseBuilder {

    public static <T extends TDAResponse> T buildResponse() {
        return null;
    }

    public static AccountResponse successfulAccountResponse(final TDAHttpResponse tdaHttpResponse) {
        return null;
    }

    public static AccountResponse failedAccountResponse(){
        return null;
    }

    public static InstrumentResponse instrumentResponse() {
        return null;
    }

    public static OrderPositionResponse orderPositionResponse() {
        return null;
    }

    public static OrderResponse orderResponse() {
        return null;
    }

    public static PriceHistoryResponse priceHistoryResponse() {
        return null;
    }

    public static QuoteResponse quoteResponse() {
        return null;
    }

    public static SavedOrderResponse savedOrderResponse() {
        return null;
    }

    public static SavedOrderPositionResponse savedOrderPositionResponse() {
        return null;
    }

}

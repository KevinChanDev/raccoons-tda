package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;

import java.util.concurrent.CompletableFuture;

public class PriceHistoryClient extends RequestOperation {

    public PriceHistoryClient(RequestClient requestClient) {
        super(requestClient);
    }

    public CompletableFuture<TDAResponse> getPriceHistory(){
        return null;
    }
}

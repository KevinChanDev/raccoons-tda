package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;

import java.util.concurrent.CompletableFuture;

public class MarketHourClient extends RequestOperation {

    public MarketHourClient(RequestClient requestClient) {
        super(requestClient);
    }

    public CompletableFuture<TDAResponse> getHoursForMultipleMarkets() {
        return null;
    }

    public CompletableFuture<TDAResponse> getHoursForASingleMarket() {
        return null;
    }

}

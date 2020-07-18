package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;
import com.raccoons.tda.context.TDAContext;

import java.util.concurrent.CompletableFuture;

public class MarketHourApiClient extends BaseApiClient {

    public MarketHourApiClient(TDAContext tdaContext) {
        super(tdaContext);
    }

    public CompletableFuture<TDAResponse> getHoursForMultipleMarkets() {
        return null;
    }

    public CompletableFuture<TDAResponse> getHoursForASingleMarket() {
        return null;
    }

}

package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;
import com.raccoons.tda.context.TDAContext;

import java.util.concurrent.CompletableFuture;

public class InstrumentApiClient extends BaseApiClient {

    public InstrumentApiClient(TDAContext tdaContext) {
        super(tdaContext);
    }

    public CompletableFuture<TDAResponse> searchInstruments() {
        return null;
    }

    public CompletableFuture<TDAResponse> getInstrument() {
        return null;
    }
}

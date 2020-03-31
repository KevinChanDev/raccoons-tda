package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;
import com.raccoons.tda.context.TDAContext;

import java.util.concurrent.CompletableFuture;

public class InstrumentClient extends BaseClient {

    public InstrumentClient(TDAContext tdaContext) {
        super(tdaContext);
    }

    public CompletableFuture<TDAResponse> searchInstruments() {
        return null;
    }

    public CompletableFuture<TDAResponse> getInstrument() {
        return null;
    }
}

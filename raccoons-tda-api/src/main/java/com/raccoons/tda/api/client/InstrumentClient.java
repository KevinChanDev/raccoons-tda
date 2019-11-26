package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;

import java.util.concurrent.CompletableFuture;

public class InstrumentClient extends RequestOperation {

    public InstrumentClient(RequestClient requestClient) {
        super(requestClient);
    }

    public CompletableFuture<TDAResponse> searchInstruments() {
        return null;
    }

    public CompletableFuture<TDAResponse> getInstrument() {
        return null;
    }
}

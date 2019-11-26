package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;

import java.util.concurrent.CompletableFuture;

public class OptionChainClient extends RequestOperation {

    public OptionChainClient(RequestClient requestClient) {
        super(requestClient);
    }

    public CompletableFuture<TDAResponse> getOptionChain(){
        return null;
    }

}

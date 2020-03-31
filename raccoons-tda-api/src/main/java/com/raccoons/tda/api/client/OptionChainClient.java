package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;
import com.raccoons.tda.context.TDAContext;

import java.util.concurrent.CompletableFuture;

public class OptionChainClient extends BaseClient {

    public OptionChainClient(TDAContext tdaContext) {
        super(tdaContext);
    }

    public CompletableFuture<TDAResponse> getOptionChain(){
        return null;
    }

}

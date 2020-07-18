package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;
import com.raccoons.tda.context.TDAContext;

import java.util.concurrent.CompletableFuture;

public class TransactionHistoryApiClient extends BaseApiClient {

    public TransactionHistoryApiClient(TDAContext tdaContext) {
        super(tdaContext);
    }

    public CompletableFuture<TDAResponse> getTransaction() {
        return null;
    }

    public CompletableFuture<TDAResponse> getTransactions() {
        return null;
    }

}

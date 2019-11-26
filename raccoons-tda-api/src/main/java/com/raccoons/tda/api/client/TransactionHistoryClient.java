package com.raccoons.tda.api.client;

import com.raccoons.tda.api.response.TDAResponse;

import java.util.concurrent.CompletableFuture;

public class TransactionHistoryClient extends RequestOperation {

    public TransactionHistoryClient(RequestClient requestClient) {
        super(requestClient);
    }

    public CompletableFuture<TDAResponse> getTransaction() {
        return null;
    }

    public CompletableFuture<TDAResponse> getTransactions() {
        return null;
    }

}

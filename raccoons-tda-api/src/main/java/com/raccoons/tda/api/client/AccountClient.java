package com.raccoons.tda.api.client;

import com.raccoons.tda.api.parser.OrderPositionResponses;
import com.raccoons.tda.api.request.OrderPositionRequest;
import com.raccoons.tda.api.request.OrderRequest;
import com.raccoons.tda.api.request.RequestEndpoints;
import com.raccoons.tda.api.response.AccountResponse;
import com.raccoons.tda.api.response.OrderPositionResponse;
import com.raccoons.tda.api.response.OrderResponse;
import com.raccoons.tda.api.response.TDAResponse;
import com.raccoons.tda.api.value.Status;
import com.raccoons.tda.context.TDAContext;
import com.raccoons.tda.net.TDAHttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AccountClient extends BaseClient {

    public AccountClient(TDAContext tdaContext) {
        super(tdaContext);
    }

    public CompletableFuture<OrderResponse> cancelOrder(final AccountContext accountContext, final String orderId) {
        final String accountId = accountContext.getAccountId();
        final String endpoint = RequestEndpoints.cancelOrder(accountId, orderId);
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();

        if (getLogger().isInfoEnabled()) {
            getLogger().info("Executing cancelOrder for " + accountId);
        }

        return tdaHttpClient.delete(endpoint, accountContext.getAuthorizationHeader()).thenApply(tdaHttpResponse -> {
            getLogger().info("Response from cancelOrder Received");
            return null;
        });
    }

    public CompletableFuture<OrderPositionResponse> getOrder(final AccountContext accountContext, final String orderId) {
        final String accountId = accountContext.getAccountId();
        final String endpoint = RequestEndpoints.getOrder(accountId, orderId);
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();

        return tdaHttpClient.get(endpoint, accountContext.getAuthorizationHeader()).thenApply(tdaHttpResponse -> {
            final String data = new String(tdaHttpResponse.getBody());
            getLogger().info(data);
            return null;
        });
    }

    public CompletableFuture<OrderPositionResponse> getOrdersByPath(final AccountContext accountContext,
                                                                    final OrderPositionRequest orderPositionRequest) {
        final String accountId = accountContext.getAccountId();
        final String endpoint = RequestEndpoints.getOrdersByPath(accountId);
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();

        final Map<String, String> parameters = new HashMap<>();

        final int maxResults = orderPositionRequest.getMaxResults();
        final String fromEnteredTime = orderPositionRequest.getFromEnteredTime();
        final String toEnteredTime = orderPositionRequest.getToEnteredTime();
        final Status status = orderPositionRequest.getStatus();

        if (maxResults > 0) {
            parameters.put("maxResults", String.valueOf(maxResults));
        }

        if (fromEnteredTime != null) {
            parameters.put("fromEnteredTime", fromEnteredTime);
        }

        if (fromEnteredTime != null && toEnteredTime != null) {
            parameters.put("toEnteredTime", toEnteredTime);
        }

        if (status != null) {
            parameters.put("status", status.toString());
        }

        return tdaHttpClient.get(endpoint, accountContext.getAuthorizationHeader(), parameters)
                .thenApply(OrderPositionResponses::getOrderPositionResponse);
    }

    public CompletableFuture<OrderPositionResponse> getOrdersByQuery(final AccountContext accountContext,
                                                                     final OrderPositionRequest orderPositionRequest) {
        final String endpoint = RequestEndpoints.getOrdersByQuery();
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();

        final String accountId = orderPositionRequest.getAccountId();

        if (accountId != null) {
            final Map<String, String> parameters = new HashMap<>();
            final int maxResults = orderPositionRequest.getMaxResults();
            final String fromEnteredTime = orderPositionRequest.getFromEnteredTime();
            final String toEnteredTime = orderPositionRequest.getToEnteredTime();
            final Status status = orderPositionRequest.getStatus();
            parameters.put("accountId", accountId);

            if (maxResults > 0) {
                parameters.put("maxResults", String.valueOf(maxResults));
            }

            if (fromEnteredTime != null) {
                parameters.put("fromEnteredTime", fromEnteredTime);
            }

            if (fromEnteredTime != null && toEnteredTime != null) {
                parameters.put("toEnteredTime", toEnteredTime);
            }

            if (status != null) {
                parameters.put("status", status.toString());
            }

            return tdaHttpClient.get(endpoint, accountContext.getAuthorizationHeader(), parameters).thenApply(tdaHttpResponse -> {
                System.out.println();
                return null;
            });
        }
        return null;
    }

    public CompletableFuture<OrderResponse> placeOrder(final AccountContext accountContext, final OrderRequest orderRequest) {
        final String accountId = accountContext.getAccountId();
        final String endpoint = RequestEndpoints.placeOrder(accountId);
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();
        return null;
    }

    public CompletableFuture<OrderResponse> replaceOrder(final AccountContext accountContext, final String orderId,
                                                         final OrderRequest orderRequest) {
        final String accountId = accountContext.getAccountId();
        final String endpoint = RequestEndpoints.replaceOrder(accountId, orderId);
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();
        return null;
    }

    public CompletableFuture<AccountResponse> getAccount(final AccountContext accountContext) {
        final String accountId = accountContext.getAccountId();
        final String endpoint = RequestEndpoints.getAccount(accountId);
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();

        return tdaHttpClient.get(endpoint, accountContext.getAuthorizationHeader()).thenApply(tdaHttpResponse -> {
            final String data = new String(tdaHttpResponse.getBody());
            getLogger().info(data);
            return null;
        });
    }

    public CompletableFuture<AccountResponse> getAccounts(final AccountContext accountContext) {
        final String endpoint = RequestEndpoints.getAccounts();
        final TDAHttpClient tdaHttpClient = getContext().getClientProvider().getTDAHttpClient();

        getLogger().info("Executing getAccounts");

        return tdaHttpClient.get(endpoint, accountContext.getAuthorizationHeader()).thenApply(tdaHttpResponse -> {
            final String data = new String(tdaHttpResponse.getBody());
            getLogger().info(data);
            return null;
        });
    }

    public CompletableFuture<TDAResponse> createSavedOrder(final AccountContext accountContext) {
        return null;
    }

    public CompletableFuture<TDAResponse> deleteSavedOrder(final AccountContext accountContext) {
        return null;
    }

    public CompletableFuture<TDAResponse> getSavedOrder(final AccountContext accountContext) {
        return null;
    }

    public CompletableFuture<TDAResponse> getSavedOrdersByPath(final AccountContext accountContext) {
        return null;
    }

    public CompletableFuture<TDAResponse> replaceSavedOrder(final AccountContext accountContext) {
        return null;
    }
}

package com.raccoons.tda.api.client;

import com.raccoons.tda.api.parser.UserInfoResponses;
import com.raccoons.tda.api.request.RequestEndpoints;
import com.raccoons.tda.api.response.TDAResponse;
import com.raccoons.tda.api.response.UserPrincipleResponse;
import com.raccoons.tda.net.TDAHttpClient;
import com.raccoons.tda.net.TDAHttpResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class UserInfoClient extends RequestOperation {

    private static final String USER_PRINCIPLE_FIELDS = "fields";

    public UserInfoClient(RequestClient requestClient) {
        super(requestClient);
    }

    public CompletableFuture<TDAResponse> getPreferences(final AccountContext accountContext) {
        final TDAHttpClient tdaHttpClient = getRequestClient().getHttpClient();
        final String accountId = accountContext.getAccountId();
        final String endpoint = RequestEndpoints.getPreferences(accountId);

        return tdaHttpClient.get(endpoint, accountContext.getAuthorizationHeader()).thenApply(new Function<TDAHttpResponse, TDAResponse>() {
            @Override
            public TDAResponse apply(TDAHttpResponse tdaHttpResponse) {
                return null;
            }
        });
    }

    public CompletableFuture<TDAResponse> getStreamerSubscriptionKeys() {
        return null;
    }

    public CompletableFuture<UserPrincipleResponse> getUserPrincipals(final AccountContext accountContext, final String... fields) {
        final TDAHttpClient tdaHttpClient = getRequestClient().getHttpClient();
        final String endpoint = RequestEndpoints.getUserPrinciples();
        final Map<String, String> parameters = new HashMap<>();
        if (fields != null && fields.length > 0) {
            parameters.put(USER_PRINCIPLE_FIELDS, String.join(",", fields));
        }
        return tdaHttpClient.get(endpoint, accountContext.getAuthorizationHeader(), parameters).thenApply(UserInfoResponses::getUserPrincipleResponse);
    }

    public CompletableFuture<TDAResponse> updatePreferences() {
        return null;
    }
}

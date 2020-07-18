package com.raccoons.tda.api.client;

import com.raccoons.tda.api.domain.AccountContext;
import com.raccoons.tda.api.model.*;
import com.raccoons.tda.api.model.account.Account;
import com.raccoons.tda.api.model.account.Accounts;
import com.raccoons.tda.api.model.order.Order;
import com.raccoons.tda.api.model.order.OrderPosition;
import com.raccoons.tda.api.request.OrderPositionRequest;
import com.raccoons.tda.api.response.TDAResponse;

public interface AccountClient {

    // Orders
    TDAResponse<Void> cancelOrder(final AccountContext accountContext, final String orderId);

    TDAResponse<OrderPosition> getOrder(final AccountContext accountContext, final String orderId);

    TDAResponse<OrderPosition> getOrdersByPath(final AccountContext accountContext,
                                               final OrderPositionRequest orderPositionRequest);

    TDAResponse<Order> getOrdersByQuery(final AccountContext accountContext);

    TDAResponse<Order> placeOrder(final AccountContext accountContext, final Order order);

    TDAResponse<Order> replaceOrder(final AccountContext accountContext, final String orderId, final Order order);

    // Accounts
    TDAResponse<Account> getAccount(final AccountContext accountContext);

    TDAResponse<Accounts> getAccounts(final AccountContext accountContext);


    // Saved Orders
    TDAResponse<SavedOrder> createSavedOrder(final AccountContext accountContext);

    TDAResponse<Void> deleteSavedOrder(final AccountContext accountContext, final String savedOrderId);

    TDAResponse<SavedOrder> getSavedOrder(final AccountContext accountContext, final String savedOrderId);

    TDAResponse<SavedOrders> getSavedOrdersByPath(final AccountContext accountContext);

    TDAResponse<SavedOrders> replaceSavedOrder(final AccountContext accountContext, final String savedOrderId);


}

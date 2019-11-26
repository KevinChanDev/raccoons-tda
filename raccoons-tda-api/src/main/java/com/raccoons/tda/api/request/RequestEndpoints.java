package com.raccoons.tda.api.request;

public class RequestEndpoints {

    public static final String CANCEL_ORDER = "https://api.tdameritrade.com/v1/accounts/{accountId}/orders/{orderId}";
    public static final String GET_ORDER = "https://api.tdameritrade.com/v1/accounts/{accountId}/orders/{orderId}";
    public static final String GET_ORDERS_BY_PATH = "https://api.tdameritrade.com/v1/accounts/%s/orders";
    public static final String GET_ORDERS_BY_QUERY = "https://api.tdameritrade.com/v1/orders";

    public static final String PLACE_ORDER = "https://api.tdameritrade.com/v1/accounts/{accountId}/orders";
    public static final String REPLACE_ORDER = "https://api.tdameritrade.com/v1/accounts/{accountId}/orders/{orderId}";

    public static final String CREATE_SAVED_ORDER = "";
    public static final String DELETE_SAVED_ORDER = "";
    public static final String GET_SAVED_ORDER = "";
    public static final String GET_SAVED_ORDER_BY_PATH = "";
    public static final String REPLACE_SAVED_ORDER = "";

    // Accounts
    public static final String GET_ACCOUNT = "https://api.tdameritrade.com/v1/accounts/{accountId}";
    public static final String GET_ACCOUNTS = "https://api.tdameritrade.com/v1/accounts";

    // Quotes
    public static final String GET_QUOTE = "https://api.tdameritrade.com/v1/marketdata/%s/quotes";
    public static final String GET_QUOTES = "https://api.tdameritrade.com/v1/marketdata/quotes";

    public static String cancelOrder(final String accountId, final String orderId) {
        return "";
    }

    public static String getOrder(final String accountId, final String orderId) {
        return "";
    }

    public static String getOrdersByPath(final String accountId) {
        return String.format(GET_ORDERS_BY_PATH, accountId);
    }

    public static String getOrdersByQuery() {
        return GET_ORDERS_BY_QUERY;
    }

    public static String placeOrder(final String accountId) {
        return "";
    }

    public static String replaceOrder(final String accountId, final String orderId) {
        return "";
    }

    public static String getAccount(final String accountId) {
        return null;
    }

    public static String getAccounts() {
        return GET_ACCOUNTS;
    }

    public static String getQuote(final String quote) {
        return String.format(GET_QUOTE, quote);
    }

    public static String getQuotes() {
        return GET_QUOTES;
    }
}

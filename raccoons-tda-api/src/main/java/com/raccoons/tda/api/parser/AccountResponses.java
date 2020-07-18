package com.raccoons.tda.api.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.model.account.Account;
import com.raccoons.tda.api.model.account.Accounts;
import com.raccoons.tda.api.response.AccountResponse;
import com.raccoons.tda.api.response.AccountsResponse;
import com.raccoons.tda.api.response.TDAResponseStatus;
import com.raccoons.tda.net.TDAHttpResponse;

import java.util.ArrayList;
import java.util.List;

public class AccountResponses {

    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null)
            objectMapper = new ObjectMapper();
        return objectMapper;
    }

    public static AccountResponse of(final TDAHttpResponse tdaHttpResponse) {
        final String data = new String(tdaHttpResponse.getBody());

        try {
            final List<Account> accountList = new ArrayList<>();
            final Accounts accounts = new Accounts(accountList);
            final AccountResponse accountResponse = new AccountResponse(TDAResponseStatus.SUCCESSFUL, accounts);

            return accountResponse;
        } catch (Exception e) {
            return failed();
        }
    }

    public static AccountResponse failed() {
        return new AccountResponse(TDAResponseStatus.FAILED, null);
    }

    public static AccountsResponse failedAccountsResponse() {
        return new AccountsResponse(TDAResponseStatus.FAILED, null);
    }

}

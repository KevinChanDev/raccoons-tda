package com.raccoons.tda.api.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty("securitiesAccount")
    private SecuritiesAccount securitiesAccount;

    public SecuritiesAccount getSecuritiesAccount() {
        return securitiesAccount;
    }
}

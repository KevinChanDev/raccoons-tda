package com.raccoons.tda.api.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Accounts {

    @JsonProperty("accounts")
    private final List<Account> accounts;

    public Accounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}

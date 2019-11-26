package com.raccoons.tda.api.model;

import com.raccoons.tda.api.value.AccountType;

import java.util.List;


public class SecuritiesAccount {

    private AccountType type;
    private String accountId;
    private int roundTrips;
    private boolean isDayTrader;
    private boolean isClosingOnlyRestricted;
    private List<Position> positions;
    private List<OrderPosition> orderStrategies;
    private InitialBalances initialBalances;
    private CurrentBalances currentBalances;
    private ProjectedBalances projectedBalances;

    public AccountType getType() {
        return type;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getRoundTrips() {
        return roundTrips;
    }

    public boolean isDayTrader() {
        return isDayTrader;
    }

    public boolean isClosingOnlyRestricted() {
        return isClosingOnlyRestricted;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<OrderPosition> getOrderStrategies() {
        return orderStrategies;
    }

    public InitialBalances getInitialBalances() {
        return initialBalances;
    }

    public CurrentBalances getCurrentBalances() {
        return currentBalances;
    }

    public ProjectedBalances getProjectedBalances() {
        return projectedBalances;
    }
}

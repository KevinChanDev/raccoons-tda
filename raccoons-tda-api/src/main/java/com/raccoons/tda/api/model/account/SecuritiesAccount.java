package com.raccoons.tda.api.model.account;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.model.*;
import com.raccoons.tda.api.model.order.OrderPosition;
import com.raccoons.tda.api.value.AccountType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecuritiesAccount {

    @JsonProperty("type")
    private AccountType type;

    @JsonProperty("accountId")
    private String accountId;

    @JsonProperty("roundTrips")
    private Integer roundTrips;

    @JsonProperty("isDayTrader")
    private Boolean isDayTrader;

    @JsonProperty("isClosingOnlyRestricted")
    private Boolean isClosingOnlyRestricted;

    @JsonProperty("positions")
    private List<Position> positions;

    @JsonProperty("orderStrategies")
    private List<OrderPosition> orderStrategies;

    @JsonProperty("initialBalances")
    private InitialBalances initialBalances;

    @JsonProperty("currentBalances")
    private CurrentBalances currentBalances;

    @JsonProperty("projectedBalances")
    private ProjectedBalances projectedBalances;

    private Map<String, Object> other;

    public AccountType getType() {
        return type;
    }

    public String getAccountId() {
        return accountId;
    }

    public Integer getRoundTrips() {
        return roundTrips;
    }

    public Boolean getDayTrader() {
        return isDayTrader;
    }

    public Boolean getClosingOnlyRestricted() {
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

    @JsonAnySetter
    private void setOther(String key, Object value) {
        if (other == null) {
            this.other = new HashMap<>();
        }
        other.put(key, value);
    }
}

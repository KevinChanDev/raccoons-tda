package com.raccoons.tda.api.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectedBalances {

    @JsonProperty("accruedInterest")
    private Double accruedInterest;

    @JsonProperty("cashBalance")
    private Double cashBalance;

    @JsonProperty("cashReceipts")
    private Double cashReceipts;

    @JsonProperty("longOptionMarketValue")
    private Double longOptionMarketValue;

    @JsonProperty("liquidationValue")
    private Double liquidationValue;

    @JsonProperty("longMarketValue")
    private Double longMarketValue;

    @JsonProperty("moneyMarketFund")
    private Double moneyMarketFund;

    @JsonProperty("savings")
    private Double savings;

    @JsonProperty("shortMarketValue")
    private Double shortMarketValue;

    @JsonProperty("pendingDeposits")
    private Double pendingDeposits;

    @JsonProperty("cashAvailableForTrading")
    private Double cashAvailableForTrading;

    @JsonProperty("cashAvailableForWithdrawal")
    private Double cashAvailableForWithdrawal;

    @JsonProperty("cashCall")
    private Double cashCall;

    @JsonProperty("longNonMarginableMarketValue")
    private Double longNonMarginableMarketValue;

    @JsonProperty("totalCash")
    private Double totalCash;

    @JsonProperty("shortOptionMarketValue")
    private Double shortOptionMarketValue;

    @JsonProperty("mutualFundValue")
    private Double mutualFundValue;

    @JsonProperty("bondValue")
    private Double bondValue;

    @JsonProperty("cashDebitCallValue")
    private Double cashDebitCallValue;

    @JsonProperty("unsettledCash")
    private Double unsettledCash;

    public Double getAccruedInterest() {
        return accruedInterest;
    }

    public Double getCashBalance() {
        return cashBalance;
    }

    public Double getCashReceipts() {
        return cashReceipts;
    }

    public Double getLongOptionMarketValue() {
        return longOptionMarketValue;
    }

    public Double getLiquidationValue() {
        return liquidationValue;
    }

    public Double getLongMarketValue() {
        return longMarketValue;
    }

    public Double getMoneyMarketFund() {
        return moneyMarketFund;
    }

    public Double getSavings() {
        return savings;
    }

    public Double getShortMarketValue() {
        return shortMarketValue;
    }

    public Double getPendingDeposits() {
        return pendingDeposits;
    }

    public Double getCashAvailableForTrading() {
        return cashAvailableForTrading;
    }

    public Double getCashAvailableForWithdrawal() {
        return cashAvailableForWithdrawal;
    }

    public Double getCashCall() {
        return cashCall;
    }

    public Double getLongNonMarginableMarketValue() {
        return longNonMarginableMarketValue;
    }

    public Double getTotalCash() {
        return totalCash;
    }

    public Double getShortOptionMarketValue() {
        return shortOptionMarketValue;
    }

    public Double getMutualFundValue() {
        return mutualFundValue;
    }

    public Double getBondValue() {
        return bondValue;
    }

    public Double getCashDebitCallValue() {
        return cashDebitCallValue;
    }

    public Double getUnsettledCash() {
        return unsettledCash;
    }
}

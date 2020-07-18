package com.raccoons.tda.api.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitialBalances {

    @JsonProperty("accruedInterest")
    private double accruedInterest;

    @JsonProperty("cashAvailableForTrading")
    private double cashAvailableForTrading;

    @JsonProperty("cashAvailableForWithdrawal")
    private double cashAvailableForWithdrawal;

    @JsonProperty("cashBalance")
    private double cashBalance;

    @JsonProperty("bondValue")
    private double bondValue;

    @JsonProperty("cashReceipts")
    private double cashReceipts;

    @JsonProperty("liquidationValue")
    private double liquidationValue;

    @JsonProperty("longOptionMarketValue")
    private double longOptionMarketValue;

    @JsonProperty("longStockValue")
    private double longStockValue;

    @JsonProperty("moneyMarketFund")
    private double moneyMarketFund;

    @JsonProperty("mutualFundValue")
    private double mutualFundValue;

    @JsonProperty("shortOptionMarketValue")
    private double shortOptionMarketValue;

    @JsonProperty("shortStockValue")
    private double shortStockValue;

    @JsonProperty("isInCall")
    private double isInCall;

    @JsonProperty("unsettledCash")
    private double unsettledCash;

    @JsonProperty("cashDebitCallValue")
    private double cashDebitCallValue;

    @JsonProperty("pendingDeposits")
    private double pendingDeposits;

    @JsonProperty("accountValue")
    private double accountValue;

    public double getAccruedInterest() {
        return accruedInterest;
    }

    public double getCashAvailableForTrading() {
        return cashAvailableForTrading;
    }

    public double getCashAvailableForWithdrawal() {
        return cashAvailableForWithdrawal;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public double getBondValue() {
        return bondValue;
    }

    public double getCashReceipts() {
        return cashReceipts;
    }

    public double getLiquidationValue() {
        return liquidationValue;
    }

    public double getLongOptionMarketValue() {
        return longOptionMarketValue;
    }

    public double getLongStockValue() {
        return longStockValue;
    }

    public double getMoneyMarketFund() {
        return moneyMarketFund;
    }

    public double getMutualFundValue() {
        return mutualFundValue;
    }

    public double getShortOptionMarketValue() {
        return shortOptionMarketValue;
    }

    public double getShortStockValue() {
        return shortStockValue;
    }

    public double getIsInCall() {
        return isInCall;
    }

    public double getUnsettledCash() {
        return unsettledCash;
    }

    public double getCashDebitCallValue() {
        return cashDebitCallValue;
    }

    public double getPendingDeposits() {
        return pendingDeposits;
    }

    public double getAccountValue() {
        return accountValue;
    }
}

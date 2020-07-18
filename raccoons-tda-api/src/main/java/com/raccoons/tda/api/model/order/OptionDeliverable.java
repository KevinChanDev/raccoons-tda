package com.raccoons.tda.api.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionDeliverable {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("deliverableUnits")
    private Double deliverableUnits;

    @JsonProperty("currencyType")
    private CurrencyType currencyType;

    @JsonProperty("assetType")
    private AssetType assetType;

    public String getSymbol() {
        return symbol;
    }

    public Double getDeliverableUnits() {
        return deliverableUnits;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public AssetType getAssetType() {
        return assetType;
    }
}

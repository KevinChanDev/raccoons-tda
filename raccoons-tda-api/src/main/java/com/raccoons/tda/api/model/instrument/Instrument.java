package com.raccoons.tda.api.model.instrument;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.model.ApiModel;
import com.raccoons.tda.api.value.AssetType;

public class Instrument extends ApiModel {

    @JsonProperty("cusip")
    protected String cusip;

    @JsonProperty("symbol")
    protected String symbol;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("exchange")
    protected String exchange;

    @JsonProperty("assetType")
    protected AssetType assetType;

    public AssetType getAssetType() {
        return assetType;
    }

    public String getCusip() {
        return cusip;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public String getExchange() {
        return exchange;
    }

}

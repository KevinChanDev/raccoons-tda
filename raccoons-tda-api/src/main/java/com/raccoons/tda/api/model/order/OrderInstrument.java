package com.raccoons.tda.api.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.model.ApiModel;

public class OrderInstrument extends ApiModel {

    public static final String FACTOR = "factor";
    public static final String MATURITY_DATE = "maturityDate";
    public static final String VARIABLE_RATE = "variableRate";
    public static final String TYPE = "type";

    @JsonProperty
    private AssetType assetType;

    @JsonProperty
    private String cusip;

    @JsonProperty
    private String symbol;

    @JsonProperty
    private String description;

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
}

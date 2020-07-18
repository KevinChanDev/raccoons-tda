package com.raccoons.tda.api.model.instrument;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.model.instrument.Instrument;
import com.raccoons.tda.api.value.AssetType;
import com.raccoons.tda.api.value.MutualFundType;

public class MutualFund extends Instrument {

    @JsonProperty("cusip")
    private String cusip;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private MutualFundType type;

    public AssetType getAssetType() {
        return AssetType.MUTUAL_FUND;
    }
}

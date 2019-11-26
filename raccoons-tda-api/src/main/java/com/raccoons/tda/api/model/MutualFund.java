package com.raccoons.tda.api.model;

import com.raccoons.tda.api.value.AssetType;
import com.raccoons.tda.api.value.MutualFundType;

public class MutualFund extends Instrument {

    private String cusip;
    private String symbol;
    private String description;
    private MutualFundType type;

    public AssetType getAssetType() {
        return AssetType.MUTUAL_FUND;
    }
}

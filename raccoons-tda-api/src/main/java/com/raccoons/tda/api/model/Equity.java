package com.raccoons.tda.api.model;

import com.raccoons.tda.api.value.AssetType;

public class Equity extends Instrument {

    private String assetType;
    private String cusip;
    private String symbol;
    private String description;

    public AssetType getAssetType() {
        return AssetType.EQUITY;
    }
}

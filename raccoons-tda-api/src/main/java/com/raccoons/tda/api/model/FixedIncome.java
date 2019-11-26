package com.raccoons.tda.api.model;

import com.raccoons.tda.api.value.AssetType;

public class FixedIncome extends Instrument {

    public AssetType getAssetType() {
        return AssetType.FIXED_INCOME;
    }
}

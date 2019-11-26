package com.raccoons.tda.api.model;

import com.raccoons.tda.api.value.AssetType;

public class Option extends Instrument {

    public AssetType getAssetType() {
        return AssetType.OPTION;
    }

}

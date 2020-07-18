package com.raccoons.tda.api.model.instrument;

import com.raccoons.tda.api.value.AssetType;

public class Option extends Instrument {

    public AssetType getAssetType() {
        return AssetType.OPTION;
    }

}

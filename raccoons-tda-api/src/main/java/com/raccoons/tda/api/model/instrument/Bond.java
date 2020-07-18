package com.raccoons.tda.api.model.instrument;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.value.AssetType;

public class Bond extends Instrument {

    @JsonProperty("bondPrice")
    private Double bondPrice;

    @Override
    public AssetType getAssetType() {
        return assetType != null ? assetType : AssetType.BOND;
    }

}

package com.raccoons.tda.api.model.instrument;

import com.raccoons.tda.api.model.Fundamental;
import com.raccoons.tda.api.model.instrument.Instrument;
import com.raccoons.tda.api.value.AssetType;

public class Equity extends Instrument {

    private Fundamental fundamental;

    @Override
    public AssetType getAssetType() {
        return assetType != null ? assetType : AssetType.EQUITY;
    }

}

package com.raccoons.tda.api.model.instrument;

import com.raccoons.tda.api.value.AssetType;

public class CashEquivalent extends Instrument {

    public AssetType getAssetType() {
        return AssetType.CASH_EQUIVALENT;
    }

}

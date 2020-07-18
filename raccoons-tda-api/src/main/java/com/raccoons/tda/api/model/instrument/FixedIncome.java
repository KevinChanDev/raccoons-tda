package com.raccoons.tda.api.model.instrument;

import com.raccoons.tda.api.model.instrument.Instrument;
import com.raccoons.tda.api.value.AssetType;

public class FixedIncome extends Instrument {

    public AssetType getAssetType() {
        return AssetType.FIXED_INCOME;
    }
}

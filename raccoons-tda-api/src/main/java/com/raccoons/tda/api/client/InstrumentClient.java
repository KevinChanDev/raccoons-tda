package com.raccoons.tda.api.client;

import com.raccoons.tda.api.domain.AccountContext;
import com.raccoons.tda.api.model.instrument.Instrument;
import com.raccoons.tda.api.model.instrument.Instruments;
import com.raccoons.tda.api.response.TDAResponse;

public interface InstrumentClient {

    TDAResponse<Instrument> getInstrument(final AccountContext accountContext, final String cusip);
    TDAResponse<Instruments> searchInstrument(final AccountContext accountContext, final String symbol, final String projection);

}

package com.raccoons.tda.api.response;

import com.raccoons.tda.api.model.Instrument;
import com.raccoons.tda.api.request.ResponseType;

public class InstrumentResponse extends TDAResponse<Instrument> {

    public InstrumentResponse(TDAResponseStatus responseStatus, Instrument data) {
        super(responseStatus, data);
    }

    public InstrumentResponse(ResponseType responseType, long startTime, long endTime, Instrument data) {
        super(responseType, startTime, endTime, data);
    }
}

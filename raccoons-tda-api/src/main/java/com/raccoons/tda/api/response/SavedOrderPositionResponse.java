package com.raccoons.tda.api.response;

import com.raccoons.tda.api.request.ResponseType;

public class SavedOrderPositionResponse extends TDAResponse<Void> {

    public SavedOrderPositionResponse(TDAResponseStatus responseStatus, Void data) {
        super(responseStatus, data);
    }

    public SavedOrderPositionResponse(ResponseType responseType, long startTime, long endTime, Void data) {
        super(responseType, startTime, endTime, data);
    }
}

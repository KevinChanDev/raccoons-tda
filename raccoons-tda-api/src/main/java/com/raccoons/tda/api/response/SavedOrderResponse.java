package com.raccoons.tda.api.response;

public class SavedOrderResponse extends TDAResponse<Void> {

    public SavedOrderResponse(TDAResponseStatus responseStatus, Void data) {
        super(responseStatus, data);
    }

}

package com.raccoons.tda.api.response;

import com.raccoons.tda.api.model.Accounts;
import com.raccoons.tda.api.request.ResponseType;

public class AccountResponse extends TDAResponse<Accounts> {

    public AccountResponse(TDAResponseStatus responseStatus, Accounts data) {
        super(responseStatus, data);
    }

    public AccountResponse(ResponseType responseType, long startTime, long endTime, Accounts data) {
        super(responseType, startTime, endTime, data);
    }
}

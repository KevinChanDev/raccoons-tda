package com.raccoons.tda.api.response;

import com.raccoons.tda.api.model.Accounts;
import com.raccoons.tda.api.request.ResponseType;

public class AccountsResponse extends TDAResponse<Accounts> {

    public AccountsResponse(TDAResponseStatus responseStatus, Accounts data) {
        super(responseStatus, data);
    }

    public AccountsResponse(ResponseType responseType, long startTime, long endTime, Accounts data) {
        super(responseType, startTime, endTime, data);
    }

}

package com.raccoons.tda.api.response;

import com.raccoons.tda.api.model.UserPrincipal;
import com.raccoons.tda.api.request.ResponseType;

public class UserPrincipleResponse extends TDAResponse<UserPrincipal> {

    public UserPrincipleResponse(TDAResponseStatus responseStatus, UserPrincipal data) {
        super(responseStatus, data);
    }

    public UserPrincipleResponse(ResponseType responseType, long startTime, long endTime, UserPrincipal data) {
        super(responseType, startTime, endTime, data);
    }

}

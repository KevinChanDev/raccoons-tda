package com.raccoons.tda.api.client;

import com.raccoons.tda.api.model.EASObject;
import com.raccoons.tda.api.response.TDAResponse;

public interface AuthenticationClient {

    TDAResponse<EASObject> postAccessToken();

}

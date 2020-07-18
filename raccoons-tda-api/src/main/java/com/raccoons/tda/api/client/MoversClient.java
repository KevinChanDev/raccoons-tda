package com.raccoons.tda.api.client;

import com.raccoons.tda.api.domain.AccountContext;
import com.raccoons.tda.api.model.Movers;
import com.raccoons.tda.api.response.TDAResponse;

public interface MoversClient {

    TDAResponse<Movers> getMovers(final AccountContext accountContext, final String index);

}

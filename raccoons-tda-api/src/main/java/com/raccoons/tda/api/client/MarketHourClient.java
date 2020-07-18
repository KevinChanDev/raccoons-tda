package com.raccoons.tda.api.client;

import com.raccoons.tda.api.domain.AccountContext;
import com.raccoons.tda.api.model.Hour;
import com.raccoons.tda.api.model.Hours;
import com.raccoons.tda.api.response.TDAResponse;

public interface MarketHourClient {

    TDAResponse<Hours> getHoursForMultipleMarkets(final AccountContext accountContext, final String date, final String[] markets);

    TDAResponse<Hour> getHoursForASingleMarket(final AccountContext accountContext, final String date);

}

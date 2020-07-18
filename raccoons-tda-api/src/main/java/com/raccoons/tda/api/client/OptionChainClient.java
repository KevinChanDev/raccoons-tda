package com.raccoons.tda.api.client;

import com.raccoons.tda.api.domain.AccountContext;
import com.raccoons.tda.api.model.OptionChain;
import com.raccoons.tda.api.model.quote.OptionQuote;
import com.raccoons.tda.api.request.OptionChainRequest;
import com.raccoons.tda.api.response.TDAResponse;

public interface OptionChainClient {

    TDAResponse<OptionChain> getOptionChain(final AccountContext accountContext, final OptionChainRequest request);
}

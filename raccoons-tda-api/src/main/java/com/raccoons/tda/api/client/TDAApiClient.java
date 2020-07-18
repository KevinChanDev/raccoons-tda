package com.raccoons.tda.api.client;

import com.raccoons.tda.context.TDAContext;

public class TDAApiClient extends BaseApiClient {

    private final AccountApiClient accountClient;
    private final InstrumentApiClient instrumentClient;
    private final MarketHourApiClient marketHourClient;
    private final MoverApiClient moverClient;
    private final OptionChainApiClient optionChainClient;
    private final PriceHistoryApiClient priceHistoryClient;
    private final QuoteApiClient quoteClient;
    private final TransactionHistoryApiClient transactionHistoryClient;
    private final UserInfoApiClient userInfoClient;

    public TDAApiClient(TDAContext tdaContext) {
        super(tdaContext);
        this.accountClient = new AccountApiClient(tdaContext);
        this.instrumentClient = new InstrumentApiClient(tdaContext);
        this.marketHourClient = new MarketHourApiClient(tdaContext);
        this.moverClient = new MoverApiClient(tdaContext);
        this.optionChainClient = new OptionChainApiClient(tdaContext);
        this.priceHistoryClient = new PriceHistoryApiClient(tdaContext);
        this.quoteClient = new QuoteApiClient(tdaContext);
        this.transactionHistoryClient = new TransactionHistoryApiClient(tdaContext);
        this.userInfoClient = new UserInfoApiClient(tdaContext);
    }

    public AccountApiClient getAccountClient() {
        return accountClient;
    }

    public InstrumentApiClient getInstrumentClient() {
        return instrumentClient;
    }

    public MarketHourApiClient getMarketHourClient() {
        return marketHourClient;
    }

    public MoverApiClient getMoverClient() {
        return moverClient;
    }

    public OptionChainApiClient getOptionChainClient() {
        return optionChainClient;
    }

    public PriceHistoryApiClient getPriceHistoryClient() {
        return priceHistoryClient;
    }

    public QuoteApiClient getQuoteClient() {
        return quoteClient;
    }

    public TransactionHistoryApiClient getTransactionHistoryClient() {
        return transactionHistoryClient;
    }

    public UserInfoApiClient getUserInfoClient() {
        return userInfoClient;
    }
}

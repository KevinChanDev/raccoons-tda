package com.raccoons.tda.api.client;

import com.raccoons.tda.context.TDAContext;

public class TDAClient extends BaseClient {

    private final AccountClient accountClient;
    private final InstrumentClient instrumentClient;
    private final MarketHourClient marketHourClient;
    private final MoverClient moverClient;
    private final OptionChainClient optionChainClient;
    private final PriceHistoryClient priceHistoryClient;
    private final QuoteClient quoteClient;
    private final TransactionHistoryClient transactionHistoryClient;
    private final UserInfoClient userInfoClient;

    public TDAClient(TDAContext tdaContext) {
        super(tdaContext);
        this.accountClient = new AccountClient(tdaContext);
        this.instrumentClient = new InstrumentClient(tdaContext);
        this.marketHourClient = new MarketHourClient(tdaContext);
        this.moverClient = new MoverClient(tdaContext);
        this.optionChainClient = new OptionChainClient(tdaContext);
        this.priceHistoryClient = new PriceHistoryClient(tdaContext);
        this.quoteClient = new QuoteClient(tdaContext);
        this.transactionHistoryClient = new TransactionHistoryClient(tdaContext);
        this.userInfoClient = new UserInfoClient(tdaContext);
    }

    public AccountClient getAccountClient() {
        return accountClient;
    }

    public InstrumentClient getInstrumentClient() {
        return instrumentClient;
    }

    public MarketHourClient getMarketHourClient() {
        return marketHourClient;
    }

    public MoverClient getMoverClient() {
        return moverClient;
    }

    public OptionChainClient getOptionChainClient() {
        return optionChainClient;
    }

    public PriceHistoryClient getPriceHistoryClient() {
        return priceHistoryClient;
    }

    public QuoteClient getQuoteClient() {
        return quoteClient;
    }

    public TransactionHistoryClient getTransactionHistoryClient() {
        return transactionHistoryClient;
    }

    public UserInfoClient getUserInfoClient() {
        return userInfoClient;
    }
}

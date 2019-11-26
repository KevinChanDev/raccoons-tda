package com.raccoons.tda.api.client;

public class TDAClient {

    private final AccountClient accountClient;
    private final InstrumentClient instrumentClient;
    private final MarketHourClient marketHourClient;
    private final MoverClient moverClient;
    private final OptionChainClient optionChainClient;
    private final PriceHistoryClient priceHistoryClient;
    private final QuoteClient quoteClient;
    private final TransactionHistoryClient transactionHistoryClient;
    private final UserInfoClient userInfoClient;
    private final RequestClient requestClient;

    public TDAClient(RequestClient requestClient) {
        this.requestClient = requestClient;
        this.accountClient = new AccountClient(requestClient);
        this.instrumentClient = new InstrumentClient(requestClient);
        this.marketHourClient = new MarketHourClient(requestClient);
        this.moverClient = new MoverClient(requestClient);
        this.optionChainClient = new OptionChainClient(requestClient);
        this.priceHistoryClient = new PriceHistoryClient(requestClient);
        this.quoteClient = new QuoteClient(requestClient);
        this.transactionHistoryClient = new TransactionHistoryClient(requestClient);
        this.userInfoClient = new UserInfoClient(requestClient);
    }

    public AccountClient accountClient() {
        return accountClient;
    }

    public InstrumentClient instrumentClient() {
        return instrumentClient;
    }

    public MarketHourClient marketHourClient() {
        return marketHourClient;
    }

    public MoverClient moverClient() {
        return moverClient;
    }

    public OptionChainClient optionChainClient() {
        return optionChainClient;
    }

    public PriceHistoryClient priceHistoryClient() {
        return priceHistoryClient;
    }

    public QuoteClient quoteClient() {
        return quoteClient;
    }

    public TransactionHistoryClient transactionHistoryClient() {
        return transactionHistoryClient;
    }

    public UserInfoClient userInfoClient() {
        return userInfoClient;
    }

    public RequestClient getRequestClient() {
        return requestClient;
    }

    /*
    public static Accounts accounts(AccountContext accountContext) {
        return new Accounts(accountContext);
    }

    public static Instruments instruments(AccountContext accountContext) {
        return new Instruments(accountContext);
    }

    public static MarketHours marketHours(AccountContext accountContext) {
        return new MarketHours(accountContext);
    }

    public static Movers movers(AccountContext accountContext) {
        return new Movers(accountContext);
    }

    public static OptionChains optionChains(AccountContext accountContext) {
        return new OptionChains(accountContext);
    }

    public static PriceHistory priceHistory(AccountContext accountContext) {
        return new PriceHistory(accountContext);
    }

    public static Quotes quotes(AccountContext accountContext) {
        return new Quotes(accountContext);
    }

    public static TransactionHistory transactionHistory(AccountContext accountContext) {
        return new TransactionHistory(accountContext);
    }

    public static UserInfo userInfo(AccountContext accountContext) {
        return new UserInfo(accountContext);
    }
    */
}

package com.raccoons.tda.context;

public class TDAContextBuilder {

    private TDAClientProvider tdaClientProvider;
    private TDALoggerContext tdaLoggerContext;

    public TDAContextBuilder() {
        this.tdaLoggerContext = TDAContext.newEmptyLoggerContext();
    }

    public static TDAContextBuilder newBuilder() {
        return new TDAContextBuilder();
    }

    public TDAContextBuilder clientProvider(TDAClientProvider tdaClientProvider) {
        this.tdaClientProvider = tdaClientProvider;
        return this;
    }

    public TDAContextBuilder loggerContext(TDALoggerContext tdaLoggerContext) {
        this.tdaLoggerContext = tdaLoggerContext;
        return this;
    }

    public TDAContext build() {
        return new TDAContext(tdaClientProvider, tdaLoggerContext);
    }

}

package com.raccoons.tda.context;

public class TDAContext {

    private TDALoggerContext loggerContext;
    private TDAClientContext clientContext;

    public TDALoggerContext loggerContext() {
        if (loggerContext == null)
            loggerContext = null;
        return loggerContext;
    }

    public TDAClientContext clientContext() {
        if (clientContext == null)
            clientContext = null;
        return clientContext;
    }
}

package com.raccoons.tda.context;

import com.raccoons.tda.logging.EmptyTDAInstructionLogger;
import com.raccoons.tda.logging.EmptyTDALogger;
import com.raccoons.tda.logging.TDAInstructionLogger;
import com.raccoons.tda.logging.TDALogger;

public class TDAContext {

    private TDAClientProvider clientProvider;
    private TDALoggerContext loggerContext;

    public TDAContext(TDAClientProvider clientProvider) {
        this.clientProvider = clientProvider;
        this.loggerContext = TDAContext.newEmptyLoggerContext();
    }

    public TDAContext(TDAClientProvider clientProvider, TDALoggerContext loggerContext) {
        this.clientProvider = clientProvider;
        this.loggerContext = loggerContext;
    }

    public TDAClientProvider getClientProvider() {
        return clientProvider;
    }

    public TDALoggerContext getLoggerContext() {
        return loggerContext;
    }

    // Empty Logger Context Instance creator
    public static TDALoggerContext newEmptyLoggerContext() {
        return new EmptyLoggerContext();
    }

    private static class EmptyLoggerContext implements TDALoggerContext {

        @Override
        public TDALogger getLogger() {
            return new EmptyTDALogger();
        }

        @Override
        public TDAInstructionLogger getInstructionLogger() {
            return new EmptyTDAInstructionLogger();
        }
    }
}

package com.raccoons.tda.context;

import com.raccoons.tda.logging.TDAInstructionLogger;
import com.raccoons.tda.logging.TDALogger;

public class EmptyTDALoggerContext implements TDALoggerContext {

    public static EmptyTDALoggerContext newInstance() {
        return new EmptyTDALoggerContext();
    }

    @Override
    public TDALogger getLogger() {
        return null;
    }

    @Override
    public TDAInstructionLogger getInstructionLogger() {
        return null;
    }
}

package com.raccoons.tda.context;

import com.raccoons.tda.logging.TDAInstructionLogger;
import com.raccoons.tda.logging.TDALogger;

public class BaseTDALoggerContext implements TDALoggerContext {

    private TDALogger tdaLogger;
    private TDAInstructionLogger tdaInstructionLogger;

    private BaseTDALoggerContext(TDALogger tdaLogger, TDAInstructionLogger tdaInstructionLogger) {
        this.tdaLogger = tdaLogger;
        this.tdaInstructionLogger = tdaInstructionLogger;
    }

    public static BaseTDALoggerContext newInstance(TDALogger tdaLogger, TDAInstructionLogger tdaInstructionLogger){
        return new BaseTDALoggerContext(tdaLogger, tdaInstructionLogger);
    }

    @Override
    public TDALogger getLogger() {
        return tdaLogger;
    }

    @Override
    public TDAInstructionLogger getInstructionLogger() {
        return tdaInstructionLogger;
    }
}

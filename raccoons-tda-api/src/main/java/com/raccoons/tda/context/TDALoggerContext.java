package com.raccoons.tda.context;

import com.raccoons.tda.logging.TDAInstructionLogger;
import com.raccoons.tda.logging.TDALogger;

public interface TDALoggerContext {

    TDALogger getLogger();
    TDAInstructionLogger getInstructionLogger();

}

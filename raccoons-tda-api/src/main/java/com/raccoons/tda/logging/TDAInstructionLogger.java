package com.raccoons.tda.logging;

import java.util.Map;

public interface TDAInstructionLogger {

    void log(Map<String, Object> values);
    boolean isEnabled();

}

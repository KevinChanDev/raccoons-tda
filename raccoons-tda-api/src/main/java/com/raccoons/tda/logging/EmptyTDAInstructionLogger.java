package com.raccoons.tda.logging;

import java.util.Map;

public class EmptyTDAInstructionLogger implements TDAInstructionLogger {

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void log(Map<String, Object> values) {

    }

}

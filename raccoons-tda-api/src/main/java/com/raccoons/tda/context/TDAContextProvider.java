package com.raccoons.tda.context;

public class TDAContextProvider {

    private boolean initialized;

    public static TDAContext initialize() {
        return new TDAContext();
    }

}

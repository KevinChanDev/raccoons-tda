package com.raccoons.tda.context;

import com.raccoons.tda.net.TDAHttpClient;
import com.raccoons.tda.net.TDAWebSocketClient;

public class BaseTDAClientProvider implements TDAClientProvider {

    private TDAHttpClient tdaHttpClient;
    private TDAWebSocketClient tdaWebSocketClient;

    protected BaseTDAClientProvider(TDAHttpClient tdaHttpClient, TDAWebSocketClient tdaWebSocketClient) {
        this.tdaHttpClient = tdaHttpClient;
        this.tdaWebSocketClient = tdaWebSocketClient;
    }

    public static BaseTDAClientProvider newInstance(TDAHttpClient tdaHttpClient, TDAWebSocketClient tdaWebSocketClient) {
        return new BaseTDAClientProvider(tdaHttpClient, tdaWebSocketClient);
    }

    @Override
    public TDAHttpClient getTDAHttpClient() {
        return tdaHttpClient;
    }

    @Override
    public TDAWebSocketClient getTDAWebSocketClient() {
        return tdaWebSocketClient;
    }

}

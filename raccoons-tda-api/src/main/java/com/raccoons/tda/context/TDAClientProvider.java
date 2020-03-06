package com.raccoons.tda.context;

import com.raccoons.tda.net.TDAHttpClient;
import com.raccoons.tda.net.TDAWebSocketClient;

public interface TDAClientContext {

    TDAHttpClient getTDAHttpClient();
    TDAWebSocketClient getTDAWebSocketClient();

}

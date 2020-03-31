package com.raccoons.tda.context;

import com.raccoons.tda.net.TDAHttpClient;
import com.raccoons.tda.net.TDAWebSocketClient;

public interface TDAClientProvider {

    TDAHttpClient getTDAHttpClient();
    TDAWebSocketClient getTDAWebSocketClient();

}

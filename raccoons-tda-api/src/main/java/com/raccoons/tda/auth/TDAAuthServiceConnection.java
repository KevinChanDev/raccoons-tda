package com.raccoons.tda.auth;

import com.raccoons.tda.net.TDAHttpClient;

import java.io.Closeable;

public class TDAAuthServiceConnection implements Closeable {

    private String connectionEndpoint;
    private String connectionToken;
    private TDAHttpClient tdaHttpClient;

    public TDAAuthServiceConnection(TDAHttpClient tdaHttpClient, String username, String password, String connectionEndpoint) {
        this.tdaHttpClient = tdaHttpClient;
        this.connectionEndpoint = connectionEndpoint;
    }

    public AccessTokenProvider getAccessTokenProvider() {
        return null;
    }

    @Override
    public void close(){

    }
}

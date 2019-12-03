package com.raccoons.tda.api.client;

import com.raccoons.tda.net.TDAHttpClient;

public class RequestClient {

    private TDAHttpClient httpClient;
    private Settings settings;

    public RequestClient(TDAHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public TDAHttpClient getHttpClient() {
        return httpClient;
    }

    public static class Settings {

        private boolean referenceRequest;
        private boolean referenceHttpResponse;

    }

}

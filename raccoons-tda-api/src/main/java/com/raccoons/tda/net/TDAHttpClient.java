package com.raccoons.tda.net;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface TDAHttpClient {

    CompletableFuture<TDAHttpResponse> get(String uri, Map<String, String> headers);

    CompletableFuture<TDAHttpResponse> get(String uri, Map<String, String> headers, Map<String, String> parameters);

    CompletableFuture<TDAHttpResponse> post(String uri, Map<String, String> headers);

    CompletableFuture<TDAHttpResponse> post(String uri, Map<String, String> headers, Map<String, Object> data);

    CompletableFuture<TDAHttpResponse> post(String uri, Map<String, String> headers, byte[] body);

    CompletableFuture<TDAHttpResponse> put(String uri, Map<String, String> headers);

    CompletableFuture<TDAHttpResponse> put(String uri, Map<String, String> headers, byte[] body);

    CompletableFuture<TDAHttpResponse> delete(String uri, Map<String, String> headers);

    CompletableFuture<TDAHttpResponse> postJson(String uri, Map<String, String> headers, String content);

    CompletableFuture<TDAHttpResponse> putJson(String uri, Map<String, String> headers, String content);

}

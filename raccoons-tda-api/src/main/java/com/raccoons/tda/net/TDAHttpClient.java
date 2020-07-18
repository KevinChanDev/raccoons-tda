package com.raccoons.tda.net;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface TDAHttpClient {

    TDAHttpResponse get(String uri, Map<String, String> headers);

    TDAHttpResponse get(String uri, Map<String, String> headers, Map<String, String> parameters);

    TDAHttpResponse post(String uri, Map<String, String> headers);

    TDAHttpResponse post(String uri, Map<String, String> headers, Map<String, Object> data);

    TDAHttpResponse post(String uri, Map<String, String> headers, byte[] body);

    TDAHttpResponse put(String uri, Map<String, String> headers);

    TDAHttpResponse put(String uri, Map<String, String> headers, byte[] body);

    TDAHttpResponse delete(String uri, Map<String, String> headers);

    TDAHttpResponse postJson(String uri, Map<String, String> headers, String content);

    TDAHttpResponse putJson(String uri, Map<String, String> headers, String content);

    CompletableFuture<TDAHttpResponse> getAsync(String uri, Map<String, String> headers);

    CompletableFuture<TDAHttpResponse> getAsync(String uri, Map<String, String> headers, Map<String, String> parameters);

    CompletableFuture<TDAHttpResponse> postAsync(String uri, Map<String, String> headers);

    CompletableFuture<TDAHttpResponse> postAsync(String uri, Map<String, String> headers, Map<String, Object> data);

    CompletableFuture<TDAHttpResponse> postAsync(String uri, Map<String, String> headers, byte[] body);

    CompletableFuture<TDAHttpResponse> putAsync(String uri, Map<String, String> headers);

    CompletableFuture<TDAHttpResponse> putAsync(String uri, Map<String, String> headers, byte[] body);

    CompletableFuture<TDAHttpResponse> deleteAsync(String uri, Map<String, String> headers);

    CompletableFuture<TDAHttpResponse> postJsonAsync(String uri, Map<String, String> headers, String content);

    CompletableFuture<TDAHttpResponse> putJsonAsync(String uri, Map<String, String> headers, String content);

}

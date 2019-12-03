package com.raccoons.tda.net;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AsyncTDAHttpClient implements TDAHttpClient {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private final HttpClient sharedHttpClient;

    public AsyncTDAHttpClient() {
        this.sharedHttpClient = HttpClient.newHttpClient();
    }

    public AsyncTDAHttpClient(Executor executor) {
        this.sharedHttpClient = HttpClient.newBuilder().executor(executor).build();
    }

    @Override
    public CompletableFuture<TDAHttpResponse> get(String uri, Map<String, String> headers) {
        return get(uri, headers, null);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> get(String uri, Map<String, String> headers, Map<String, String> parameters) {
        URI requestUri = null;
        try {
            final URIBuilder uriBuilder = new URIBuilder(uri);
            if (parameters != null) {
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            requestUri = uriBuilder.build();
        } catch (URISyntaxException e) {
        }
        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().GET().uri(requestUri);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build());
    }

    @Override
    public CompletableFuture<TDAHttpResponse> post(String uri, Map<String, String> headers) {
        return post(uri, headers, new byte[0]);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> post(String uri, Map<String, String> headers, Map<String, Object> data) {
        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().POST(ofFormData(data)).uri(URI.create(uri));
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build());
    }

    @Override
    public CompletableFuture<TDAHttpResponse> post(String uri, Map<String, String> headers, byte[] body) {
        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().POST(
                HttpRequest.BodyPublishers.ofByteArray(body)
        ).uri(URI.create(uri));
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build());
    }

    @Override
    public CompletableFuture<TDAHttpResponse> put(String uri, Map<String, String> headers) {
        return put(uri, headers, new byte[0]);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> put(String uri, Map<String, String> headers, byte[] body) {
        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().PUT(
                HttpRequest.BodyPublishers.ofByteArray(body)
        ).uri(URI.create(uri));
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build());
    }

    @Override
    public CompletableFuture<TDAHttpResponse> delete(String uri, Map<String, String> headers) {
        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().DELETE().uri(URI.create(uri));
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build());
    }

    @Override
    public CompletableFuture<TDAHttpResponse> postJson(String uri, Map<String, String> headers, String content) {
        final Map<String, String> jsonHeaders = new HashMap<>(headers);
        jsonHeaders.put(CONTENT_TYPE, APPLICATION_JSON);
        return post(uri, jsonHeaders, content.getBytes());
    }

    @Override
    public CompletableFuture<TDAHttpResponse> putJson(String uri, Map<String, String> headers, String content) {
        final Map<String, String> jsonHeaders = new HashMap<>(headers);
        jsonHeaders.put(CONTENT_TYPE, APPLICATION_JSON);
        return put(uri, jsonHeaders, content.getBytes());
    }

    private CompletableFuture<TDAHttpResponse> sendAsync(final HttpRequest httpRequest) {
        final long requestStartTime = System.currentTimeMillis();
        return sharedHttpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(httpResponse -> {
                    final int statusCode = httpResponse.statusCode();
                    final Map<String, List<String>> responseHeaders = httpResponse.headers().map();
                    final Map<String, String> flattenedResponseHeaders = new HashMap<>();
                    for (Map.Entry<String, List<String>> entry : responseHeaders.entrySet()) {
                        flattenedResponseHeaders.put(entry.getKey(), String.join(";", entry.getValue()));
                    }
                    final byte[] responseBody = httpResponse.body();
                    return new TDAHttpResponse(statusCode, flattenedResponseHeaders, responseBody,
                            requestStartTime, System.currentTimeMillis());
                });
    }

    private static HttpRequest.BodyPublisher ofFormData(Map<String, Object> data) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

}

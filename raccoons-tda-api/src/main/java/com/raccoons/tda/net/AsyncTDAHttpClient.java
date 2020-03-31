package com.raccoons.tda.net;

import com.raccoons.tda.context.EmptyTDALoggerContext;
import com.raccoons.tda.context.TDALoggerContext;
import com.raccoons.tda.logging.TDAInstructionLogger;
import com.raccoons.tda.logging.TDALogger;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class AsyncTDAHttpClient implements TDAHttpClient {

    private static final long DEFAULT_TIMEOUT = 30000;
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private final AtomicLong requestId;
    private final HttpClient sharedHttpClient;

    private final TDALoggerContext tdaLoggerContext;
    private final TDALogger tdaLogger;
    private final TDAInstructionLogger tdaInstructionLogger;

    private Settings settings;

    public AsyncTDAHttpClient() {
        this(Settings.newBuilder().build(), EmptyTDALoggerContext.newInstance());
    }

    public AsyncTDAHttpClient(final TDALoggerContext tdaLoggerContext) {
        this(Settings.newBuilder().build(), tdaLoggerContext);
    }

    public AsyncTDAHttpClient(final Settings settings, final TDALoggerContext tdaLoggerContext) {
        this.settings = settings;
        this.tdaLoggerContext = tdaLoggerContext;
        this.sharedHttpClient = HttpClient.newHttpClient();
        this.requestId = new AtomicLong();
        this.tdaLogger = tdaLoggerContext.getLogger();
        this.tdaInstructionLogger = tdaLoggerContext.getInstructionLogger();
    }

    public AsyncTDAHttpClient(final Settings settings, final Executor executor) {
        this(settings, executor, EmptyTDALoggerContext.newInstance());
    }

    public AsyncTDAHttpClient(final Settings settings, final Executor executor, final TDALoggerContext tdaLoggerContext) {
        this.settings = settings;
        this.tdaLoggerContext = tdaLoggerContext;
        this.sharedHttpClient = HttpClient.newBuilder().executor(executor).build();
        this.requestId = new AtomicLong();
        this.tdaLogger = tdaLoggerContext.getLogger();
        this.tdaInstructionLogger = tdaLoggerContext.getInstructionLogger();
    }

    @Override
    public CompletableFuture<TDAHttpResponse> get(final String uri, final Map<String, String> headers) {
        return get(uri, headers, null);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> get(final String uri, final Map<String, String> headers, final Map<String, String> parameters) {
        final long httpRequestId = requestId.incrementAndGet();

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
            if (tdaLogger.isErrorEnabled()) {
                tdaLogger.error("Error occurred while building URI for a GET request of id " + httpRequestId + ".", e);
            }
        }

        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().GET().uri(requestUri);

        if (tdaLogger.isTraceEnabled()) {
            tdaLogger.trace(String.format("URI %s built for GET request of id %d.", requestUri, httpRequestId));
        }

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build(), httpRequestId);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> post(final String uri, final Map<String, String> headers) {
        return post(uri, headers, new byte[0]);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> post(final String uri, final Map<String, String> headers, final Map<String, Object> data) {
        final long httpRequestId = requestId.incrementAndGet();

        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().POST(ofFormData(data)).uri(URI.create(uri));

        if (tdaLogger.isTraceEnabled()) {
            tdaLogger.trace(String.format("URI %s built for POST request of id %d.", uri, httpRequestId));
        }

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build(), httpRequestId);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> post(final String uri, final Map<String, String> headers, final byte[] body) {
        final long httpRequestId = requestId.incrementAndGet();

        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().POST(
                HttpRequest.BodyPublishers.ofByteArray(body)
        ).uri(URI.create(uri));

        if (tdaLogger.isTraceEnabled()) {
            tdaLogger.trace(String.format("URI %s built for POST request of id %d with a content size of %d.", uri,
                    httpRequestId, body.length));
        }

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build(), httpRequestId);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> put(final String uri, final Map<String, String> headers) {
        return put(uri, headers, new byte[0]);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> put(final String uri, final Map<String, String> headers, final byte[] body) {
        final long httpRequestId = requestId.incrementAndGet();

        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().PUT(
                HttpRequest.BodyPublishers.ofByteArray(body)
        ).uri(URI.create(uri));

        if (tdaLogger.isTraceEnabled()) {
            tdaLogger.trace(String.format("URI %s built for PUT request of id %d with a content size of %d.", uri,
                    httpRequestId, body.length));
        }

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build(), httpRequestId);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> delete(final String uri, final Map<String, String> headers) {
        final long httpRequestId = requestId.incrementAndGet();

        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().DELETE().uri(URI.create(uri));

        if (tdaLogger.isTraceEnabled()) {
            tdaLogger.trace(String.format("URI %s built for DELETE request of id %d.", uri, httpRequestId));
        }

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return sendAsync(requestBuilder.build(), httpRequestId);
    }

    @Override
    public CompletableFuture<TDAHttpResponse> postJson(final String uri, final Map<String, String> headers, final String content) {
        final Map<String, String> jsonHeaders = new HashMap<>(headers);
        jsonHeaders.put(CONTENT_TYPE, APPLICATION_JSON);
        return post(uri, jsonHeaders, content.getBytes());
    }

    @Override
    public CompletableFuture<TDAHttpResponse> putJson(final String uri, final Map<String, String> headers, final String content) {
        final Map<String, String> jsonHeaders = new HashMap<>(headers);
        jsonHeaders.put(CONTENT_TYPE, APPLICATION_JSON);
        return put(uri, jsonHeaders, content.getBytes());
    }

    private CompletableFuture<TDAHttpResponse> sendAsync(final HttpRequest httpRequest, final long requestId) {
        final long requestStartTime = System.currentTimeMillis();
        final boolean isTraceEnabled = tdaLogger.isTraceEnabled();

        if (isTraceEnabled) {
            tdaLogger.trace(String.format("Sending Async %s request of id %d.", httpRequest.method(), requestId));
        }

        return sharedHttpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(httpResponse -> {
                    final int statusCode = httpResponse.statusCode();
                    final Map<String, List<String>> responseHeaders = httpResponse.headers().map();
                    final Map<String, String> flattenedResponseHeaders = new HashMap<>();

                    for (Map.Entry<String, List<String>> entry : responseHeaders.entrySet()) {
                        flattenedResponseHeaders.put(entry.getKey(), String.join(";", entry.getValue()));
                    }

                    final byte[] responseBody = httpResponse.body();

                    if (isTraceEnabled) {
                        tdaLogger.trace(String.format("Request of id %d returned status %d with a content size of %d.",
                                requestId, statusCode, responseBody.length));
                    }

                    return new TDAHttpResponse(TDAHttpResponse.State.COMPLETED, statusCode, flattenedResponseHeaders,
                            responseBody, requestId, requestStartTime, System.currentTimeMillis());
                }).completeOnTimeout(((Supplier<TDAHttpResponse>) () -> {
                    if (isTraceEnabled) {
                        tdaLogger.trace("Request of id " + requestId + " has timed out.");
                    }
                    return new TDAHttpResponse(TDAHttpResponse.State.TIMED_OUT, -1, Collections.emptyMap(),
                            new byte[0], requestId, requestStartTime, System.currentTimeMillis());
                }).get(), settings.getTimeout(), TimeUnit.MILLISECONDS);
    }

    private static HttpRequest.BodyPublisher ofFormData(final Map<String, Object> data) {
        final StringBuilder builder = new StringBuilder();

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

    public static class Settings {

        private long timeout;

        public Settings(long timeout) {
            this.timeout = timeout;
        }

        public long getTimeout() {
            return timeout;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class Builder {

            private long timeout = DEFAULT_TIMEOUT;

            public Builder setTimeout(long timeout) {
                this.timeout = timeout;
                return this;
            }

            public Settings build() {
                return new Settings(timeout);
            }

        }
    }

}

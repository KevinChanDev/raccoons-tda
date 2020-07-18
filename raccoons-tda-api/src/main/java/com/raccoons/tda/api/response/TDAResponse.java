package com.raccoons.tda.api.response;

import com.raccoons.tda.api.request.TDAResponseType;
import com.raccoons.tda.api.request.TDARequest;
import com.raccoons.tda.net.TDAHttpResponse;

public class TDAResponse<T> {

    private final String requestId;
    private final TDAResponseType tdaResponseType;
    private final TDAResponseStatus responseStatus;
    private final long startTime;
    private final long endTime;
    private final T data;

    private final TDARequest tdaRequest;
    private final TDAHttpResponse tdaHttpResponse;

    public TDAResponse() {
        this.requestId = null;
        this.tdaResponseType = null;
        this.responseStatus = null;
        this.startTime = -1;
        this.endTime = -1;
        this.data = null;
        this.tdaRequest = null;
        this.tdaHttpResponse = null;
    }

    public TDAResponse(String requestId, TDAResponseType tdaResponseType, TDAResponseStatus responseStatus,
                       long startTime, long endTime, T data, TDARequest tdaRequest,
                       TDAHttpResponse tdaHttpResponse) {
        this.requestId = requestId;
        this.tdaResponseType = tdaResponseType;
        this.responseStatus = responseStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.data = data;
        this.tdaRequest = tdaRequest;
        this.tdaHttpResponse = tdaHttpResponse;
    }

    public TDARequest getTDARequest() {
        return tdaRequest;
    }

    public TDAHttpResponse getTdaHttpResponse() {
        return tdaHttpResponse;
    }

    public String getRequestId() {
        return requestId;
    }

    public TDAResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public TDAResponseType getTDAResponseType() {
        return tdaResponseType;
    }

    public T getData() {
        return data;
    }

    public static <T> Builder<T> newBuilder() {
        return new Builder<>();
    }

    public static final class Builder<T> {

        private TDARequest tdaRequest;
        private TDAHttpResponse tdaHttpResponse;
        private String requestId;
        private TDAResponseType tdaResponseType;
        private TDAResponseStatus responseStatus;
        private long startTime;
        private long endTime;
        private T data;

        public Builder<T> tdaRequest(final TDARequest tdaRequest) {
            this.tdaRequest = tdaRequest;
            return this;
        }

        public Builder<T> tdaHttpResponse(final TDAHttpResponse tdaHttpResponse) {
            this.tdaHttpResponse = tdaHttpResponse;
            return this;
        }

        public Builder<T> requestId(final String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder<T> responseType(final TDAResponseType tdaResponseType) {
            this.tdaResponseType = tdaResponseType;
            return this;
        }

        public Builder<T> responseStatus(final TDAResponseStatus responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public Builder<T> startTime(final long startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder<T> endTime(final long endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder<T> data(final T data) {
            this.data = data;
            return this;
        }

        public TDAResponse<T> build() {
            return new TDAResponse<>(requestId, tdaResponseType, responseStatus, startTime, endTime, data,
                    tdaRequest, tdaHttpResponse);
        }

    }
}

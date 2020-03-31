package com.raccoons.auth.lib.message;

import java.util.Map;

public class ServiceMessage {

    private long id;
    private Map<String, Object> payload;
    private long timestamp;
    private int messageType;

    private ServiceMessage() {
    }

    private ServiceMessage(Builder builder) {
        this.id = builder.id;
        this.timestamp = builder.timestamp;
        this.messageType = builder.messageType;
        this.payload = builder.payload;
    }

    public long getId() {
        return id;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getMessageType() {
        return messageType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private long id;
        private Map<String, Object> payload;
        private long timestamp;
        private int messageType;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder payload(Map<String, Object> payload) {
            this.payload = payload;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder messageType(int messageType) {
            this.messageType = messageType;
            return this;
        }

        public ServiceMessage build() {
            return new ServiceMessage(this);
        }
    }

}

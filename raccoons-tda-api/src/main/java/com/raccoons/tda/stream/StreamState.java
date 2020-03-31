package com.raccoons.tda.stream;

import java.util.concurrent.atomic.AtomicLong;

public class StreamState {

    private AtomicLong inboundMessages;
    private AtomicLong outboundMessages;

    public StreamState() {
        this.inboundMessages = new AtomicLong();
        this.outboundMessages = new AtomicLong();
    }

}

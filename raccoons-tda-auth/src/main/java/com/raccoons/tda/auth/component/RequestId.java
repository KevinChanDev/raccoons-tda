package com.raccoons.tda.auth.component;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RequestId {

    private AtomicLong requestId;

    @PostConstruct
    public void init(){
        requestId = new AtomicLong();
    }

    public long getId(){
        return requestId.incrementAndGet();
    }

}

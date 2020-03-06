package com.raccoons.tda.auth.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StatusService {

    private long applicationStartTime;

    @PostConstruct
    public void init() {
        applicationStartTime = System.currentTimeMillis();
    }

    public long getUpTime() {
        return System.currentTimeMillis() - applicationStartTime;
    }
}

package com.raccoons.tda.auth.service.token;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccessTokenDetailService {

    // Owner, Time to expiration
    private Map<String, Long> expirationTime;
    private Map<String, Long> scheduledRefreshTime;

    @PostConstruct
    public void init() {
        expirationTime = new ConcurrentHashMap<>();
        scheduledRefreshTime = new ConcurrentHashMap<>();
    }

    public Optional<Long> getExpirationTime(final String owner) {
        return Optional.ofNullable(expirationTime.get(owner));
    }

    public Optional<Long> getScheduledRefreshTime(final String owner) {
        return Optional.ofNullable(scheduledRefreshTime.get(owner));
    }

    public void putExpirationTime(final String owner, final long expiration) {
        expirationTime.put(owner, expiration);
    }

    public void putScheduledRefreshTime(final String owner, final long expiration) {
        scheduledRefreshTime.put(owner, expiration);
    }

}

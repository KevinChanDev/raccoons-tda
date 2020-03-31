package com.raccoons.tda.auth.service.security;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class PermissionService {

    private Map<String, Set<String>> owners;
    private Map<String, List<String>> users;

    @PostConstruct
    public void init() {
        owners = new ConcurrentHashMap<>();
    }

    public void registerOwner(final String user, final String owner) {
        final Set<String> set = owners.computeIfAbsent(user, s -> new ConcurrentSkipListSet<>());
        set.add(owner);
    }

}

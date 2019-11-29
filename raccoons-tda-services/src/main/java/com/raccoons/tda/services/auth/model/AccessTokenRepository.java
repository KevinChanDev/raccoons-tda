package com.raccoons.tda.services.auth.model;

import java.time.Instant;
import java.util.Map;

public class AccessTokenRepository {

    private Map<String, AccessToken> accessTokens;

    public AccessToken get(String key){
        return accessTokens.get(key);
    }

    public void delete(String key){

    }

    public void prune(){
        final Instant time = Instant.now();
    }

}

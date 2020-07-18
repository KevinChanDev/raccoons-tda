package com.raccoons.tda.api.domain;

import com.raccoons.tda.api.auth.Authorization;

import java.util.HashMap;
import java.util.Map;

public class AccountContext {

    private String accountId;
    private String accessToken;

    public AccountContext(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccountContext(String accountId, String accessToken) {
        this.accountId = accountId;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getBearerToken() {
        return Authorization.getBearerToken(accessToken);
    }

    public String getAccountId() {
        return accountId;
    }

    public void updateAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public Map<String, String> getAuthorizationHeader(){
        return new HashMap<String, String>(){{
            put("Authorization", getBearerToken());
        }};
    }
}

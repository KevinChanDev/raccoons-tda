package com.raccoons.tda.auth.security;

public class ApplicationSecretKey {

    private String secretKey;

    public ApplicationSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

}

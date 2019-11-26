package com.raccoons.tda.api.auth;

public class Authorization {

    public static final String HEADER = "Authorization";

    public static String getBearerToken(final String accessToken) {
        return "Bearer " + accessToken;
    }
}

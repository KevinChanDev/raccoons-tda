package com.raccoons.tda.auth.util;

import com.raccoons.tda.auth.model.token.AccessToken;

public class AccessTokens {

    public static boolean isExpired(final AccessToken accessToken) {
        final long currentTime = System.currentTimeMillis();
        final long expiration = accessToken.getAccessTokenExpiration();
        if (expiration <= 0) {
            return true;
        }
        return currentTime > expiration;
    }

    public static boolean isRefreshExpired(final AccessToken accessToken) {
        final long currentTime = System.currentTimeMillis();
        final long expiration = accessToken.getRefreshTokenExpiration();
        if (expiration <= 0) {
            return true;
        }
        return currentTime > expiration;
    }

}

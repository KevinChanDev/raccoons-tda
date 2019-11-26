package com.raccoons.tda.auth;

public interface AccessTokenProvider {

    String getAccessToken(String ownerKey);

    String refreshAccessToken(String ownerKey);

    String refreshAndGetAccessToken(String ownerKey);

    boolean revokeToken(String ownerKey);

    boolean invalidate(String ownerKey);

}

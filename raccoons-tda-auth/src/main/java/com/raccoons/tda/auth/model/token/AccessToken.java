package com.raccoons.tda.auth.model.token;


public class AccessToken {

    private final String owner;
    private final String accessToken;
    private final String refreshToken;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public AccessToken(String owner, String accessToken, String refreshToken, long accessTokenExpiration,
                       long refreshTokenExpiration) {
        this.owner = owner;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiration = Math.max(0, accessTokenExpiration);
        this.refreshTokenExpiration = Math.max(0, refreshTokenExpiration);
    }

    public String getOwner() {
        return owner;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public long getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private String owner;
        private String accessToken;
        private String refreshToken;
        private long accessTokenExpiration;
        private long refreshTokenExpiration;

        public Builder owner(final String owner) {
            this.owner = owner;
            return this;
        }

        public Builder accessToken(final String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder refreshToken(final String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder accessTokenExpiration(final long accessTokenExpiration) {
            this.accessTokenExpiration = accessTokenExpiration;
            return this;
        }

        public Builder refreshTokenExpiration(final long refreshTokenExpiration) {
            this.refreshTokenExpiration = refreshTokenExpiration;
            return this;
        }

        public AccessToken build() {
            return new AccessToken(owner, accessToken, refreshToken, accessTokenExpiration, refreshTokenExpiration);
        }
    }
}

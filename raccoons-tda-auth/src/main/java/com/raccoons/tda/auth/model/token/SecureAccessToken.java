package com.raccoons.tda.auth.model.token;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "updateOrInsertAccessToken", procedureName = "update_or_insert_access_token",
                resultClasses = SecureAccessToken.class)
})
public class SecureAccessToken {

    @Id
    @Column(unique = true, nullable = false)
    private String owner;

    @Column(length = 2048)
    private String accessToken;

    @Column(length = 2048)
    private String refreshToken;

    private Date accessTokenExpiration;
    private Date refreshTokenExpiration;

    @Enumerated(EnumType.STRING)
    private TokenState tokenState;

    public String getOwner() {
        return owner;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Date getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public Date getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setAccessTokenExpiration(Date accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public void setRefreshTokenExpiration(Date refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}

package com.raccoons.tda.auth.model.token;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SecureAccessToken {

    @Id
    private Long id;
    private String owner;
    private String accessToken;
    private String refreshToken;
    private Date accessTokenExpiration;
    private Date refreshTokenExpiration;

//    @Enumerated(EnumType.STRING)
//    private TokenState tokenState;

    public String getOwner() {
        return owner;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
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

    public Long getId() {
        return id;
    }

    public Date getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public Date getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }
}

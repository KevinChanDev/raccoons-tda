package com.raccoons.tda.auth.service.security;

import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.model.token.SecureAccessToken;
import com.raccoons.tda.auth.util.Tokens;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccessTokenSecurityService {

    private static final Logger logger = LogManager.getLogger(AccessTokenSecurityService.class);

    @Autowired
    private EncryptionService encryptionService;

    public String encryptAndWrapOwner(final String owner) {
        final String wrappedOwner = Tokens.wrapOwner(owner);
        return encryptionService.encryptValue(wrappedOwner);
    }

    public AccessToken decrypt(final SecureAccessToken secureAccessToken) {
        final AccessToken.Builder builder = AccessToken.newBuilder();

        final String secureOwner = secureAccessToken.getOwner();
        final String secureAccessTokenValue = secureAccessToken.getAccessToken();
        final String secureRefreshTokenValue = secureAccessToken.getRefreshToken();

        final Date accessTokenExpiration = secureAccessToken.getAccessTokenExpiration();
        final Date refreshTokenExpiration = secureAccessToken.getRefreshTokenExpiration();

        final long accessTokenExpirationMillis = accessTokenExpiration.getTime();
        final long refreshTokenExpirationMillis = refreshTokenExpiration.getTime();

        builder.accessTokenExpiration(accessTokenExpirationMillis);
        builder.refreshTokenExpiration(refreshTokenExpirationMillis);

        if (secureOwner != null) {
            final String decryptedOwner = encryptionService.decryptValue(secureOwner);
            final String extractedOwner = Tokens.extractOwner(decryptedOwner);
            builder.owner(extractedOwner);
        }

        if (secureAccessTokenValue != null) {
            final String decryptedAccessToken = encryptionService.decryptValue(secureAccessTokenValue);
            final String extractedToken = Tokens.extractToken(decryptedAccessToken);
            builder.accessToken(extractedToken);
        }

        if (secureRefreshTokenValue != null) {
            final String decryptedRefreshToken = encryptionService.decryptValue(secureRefreshTokenValue);
            final String extractedToken = Tokens.extractToken(decryptedRefreshToken);
            builder.refreshToken(extractedToken);
        }
        return builder.build();
    }

    public SecureAccessToken encrypt(final AccessToken accessToken) {
        if (accessToken == null) {
            logger.trace("Access token object provided was null returning an empty SecureAccessToken instance.");
            return new SecureAccessToken();
        }

        final SecureAccessToken secureAccessToken = new SecureAccessToken();

        final String owner = accessToken.getOwner();
        final String accessTokenValue = accessToken.getAccessToken();
        final String refreshTokenValue = accessToken.getRefreshToken();

        final long accessTokenExpiration = accessToken.getAccessTokenExpiration();
        final long refreshTokenExpiration = accessToken.getRefreshTokenExpiration();

        secureAccessToken.setAccessTokenExpiration(new Date(accessTokenExpiration));
        secureAccessToken.setRefreshTokenExpiration(new Date(refreshTokenExpiration));

        if (owner != null) {
            logger.trace("Encrypting owner value from AccessToken instance.");
            final String wrappedOwner = Tokens.wrapOwner(owner);
            final String encryptedOwner = encryptionService.encryptValue(wrappedOwner);
            secureAccessToken.setOwner(encryptedOwner);
        } else {
            logger.trace("Owner value from AccessToken instance is null.");
        }

        if (accessTokenValue != null) {
            logger.info("Encrypting access token value from AccessToken instance.");
            final String wrappedAccessToken = Tokens.wrapToken(accessTokenValue);
            final String encryptedAccessToken = encryptionService.encryptValue(wrappedAccessToken);
            secureAccessToken.setAccessToken(encryptedAccessToken);
        } else {
            logger.debug("Access token value from AccessToken instance is null.");
        }

        if (refreshTokenValue != null) {
            logger.info("Encrypting refresh token value from AccessToken instance.");
            final String wrappedRefreshToken = Tokens.wrapToken(refreshTokenValue);
            final String encryptedRefreshToken = encryptionService.encryptValue(wrappedRefreshToken);
            secureAccessToken.setRefreshToken(encryptedRefreshToken);
        } else {
            logger.debug("Refresh token value from AccessToken instance is null.");
        }
        return secureAccessToken;
    }

}

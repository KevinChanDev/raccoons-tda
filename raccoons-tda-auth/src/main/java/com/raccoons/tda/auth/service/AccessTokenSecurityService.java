package com.raccoons.tda.auth.service;

import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.model.token.SecureAccessToken;
import com.raccoons.tda.auth.model.token.TransientAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenSecurityService {

    private static final String OWNER_PREFIX = "OWNER{{";
    private static final String OWNER_SUFFIX = "}}";

    // Used to determine whether or not the encryption/decryption process was successful
    private static final String TOKEN_PREFIX = "TOKEN{{";
    private static final String TOKEN_SUFFIX = "}}";

    @Autowired
    private EncryptionService encryptionService;

    public AccessToken decrypt(final SecureAccessToken secureAccessToken) {
        final AccessToken accessToken = new AccessToken();

        final String secureOwner = secureAccessToken.getOwner();
        final String secureAccessTokenValue = secureAccessToken.getAccessToken();
        final String secureRefreshTokenValue = secureAccessToken.getRefreshToken();

        if (secureOwner != null) {
            final String decryptedOwner = encryptionService.decryptValue(secureOwner);
        }

        if (secureAccessTokenValue != null) {
            final String decryptedAccessToken = encryptionService.decryptValue(secureAccessTokenValue);
        }

        if (secureRefreshTokenValue != null) {
            final String decryptedRefreshToken = encryptionService.decryptValue(secureRefreshTokenValue);
        }
        return accessToken;
    }

    public TransientAccessToken convert(final AccessToken accessToken) {
        final TransientAccessToken transientAccessToken = new TransientAccessToken();

        return transientAccessToken;
    }

    public SecureAccessToken encrypt(final AccessToken accessToken) {
        final SecureAccessToken secureAccessToken = new SecureAccessToken();

        final String owner = secureAccessToken.getOwner();
        final String accessTokenValue = secureAccessToken.getAccessToken();
        final String refreshTokenValue = secureAccessToken.getRefreshToken();

        return secureAccessToken;
    }

}

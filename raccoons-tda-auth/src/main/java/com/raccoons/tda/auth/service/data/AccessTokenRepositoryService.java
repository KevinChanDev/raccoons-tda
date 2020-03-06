package com.raccoons.tda.auth.service.data;

import com.raccoons.tda.auth.model.token.AccessToken;
import com.raccoons.tda.auth.model.token.SecureAccessToken;
import com.raccoons.tda.auth.repository.SecureAccessTokenRepository;
import com.raccoons.tda.auth.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AccessTokenRepositoryService {

//    @Autowired
//    private SecureAccessTokenRepository secureAccessTokenRepository;

    @Autowired
    private EncryptionService encryptionService;

    public CompletableFuture<Optional<AccessToken>> getAccessToken(final String owner) {
        return null;
    }

    public CompletableFuture<String> storeAccessToken(final AccessToken accessToken) {
        return null;
    }

    public CompletableFuture<Void> deleteAccessToken(final String owner) {
        return null;
    }

    public void pruneRepository() {

    }

    private AccessToken decrypt(final SecureAccessToken secureAccessToken) {
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

    private SecureAccessToken encrypt(final AccessToken accessToken) {
        final SecureAccessToken secureAccessToken = new SecureAccessToken();

        final String owner = secureAccessToken.getOwner();
        final String accessTokenValue = secureAccessToken.getAccessToken();
        final String refreshTokenValue = secureAccessToken.getRefreshToken();

        return secureAccessToken;
    }
}

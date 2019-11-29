package com.raccoons.tda.services.auth.service;

import com.raccoons.tda.services.auth.util.AESCryptography;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    private String secretKey;

    public Optional<String> encryptValue(String value) {
        return AESCryptography.encrypt(value, secretKey);
    }

    public Optional<String> decryptValue(String value) {
        return AESCryptography.decrypt(value, secretKey);
    }

}

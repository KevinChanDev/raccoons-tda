package com.raccoons.tda.services.auth.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCryptography {

    private static final String DIGEST_ALGORITHM = "SHA-256";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static SecretKeySpec generateKeySpec(String myKey) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance(DIGEST_ALGORITHM);
        byte[] keyBytes = myKey.getBytes(StandardCharsets.UTF_8);
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static Optional<String> encrypt(String value, String secret) {
        try {
            SecretKeySpec keySpec = generateKeySpec(secret);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return Optional.of(Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<String> decrypt(String value, String secret) {
        try {
            SecretKeySpec keySpec = generateKeySpec(secret);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return Optional.of(new String(cipher.doFinal(Base64.getDecoder().decode(value))));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
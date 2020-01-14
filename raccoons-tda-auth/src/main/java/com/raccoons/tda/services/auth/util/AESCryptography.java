package com.raccoons.tda.services.auth.util;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCryptography {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    private static final String FACTORY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";

    private final String salt;

    public AESCryptography(String salt) {
        this.salt = salt;
    }

    public String encrypt(String value, String secret) {
        try {
            byte[] iv = new byte[16];
            final IvParameterSpec ivSpec = new IvParameterSpec(iv);
            final SecretKeyFactory factory = SecretKeyFactory.getInstance(FACTORY_ALGORITHM);
            final KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
            final SecretKey tmp = factory.generateSecret(spec);
            final SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), KEY_ALGORITHM);

            final Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            return null;
        }
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            byte[] iv = new byte[16];
            final IvParameterSpec ivSpec = new IvParameterSpec(iv);
            final SecretKeyFactory factory = SecretKeyFactory.getInstance(FACTORY_ALGORITHM);
            final KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
            final SecretKey tmp = factory.generateSecret(spec);
            final SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), KEY_ALGORITHM);

            final Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            return null;
        }
    }
}
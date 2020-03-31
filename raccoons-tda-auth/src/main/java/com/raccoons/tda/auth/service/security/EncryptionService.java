package com.raccoons.tda.auth.service.security;

import com.raccoons.tda.auth.security.ApplicationSecretKey;
import com.raccoons.tda.auth.util.AESCryptography;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EncryptionService {

    private static final Logger logger = LogManager.getLogger(EncryptionService.class);

    private String salt;

    @Autowired
    private ApplicationSecretKey applicationSecretKey;

    private AESCryptography aesCryptography;

    @PostConstruct
    public void init() {
        logger.trace("Initializing AESCryptography instance.");
        this.aesCryptography = new AESCryptography("z");
    }

    public String encryptValue(final String value) {
        logger.trace("Encrypting value of length {}.", value.length());
        return aesCryptography.encrypt(value, applicationSecretKey.getSecretKey());
    }

    public String decryptValue(final String value) {
        logger.trace("Decrypting value of length {}.", value.length());
        return aesCryptography.decrypt(value, applicationSecretKey.getSecretKey());
    }

}

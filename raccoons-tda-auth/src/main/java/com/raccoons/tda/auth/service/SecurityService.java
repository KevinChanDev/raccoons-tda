package com.raccoons.tda.auth.service;

import com.raccoons.tda.auth.security.ApplicationSecretKey;
import com.raccoons.tda.auth.util.AESCryptography;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SecurityService {

    private static final Logger logger = LogManager.getLogger(SecurityService.class);

    private String salt;

    @Autowired
    private ApplicationSecretKey applicationSecretKey;

    private AESCryptography aesCryptography;

    @PostConstruct
    public void init() {
        logger.info("Initializing AESCryptography instance");
        this.aesCryptography = new AESCryptography(salt);
    }

    public String encryptValue(String value) {
        return aesCryptography.encrypt(value, applicationSecretKey.getSecretKey());
    }

    public String decryptValue(String value) {
        return aesCryptography.decrypt(value, applicationSecretKey.getSecretKey());
    }

}

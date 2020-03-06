package com.raccoons.tda.auth.configuration;

import com.raccoons.tda.auth.security.ApplicationSecretKey;
import com.raccoons.tda.auth.security.SecurityVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.ConfigurationException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class SecretKeyConfiguration {

    private static final Logger logger = LogManager.getLogger(SecretKeyConfiguration.class);

    @Autowired
    private SecurityVariables securityVariables;

    @Bean
    public ApplicationSecretKey applicationSecretKey() throws ConfigurationException {
        final String secretKeyFilePath = securityVariables.getSecretKeyFilePath();
        if (secretKeyFilePath != null && !secretKeyFilePath.isEmpty()) {
            logger.info("Attempting to load secret key from file.");
            try {
                final byte[] data = Files.readAllBytes(Paths.get(secretKeyFilePath));
                if (data.length > 0) {
                    final ApplicationSecretKey applicationSecretKey = new ApplicationSecretKey(new String(data));
                    logger.info("Secret key successfully loaded from file.");
                    return applicationSecretKey;
                } else {
                    logger.error("The provided secret key file is empty.");
                    throw new ConfigurationException("The provided secret key file is empty.");
                }
            } catch (Exception e) {
                logger.error("Error occurred while loading secret key file.", e);
                throw new ConfigurationException("Error occurred while loading secret key file.");
            }
        } else {
            throw new ConfigurationException("A secret key file was not provided.");
        }
    }
}

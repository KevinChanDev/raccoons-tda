package com.raccoons.tda.auth.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityVariables {

    @Value("${tda.auth.secret.key.file}")
    private String secretKeyFilePath;

    public String getSecretKeyFilePath() {
        return secretKeyFilePath;
    }

}

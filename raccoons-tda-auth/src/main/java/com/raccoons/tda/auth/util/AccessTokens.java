package com.raccoons.tda.auth.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccessTokens {

    private static final Logger logger = LogManager.getLogger(AccessTokens.class);

    private static final String ACCESS_TOKEN_PREFIX = "TOKEN{{";
    private static final String ACCESS_TOKEN_SUFFIX = "}}";

    public static boolean isValidAccessToken(final String accessToken) {
        return accessToken.startsWith(ACCESS_TOKEN_PREFIX) && accessToken.endsWith(ACCESS_TOKEN_SUFFIX);
    }

    public static String extractToken(final String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return null;
        } else if (isValidAccessToken(accessToken)) {
            final int startIndex = ACCESS_TOKEN_PREFIX.length();
            final int endIndex = accessToken.length() - ACCESS_TOKEN_SUFFIX.length();
            return accessToken.substring(startIndex, endIndex);
        } else {
            logger.error("Could not extract access token, parameter is not a valid access token.");
            return null;
        }
    }

    public static String wrapToken(final String accessToken) {
        return ACCESS_TOKEN_PREFIX + accessToken + ACCESS_TOKEN_SUFFIX;
    }

}

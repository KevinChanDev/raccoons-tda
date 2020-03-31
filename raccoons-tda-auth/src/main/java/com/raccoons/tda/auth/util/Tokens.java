package com.raccoons.tda.auth.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tokens {

    private static final Logger logger = LogManager.getLogger(Tokens.class);

    private static final String OWNER_PREFIX = "OWNER{{";
    private static final String OWNER_SUFFIX = "}}";

    // Used to determine whether or not the encryption/decryption process was successful
    private static final String TOKEN_PREFIX = "TOKEN{{";
    private static final String TOKEN_SUFFIX = "}}";

    public static boolean isValidToken(final String token) {
        return token.startsWith(TOKEN_PREFIX) && token.endsWith(TOKEN_SUFFIX);
    }

    public static boolean isValidOwner(final String owner) {
        return owner.startsWith(OWNER_PREFIX) && owner.endsWith(OWNER_SUFFIX);
    }

    public static String extractToken(final String token) {
        if (token == null || token.isEmpty()) {
            return null;
        } else if (isValidToken(token)) {
            final int startIndex = TOKEN_PREFIX.length();
            final int endIndex = token.length() - TOKEN_SUFFIX.length();
            return token.substring(startIndex, endIndex);
        } else {
            logger.error("Could not extract access token, parameter is not a valid access token.");
            return null;
        }
    }

    public static String extractOwner(final String owner) {
        if (owner == null || owner.isEmpty()) {
            return null;
        } else if (isValidOwner(owner)) {
            final int startIndex = OWNER_PREFIX.length();
            final int endIndex = owner.length() - OWNER_SUFFIX.length();
            return owner.substring(startIndex, endIndex);
        } else {
            logger.error("Could not extract access token, parameter is not a valid access token.");
            return null;
        }
    }

    public static String wrapOwner(final String owner) {
        if (owner == null)
            return null;
        return OWNER_PREFIX + owner + OWNER_SUFFIX;
    }

    public static String wrapToken(final String token) {
        if (token == null)
            return null;
        return TOKEN_PREFIX + token + TOKEN_SUFFIX;
    }

}

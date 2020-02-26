package com.raccoons.tda.auth.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUserInfo {

    private static final String USER_AGENT = "User-Agent";

    public static String getRequestIpAddress(final HttpServletRequest request){
        return request.getRemoteAddr();
    }

    public static String getUserAgent(final HttpServletRequest request) {
        final String userAgent = request.getHeader(USER_AGENT);
        return userAgent != null ? userAgent : "";
    }

}

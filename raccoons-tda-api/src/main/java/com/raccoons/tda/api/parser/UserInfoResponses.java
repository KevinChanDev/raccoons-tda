package com.raccoons.tda.api.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.model.UserPrincipal;
import com.raccoons.tda.api.response.TDAResponseStatus;
import com.raccoons.tda.api.response.UserPrincipleResponse;
import com.raccoons.tda.net.TDAHttpResponse;

public class UserInfoResponses {

    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static UserPrincipleResponse getUserPrincipleResponse(final TDAHttpResponse tdaHttpResponse) {
        final String data = new String(tdaHttpResponse.getBody());
        final TypeReference<UserPrincipal> typeReference = new TypeReference<UserPrincipal>() {
        };

        try {
            final UserPrincipal userPrincipal = getObjectMapper().readValue(data, typeReference);
            return new UserPrincipleResponse(TDAResponseStatus.SUCCESSFUL, userPrincipal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserPrincipleResponse(TDAResponseStatus.SUCCESSFUL, null);
    }
}

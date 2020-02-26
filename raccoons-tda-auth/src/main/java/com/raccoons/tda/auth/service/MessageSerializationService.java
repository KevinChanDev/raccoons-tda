package com.raccoons.tda.auth.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.auth.lib.AccessTokenRequest;
import com.raccoons.auth.lib.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 */
@Service
public class MessageSerializationService {

    @Autowired
    private ObjectMapper objectMapper;

    private static TypeReference<AccessTokenRequest> REQUEST_TYPE_REF = new TypeReference<AccessTokenRequest>() {
    };

    public Optional<String> writeResponse(AccessTokenResponse response) {
        try {
            final String responseString = objectMapper.writeValueAsString(response);
            return Optional.of(responseString);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<AccessTokenRequest> writeRequest(final String requestString) {
        try {
            final AccessTokenRequest request = objectMapper.readValue(requestString, REQUEST_TYPE_REF);
            return Optional.of(request);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}

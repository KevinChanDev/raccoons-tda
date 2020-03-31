package com.raccoons.tda.auth.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.auth.lib.ServiceRequest;
import com.raccoons.auth.lib.message.ServiceMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The goal of this service is to convert Strings to AccessTokenResponse and AccessTokenRequests
 */
@Component
public class ServiceMessageSerializer {

    private static final Logger logger = LogManager.getLogger(ServiceMessageSerializer.class);

    @Autowired
    private ObjectMapper objectMapper;

    private static TypeReference<ServiceRequest> REQUEST_TYPE_REF = new TypeReference<ServiceRequest>() {
    };

    private static TypeReference<ServiceMessage> MESSAGE_TYPE_REF = new TypeReference<ServiceMessage>() {
    };

    public Optional<String> writeMessage(final ServiceMessage message) {
        try {
            final String responseString = objectMapper.writeValueAsString(message);
            return Optional.of(responseString);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
    }

    public Optional<ServiceMessage> readMessage(final String message) {
        try {
            final ServiceMessage serviceMessage = objectMapper.readValue(message, MESSAGE_TYPE_REF);
            return Optional.of(serviceMessage);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
    }

    public Optional<ServiceRequest> readRequest(final String message) {
        try {
            final ServiceRequest serviceRequest = objectMapper.readValue(message, REQUEST_TYPE_REF);
            return Optional.of(serviceRequest);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
    }

}

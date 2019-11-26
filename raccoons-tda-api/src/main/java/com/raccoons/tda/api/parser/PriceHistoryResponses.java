package com.raccoons.tda.api.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PriceHistoryResponses {

    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper(){
        if (objectMapper == null)
            objectMapper = new ObjectMapper();
        return objectMapper;
    }

}

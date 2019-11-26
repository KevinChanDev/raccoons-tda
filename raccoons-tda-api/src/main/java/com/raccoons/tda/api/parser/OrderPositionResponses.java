package com.raccoons.tda.api.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.response.OrderPositionResponse;
import com.raccoons.tda.api.response.TDAResponseStatus;
import com.raccoons.tda.net.TDAHttpResponse;

public class OrderPositionResponses {

    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper(){
        if (objectMapper == null)
            objectMapper = new ObjectMapper();
        return objectMapper;
    }

    public static OrderPositionResponse getOrderPositionResponse(final TDAHttpResponse tdaHttpResponse){
        System.out.println(new String(tdaHttpResponse.getBody()));
        return new OrderPositionResponse(TDAResponseStatus.SUCCESSFUL, null);
    }

}

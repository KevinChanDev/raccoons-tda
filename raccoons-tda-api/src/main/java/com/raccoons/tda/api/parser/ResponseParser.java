package com.raccoons.tda.api.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoons.tda.api.response.TDAResponse;
import com.raccoons.tda.net.TDAHttpResponse;

public class ResponseParser {

    protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public <T extends TDAResponse<?>> T parse(final TDAHttpResponse tdaHttpResponse) {
        return null;
    }

}

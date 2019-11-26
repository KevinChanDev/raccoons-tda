package com.raccoons.tda.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.raccoons.tda.api.model.SecuritiesAccount;

import java.io.IOException;

public class SecuritiesAccountDeserializer extends StdDeserializer<SecuritiesAccount> {

    protected SecuritiesAccountDeserializer(Class<?> vc) {
        super(vc);
    }

    protected SecuritiesAccountDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public SecuritiesAccount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);

        return null;
    }
}

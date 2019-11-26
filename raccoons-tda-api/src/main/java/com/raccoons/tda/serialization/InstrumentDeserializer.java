package com.raccoons.tda.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.raccoons.tda.api.model.Instrument;

import java.io.IOException;

public class InstrumentDeserializer extends StdDeserializer<Instrument> {

    private InstrumentDeserializer(Class<?> vc) {
        super(vc);
    }

    public InstrumentDeserializer() {
        this(null);
    }

    @Override
    public Instrument deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new Instrument();
    }
}

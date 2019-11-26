package com.raccoons.tda.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.raccoons.tda.api.model.quote.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class QuoteDeserializer extends StdDeserializer<Quote> {

    protected QuoteDeserializer(Class<?> vc) {
        super(vc);
    }

    protected QuoteDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected QuoteDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Quote deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        final Map<String, JsonNode> entries = new HashMap<String, JsonNode>() {{
            final Iterator<Entry<String, JsonNode>> entryIterator = node.fields();
            while (entryIterator.hasNext()) {
                final Entry<String, JsonNode> nextEntry = entryIterator.next();
                put(nextEntry.getKey(), nextEntry.getValue());
            }
        }};

        final Set<String> keys = entries.keySet();
        final Class<?> quoteType = QuoteTemplate.getQuoteType(keys);

        if (quoteType.equals(EquityQuote.class)) {
            return buildEquityQuote(node);
        } else if (quoteType.equals(ETFQuote.class)) {
            return buildETFQuote(node);
        } else if (quoteType.equals(ForexQuote.class)) {
            return buildForexQuote(node);
        } else if (quoteType.equals(FutureOptionQuote.class)) {
            return buildFutureOptionQuote(node);
        } else if (quoteType.equals(IndexQuote.class)) {
            return buildIndexQuote(node);
        } else if (quoteType.equals(MutualFundQuote.class)) {
            return buildMutualFundQuote(node);
        } else if (quoteType.equals(OptionQuote.class)) {
            return buildOptionQuote(node);
        } else {
            return null;
        }
    }

    private EquityQuote buildEquityQuote(JsonNode rootNode) {
        return new EquityQuote(null);
    }

    private ETFQuote buildETFQuote(JsonNode rootNode) {
        return new ETFQuote(null);
    }

    private ForexQuote buildForexQuote(JsonNode rootNode) {
        return new ForexQuote(null);
    }

    private FutureOptionQuote buildFutureOptionQuote(JsonNode rootNode) {
        return new FutureOptionQuote(null);
    }

    private IndexQuote buildIndexQuote(JsonNode rootNode) {
        return new IndexQuote(null);
    }

    private MutualFundQuote buildMutualFundQuote(JsonNode rootNode) {
        return new MutualFundQuote(null);
    }

    private OptionQuote buildOptionQuote(JsonNode rootNode) {
        return new OptionQuote(null);
    }

    private static Object parseNode(final Class<?> type, final JsonNode node) {
        if (type.equals(String.class)) {
            return node.toString();
        } else if (type.equals(Integer.class)) {
            return node.asInt();
        } else if (type.equals(Long.class)) {
            return node.asLong();
        } else if (type.equals(Double.class)) {
            return node.asDouble();
        } else if (type.equals(Boolean.class)) {
            return node.asBoolean();
        }
        return null;
    }
}

package com.raccoons.tda.api.model.order;

import java.util.HashMap;
import java.util.Map;

public class CashEquivalent extends OrderInstrument {

    public Type getType() {
        return Type.from((String) values.get(TYPE));
    }

    public enum Type {

        SAVINGS,
        MONEY_MARKET_FUND;

        private final static Map<String, Type> VALUE_MAP = new HashMap<String, Type>() {{
            for (Type type : values()) {
                put(type.name().toLowerCase(), type);
            }
        }};

        public static Type from(final String value) {
            if (value == null)
                return null;
            return VALUE_MAP.get(value.toLowerCase());
        }

    }
}

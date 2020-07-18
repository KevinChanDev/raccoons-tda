package com.raccoons.tda.api.model.order;

import java.util.HashMap;
import java.util.Map;

public class MutualFund extends OrderInstrument {

    public Type getType() {
        return Type.from((String) values.get(TYPE));
    }

    public enum Type {

        NOT_APPLICABLE,
        OPEN_END_NON_TAXABLE,
        OPEN_END_TAXABLE,
        NO_LOAD_NON_TAXABLE,
        NO_LOAD_TAXABLE;

        private final static Map<String, Type> VALUE_MAP = new HashMap<String, Type>() {{
            for (Type type : Type.values()) {
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

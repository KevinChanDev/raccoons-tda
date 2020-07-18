package com.raccoons.tda.api.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Option extends OrderInstrument {

    @JsonProperty("optionDeliverables")
    private List<OptionDeliverable> optionDeliverables;

    @JsonProperty("putCall")
    private ContractType contractType;

    @JsonProperty("optionMultiplier")
    private Double optionMultiplier;

    @JsonProperty("underlyingSymbol")
    private String underlyingSymbol;

    public List<OptionDeliverable> getOptionDeliverables() {
        return optionDeliverables;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public Double getOptionMultiplier() {
        return optionMultiplier;
    }

    public Type getType(){
        return Type.from((String) values.get(TYPE));
    }

    public enum Type {

        VANILLA,
        BINARY,
        BARRIER;

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

    public enum ContractType {
        PUT,
        CALL
    }

}

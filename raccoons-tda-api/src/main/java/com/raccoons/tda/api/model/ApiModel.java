package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiModel {

    protected Map<String, Object> values;

    protected ApiModel() {
    }

    protected ApiModel(Map<String, Object> values) {
        this.values = values;
    }

    public Object getValue(final String key) {
        if (values == null || key == null)
            return null;
        return values.get(key);
    }

    @JsonAnyGetter
    public Map<String, Object> getValues() {
        return values;
    }

    @JsonAnySetter
    private void setValues(final String key, final String value) {
        if (values == null) {
            values = new HashMap<>();
        }
        values.put(key, value);
    }
}

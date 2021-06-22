package com.icb.icanbuy.models.ConexionCarrito;

import java.util.HashMap;
import java.util.Map;

public class ConexionCarritoRecord {
    private String id;
    private ConexionCarritoFields fields;
    private String createdTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConexionCarritoFields getFields() {
        return fields;
    }

    public void setFields(ConexionCarritoFields fields) {
        this.fields = fields;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

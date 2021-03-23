package com.icb.icanbuy.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UsuarioRecord {
    private String id;
    private UsuarioFields fields;
    private String createdTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UsuarioFields getFields() {
        return fields;
    }

    public void setFields(UsuarioFields fields) {
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


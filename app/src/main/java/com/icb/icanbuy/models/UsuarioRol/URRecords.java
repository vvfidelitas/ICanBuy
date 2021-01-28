package com.icb.icanbuy.models.UsuarioRol;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URRecords {
    private List<URRecord> records = null;
    private String offset;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<URRecord> getRecords() {
        return records;
    }

    public void setRecords(List<URRecord> records) {
        this.records = records;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

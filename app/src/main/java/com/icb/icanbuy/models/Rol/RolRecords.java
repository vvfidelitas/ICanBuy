package com.icb.icanbuy.models.Rol;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RolRecords {
    private List<RolRecord> records = null;
    private String offset;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<RolRecord> getRecords() {
        return records;
    }

    public void setRecords(List<RolRecord> records) {
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

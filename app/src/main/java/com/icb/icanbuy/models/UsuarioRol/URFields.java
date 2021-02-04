package com.icb.icanbuy.models.UsuarioRol;

import java.util.HashMap;
import java.util.Map;

public class URFields {
    private int idUsRol;
    private int idUsuario;
    private int idRol;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getIdUsRol() {
        return idUsRol;
    }

    public void setIdUsRol(int idUsRol) {
        this.idUsRol = idUsRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public Map<String, Object> getAdditionalProperties() {

        return this.additionalProperties;
    }
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

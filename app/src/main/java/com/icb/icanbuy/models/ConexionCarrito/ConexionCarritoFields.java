package com.icb.icanbuy.models.ConexionCarrito;

import java.util.HashMap;
import java.util.Map;

public class ConexionCarritoFields {
    public String iDUsuario;
    public String nombreUsuario;
    public int estado;
    public String iDTablet;
    public int conexionID;
    public String totalAPagar;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getiDUsuario() {
        return iDUsuario;
    }

    public void setiDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getiDTablet() {
        return iDTablet;
    }

    public void setiDTablet(String iDTablet) {
        this.iDTablet = iDTablet;
    }

    public int getConexionID() {
        return conexionID;
    }

    public void setConexionID(int conexionID) {
        this.conexionID = conexionID;
    }

    public String getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(String totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

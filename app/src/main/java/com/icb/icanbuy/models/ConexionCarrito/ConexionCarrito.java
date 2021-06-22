package com.icb.icanbuy.models.ConexionCarrito;

public class ConexionCarrito {
    public String iDUsuario;
    public String nombreUsuario;
    public int estado;
    public String iDTablet;
    public String conexionID;
    public String totalAPagar;

    public ConexionCarrito(String iDUsuario, String nombreUsuario, int estado,
                           String iDTablet, String conexionID, String totalAPagar) {
        this.iDUsuario = iDUsuario;
        this.nombreUsuario = nombreUsuario;
        this.estado = estado;
        this.iDTablet = iDTablet;
        this.conexionID=conexionID;
        this.totalAPagar=totalAPagar;
    }

    public ConexionCarrito() {
    }

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

    public String getConexionID() {
        return conexionID;
    }

    public void setConexionID(String conexionID) {
        this.conexionID = conexionID;
    }

    public String getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(String totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
}

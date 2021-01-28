package com.icb.icanbuy.models.UsuarioRol;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root
public class UsuarioRol implements Serializable {
    @Element
    private int idUsRol;
    @Element
    private int idUsuario;
    @Element
    private int idRol;

    public UsuarioRol(int idUsRol, int idUsuario, int idRol) {
        this.idUsRol = idUsRol;
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public UsuarioRol() {
    }

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
}

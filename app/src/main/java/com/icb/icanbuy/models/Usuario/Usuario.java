package com.icb.icanbuy.models.Usuario;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root
public class Usuario implements Serializable {
    @Element
    public int idUsuario;
    @Element
    public String nombre;
    @Element
    public String apellido;
    @Element
    public String fechaNac;
    @Element
    public String correo;
    @Element
    public String contrasena;

    public static int count = 0;

    public Usuario( String nombre, String apellido, String fechaNac, String correo) {
        //this.idUsuario = idUsuario;
        setIdUsuario(++count);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.correo = correo;
       // this.contrasena = contrasena;
    }

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

package com.icb.icanbuy.models.Usuario;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
<<<<<<< HEAD
=======
import java.util.Date;
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a

@Root
public class Usuario implements Serializable {
    @Element
<<<<<<< HEAD
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
=======
    private int idUsuario;
    @Element
    private String nombre;
    @Element
    private String apellido;
    @Element
    private Date fechaNac;
    @Element
    private String correo;
    @Element
    private String contrasena;

    public Usuario(int idUsuario, String nombre, String apellido, Date fechaNac, String correo, String contrasena) {
        this.idUsuario = idUsuario;
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.correo = correo;
<<<<<<< HEAD
       // this.contrasena = contrasena;
=======
        this.contrasena = contrasena;
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
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

<<<<<<< HEAD
    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
=======
    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
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

package com.icb.icanbuy.models.Usuario;

<<<<<<< HEAD
=======
import java.util.Date;
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
import java.util.HashMap;
import java.util.Map;

public class UsuarioFields {
    private int idUsuario;
    private String nombre;
    private String apellido;
<<<<<<< HEAD
    private String fechaNac;
=======
    private Date fechaNac;
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
    private String correo;
    private String contrasena;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

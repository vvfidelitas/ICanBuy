package com.icb.icanbuy.models.Usuario;


import java.util.HashMap;

public class UsuarioList {
    private HashMap<String, Usuario> usuarioList;

    public UsuarioList(){

        usuarioList = new HashMap<String, Usuario>();
    }

    public UsuarioList(HashMap<String, Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public void setUsuarioList(HashMap<String, Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public HashMap<String, Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void addUsuario(Usuario usuario){
        usuarioList.put(String.valueOf(usuario.getIdUsuario()), usuario);
    }

    public Usuario getUsuario(String id){

        return usuarioList.get(id);
    }

}

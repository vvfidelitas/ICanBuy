package com.icb.icanbuy.models.UsuarioRol;


import java.util.HashMap;

public class URList {
    private HashMap<String, UsuarioRol> urList;

    public URList(){
        urList = new HashMap<String, UsuarioRol>();
    }

    public URList(HashMap<String, UsuarioRol> urList) {
        this.urList = urList;
    }

    public void setURList(HashMap<String, UsuarioRol> productList) {
        this.urList = urList;
    }

    public HashMap<String, UsuarioRol> getURList() {
        return urList;
    }

    public void addUR(UsuarioRol usuarioRol){
        urList.put(String.valueOf(usuarioRol.getIdUsRol()), usuarioRol);
    }

    public UsuarioRol getUR(String code){
        return urList.get(code);
    }
}

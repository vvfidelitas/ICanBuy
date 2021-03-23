package com.icb.icanbuy.models.Rol;


import java.util.HashMap;

public class RolList {
    private HashMap<String, Rol> rolList;

    public RolList(){
        rolList = new HashMap<String, Rol>();
    }

    public RolList(HashMap<String, Rol> rolList) {
        this.rolList = rolList;
    }

    public void setProductList(HashMap<String, Rol> rolList) {
        this.rolList = rolList;
    }

    public HashMap<String, Rol> getRolList() {
        return rolList;
    }

    public void addProduct(Rol rol){
        rolList.put(String.valueOf(rol.getIdRol()), rol);
    }

    public Rol getProduct(String code){
        return rolList.get(code);
    }
}

package com.icb.icanbuy.models.ConexionCarrito;

import java.util.HashMap;

public class ConexionCarritoList {
    private HashMap<String, ConexionCarrito> conexionCarritoList;

    public ConexionCarritoList(){

        conexionCarritoList = new HashMap<String, ConexionCarrito>();
    }

    public ConexionCarritoList(HashMap<String, ConexionCarrito> conexionCarritoList) {
        this.conexionCarritoList = conexionCarritoList;
    }

    public void setConexionCarritoList(HashMap<String, ConexionCarrito> conexionCarritoList) {
        this.conexionCarritoList   =conexionCarritoList; }

    public HashMap<String, ConexionCarrito> getConexionCarritoList() {
        return conexionCarritoList;
    }

    public void addConexionCarrito(ConexionCarrito conexionCarrito){
        conexionCarritoList.put(String.valueOf(conexionCarrito.getConexionID()), conexionCarrito);
    }

    public ConexionCarrito getConexionCarrito(String id){

        return conexionCarritoList.get(id);
    }
}

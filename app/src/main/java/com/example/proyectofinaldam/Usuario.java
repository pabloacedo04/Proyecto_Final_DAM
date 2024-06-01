package com.example.proyectofinaldam;

public class Usuario {
    private String usr, contrasena;

    public Usuario(){

    }

    public String getContrasena(){
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }
}

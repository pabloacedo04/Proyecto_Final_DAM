package com.example.proyectofinaldam;

public class Usuario {
    private String usr, contrasena;

    public Usuario(){

    }
    public Usuario(String usr, String contrasena){
        this.usr = usr;
        this.contrasena = contrasena;
    }

    public String getUsr(){
        return this.usr;
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

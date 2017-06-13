package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cecilia on 6/6/2017.
 */
public class Usuario {
    private Integer id;
    private String nombreUsuario;
    private String contrasenia;
    private String direccion_correo;


    public Usuario(){
        this.id = 0;
        this.nombreUsuario = "";
        this.contrasenia = "";
        this.direccion_correo = "";
    }

    public Usuario(String nombre, String pass, String correo){
        this.nombreUsuario = nombre;
        this.contrasenia = pass;
        this.direccion_correo = correo;
    }

    public String getNombreUsuario() {

        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getDireccion_correo() {
        return direccion_correo;
    }

    public void setDireccion_correo(String direccion_correo) {
        this.direccion_correo = direccion_correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

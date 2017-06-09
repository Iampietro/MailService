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
    private ArrayList<Mensaje> listaMensajes;

    private String nombre;
    private String apellido;
    private String direccion;
    private Integer telefono;
    private String ciudad;
    private String provincia;
    private String pais;

    public Usuario(){
        this.id = 0;
        this.nombreUsuario = "";
        this.contrasenia = "";
        this.direccion_correo = "";
        this.listaMensajes = new ArrayList<Mensaje>();
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.telefono = 0;
        this.ciudad = "";
        this.provincia = "";
        this.pais = "";

    }

    public ArrayList<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

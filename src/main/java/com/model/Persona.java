package com.model;

/**
 * Created by Cecilia on 9/6/2017.
 */
public class Persona {

    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
    private Integer telefono;
    private String ciudad;
    private String provincia;
    private String pais;

    public Persona() {
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.telefono = 0;
        this.ciudad = "";
        this.provincia = "";
        this.pais = "";
    }

    public Persona(String nombre, String apellido, String direccion, Integer telefono, String ciudad, String provincia, String pais) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
    }

    public Persona(Integer id, String nombre, String apellido, String direccion, Integer telefono, String ciudad, String provincia, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

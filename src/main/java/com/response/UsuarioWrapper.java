package com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Cecilia on 14/6/2017.
 */
public class UsuarioWrapper {

    @JsonProperty
    private String nombre;
    @JsonProperty
    private String apellido;
    @JsonProperty
    private String direccion;
    @JsonProperty
    private Integer telefono;
    @JsonProperty
    private String ciudad;
    @JsonProperty
    private String provincia;
    @JsonProperty
    private String pais;
    @JsonProperty
    private String mail;

    public UsuarioWrapper(){}

    public UsuarioWrapper(String nombre, String apellido, String direccion, Integer telefono,
                          String ciudad, String provincia, String pais, String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

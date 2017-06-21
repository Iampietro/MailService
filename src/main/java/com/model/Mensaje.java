package com.model;

import org.joda.time.DateTime;

import java.sql.Date;

/**
 * Created by Cecilia on 6/6/2017.
 */
public class Mensaje {

    private Integer id;
    private String cuerpo;
    private String remitente;
    private String receptor;
    private String asunto;
    private boolean borrado;
    private boolean leido;

    public Mensaje(Integer id, String cuerpo, String id_remitente,
                   String id_receptor, String asunto, boolean borrado, boolean leido) {
        this.id = id;
        this.cuerpo = cuerpo;
        this.remitente = id_remitente;
        this.receptor = id_receptor;
        this.asunto = asunto;
        this.borrado = borrado;
        this.leido = leido;
    }

    public Mensaje(String cuerpo, String id_remitente,
                   String id_receptor, String asunto){
        this.cuerpo = cuerpo;
        this.remitente = id_remitente;
        this.receptor = id_receptor;
        this.asunto = asunto;
    }

    public  Mensaje(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}

package com.model;

/**
 * Created by Cecilia on 6/6/2017.
 */
public class Mensaje {

    private Integer id;
    private String cuerpo;
    private Integer id_remitente;
    private Integer id_receptor;
    private String asunto;
    private boolean borrado;
    private boolean leido;

    public Mensaje(Integer id, String cuerpo, Integer id_remitente,
                   Integer id_receptor, String asunto, boolean borrado, boolean leido) {
        this.id = id;
        this.cuerpo = cuerpo;
        this.id_remitente = id_remitente;
        this.id_receptor = id_receptor;
        this.asunto = asunto;
        this.borrado = borrado;
        this.leido = leido;
    }

    public Mensaje(String cuerpo, Integer id_remitente,
                   Integer id_receptor, String asunto){
        this.cuerpo = cuerpo;
        this.id_remitente = id_remitente;
        this.id_receptor = id_receptor;
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

    public Integer getId_remitente() {
        return id_remitente;
    }

    public void setId_remitente(Integer id_remitente) {
        this.id_remitente = id_remitente;
    }

    public Integer getId_receptor() {
        return id_receptor;
    }

    public void setId_receptor(Integer id_receptor) {
        this.id_receptor = id_receptor;
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

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
}

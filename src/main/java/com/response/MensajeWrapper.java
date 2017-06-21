package com.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by Cecilia on 14/6/2017.
 */
public class MensajeWrapper {

    @JsonProperty
    private String asunto;
    @JsonProperty
    private String cuerpo;
    @JsonProperty
    private boolean leido;

    public MensajeWrapper(String asunto, String cuerpo, boolean leido) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.leido = leido;
    }

    public MensajeWrapper(){}

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}

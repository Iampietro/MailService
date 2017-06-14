package com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

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

package com.converter;

import com.model.Mensaje;
import com.response.MensajeWrapper;
import org.springframework.stereotype.Component;

@Component
public class MensajeConverter {

    public MensajeConverter(){}

    public MensajeWrapper convert(Mensaje unMensaje){
        MensajeWrapper mensajeWrapper = new MensajeWrapper();
        mensajeWrapper.setAsunto(unMensaje.getAsunto());
        mensajeWrapper.setCuerpo(unMensaje.getCuerpo());
        mensajeWrapper.setLeido(unMensaje.isLeido());
        return mensajeWrapper;
    }
}

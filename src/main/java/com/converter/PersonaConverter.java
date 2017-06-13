package com.converter;

import com.model.Persona;
import com.response.PersonaWrapper;
import org.springframework.stereotype.Component;

/**
 * Created by Cecilia on 10/6/2017.
 */

@Component
public class PersonaConverter {

    public PersonaConverter(){}

    public PersonaWrapper convertir (Persona persona)
    {
        PersonaWrapper p = new PersonaWrapper();
        p.setNombre(persona.getNombre());
        p.setPais(persona.getPais());
        p.setApellido(persona.getApellido());
        p.setCiudad(persona.getCiudad());
        p.setDireccion(persona.getDireccion());
        p.setProvincia(persona.getProvincia());
        p.setTelefono(persona.getTelefono());
        return p;
    }
}

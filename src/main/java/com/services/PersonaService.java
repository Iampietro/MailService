package com.services;

import com.model.Persona;
import com.model.Usuario;
import com.persistence.PersonaDao;
import com.persistence.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    PersonaDao personaDao;

    @Autowired
    UsuarioDao usrDao;


    public PersonaService(PersonaDao personaDao){
        this.personaDao = personaDao;
    }

    public void newPersona(String nombre, String apellido, String direccion,
                           int telefono, String ciu, String prov, String pa,
    String nombreUsuario, String pass, String correo) {
        Persona p = new Persona(nombre,apellido,direccion,telefono,ciu,prov,pa);
        Usuario u = new Usuario(nombreUsuario, pass, correo);
        this.personaDao.save(p);
        this.usrDao.save(u);
    }

}

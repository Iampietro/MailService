package com.services;

import com.model.Usuario;
import com.persistence.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Cecilia on 8/6/2017.
 */
public class UsuarioService {

    UsuarioDao usuarioDao;

    @Autowired
    public UsuarioService(UsuarioDao dao){
        this.usuarioDao = dao;
    }


}

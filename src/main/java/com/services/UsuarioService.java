package com.services;

import com.model.Usuario;
import com.persistence.UsuarioDao;
import com.response.UsuarioWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UsuarioService {

    UsuarioDao usuarioDao;

    @Autowired
    public UsuarioService(UsuarioDao dao){
        this.usuarioDao = dao;
    }

    public Usuario login(String nombreUsuario, String password) {
        return usuarioDao.get(nombreUsuario,password);
    }

    public UsuarioWrapper getByName(String name) {
        return usuarioDao.getByName(name);
    }


    public ArrayList<UsuarioWrapper> getAll() {
        return usuarioDao.getAll();
    }

    public void deleteUser(int id_usrToDelete) {
        usuarioDao.deleteUser(id_usrToDelete);
    }
}

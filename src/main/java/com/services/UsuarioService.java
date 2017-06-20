package com.services;

import com.model.Usuario;
import com.persistence.UsuarioDao;
import com.response.UsuarioWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class UsuarioService {

    UsuarioDao usuarioDao;

    @Autowired
    public UsuarioService(UsuarioDao dao) {
        this.usuarioDao = dao;
    }

    public Usuario login(String nombreUsuario, String password) throws SQLException {
        try {
            return usuarioDao.get(nombreUsuario, password);
        } catch (SQLException e) {
            throw e;
        }
    }

    public UsuarioWrapper getByName(String name) throws Exception {
        try {
            return usuarioDao.getByName(name);
        }catch (Exception e){
            throw e;
        }
    }


    public ArrayList<UsuarioWrapper> getAll() {
        return usuarioDao.getAll();
    }

    public void deleteUser(int id_usrToDelete) throws Exception {
        usuarioDao.deleteUser(id_usrToDelete);
    }
}

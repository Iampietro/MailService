package com.services;


import com.model.Mensaje;
import com.persistence.MensajeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class MensajeService {

    @Autowired
    MensajeDao mensajeDao;

    public MensajeService(MensajeDao mensajeDao) {
        this.mensajeDao = mensajeDao;
    }

    public void save(Mensaje mensaje) {
        mensajeDao.save(mensaje);
    }

    public void delete(int id_mensaje)  {
        try {
            mensajeDao.delete(id_mensaje);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Mensaje> getDeleted(int id_receptor) {
        return mensajeDao.getDeleted(id_receptor);
    }

    public ArrayList<Mensaje> getInbox(int id_receptor) {
        return mensajeDao.getInbox(id_receptor);
    }

    public ArrayList<Mensaje> getSended(int id_remitente) {
        return mensajeDao.getSended(id_remitente);
    }
}

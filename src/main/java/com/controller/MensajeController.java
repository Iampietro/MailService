package com.controller;

import com.converter.MensajeConverter;
import com.model.Mensaje;
import com.persistence.MensajeDao;
import com.response.MensajeWrapper;
import com.services.MensajeService;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MensajeController {

    @Autowired
    MensajeService mensajeService;

    @Autowired
    MensajeConverter converter;

    @RequestMapping(value = "/api/mensaje", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity send (@RequestBody Mensaje msj){    // ENVIAR MENSAJE
        try{
            mensajeService.save(msj);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/mensaje", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody                               // "BORRAR MENSAJE"
    ResponseEntity delete (@RequestParam("id_msj") int id_mensaje){
        try{
            mensajeService.delete(id_mensaje);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/mensaje/trash", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody                               // Ver Borrados
    ResponseEntity<ArrayList<MensajeWrapper>> getDeleted (@RequestParam("id_receptor") int id_receptor){
        try{
            ArrayList<Mensaje> listMensajes = mensajeService.getDeleted(id_receptor);
            return  new ResponseEntity<ArrayList<MensajeWrapper>>(this.convertList(listMensajes) ,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "api/mensaje/inbox", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody                                // Ver Recibidos
    ResponseEntity<ArrayList<MensajeWrapper>> getInbox (@RequestParam("id_receptor") int id_receptor){
        try{
            ArrayList<Mensaje> listMensajes = mensajeService.getInbox(id_receptor);
            return new ResponseEntity<ArrayList<MensajeWrapper>> (this.convertList(listMensajes), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "api/mensaje", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody                                // Ver Enviados
    ResponseEntity<ArrayList<MensajeWrapper>> getSended(@RequestParam("id_remitente") int id_remitente){
        try{
            ArrayList<Mensaje> listMensajes = mensajeService.getSended(id_remitente);
            return new ResponseEntity<ArrayList<MensajeWrapper>>(this.convertList(listMensajes), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.NO_CONTENT);
        }
    }


    private ArrayList<MensajeWrapper> convertList(ArrayList<Mensaje> listMensajes) {
        ArrayList<MensajeWrapper> mensajeWrapperList = new ArrayList<MensajeWrapper>();
        for( Mensaje m : listMensajes){
            mensajeWrapperList.add(converter.convert(m));
        }
        return mensajeWrapperList;
    }
}

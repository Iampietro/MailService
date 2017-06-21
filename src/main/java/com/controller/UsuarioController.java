package com.controller;

import com.model.Usuario;
import com.request.PersonaRequest;
import com.response.LoginResponseWrapper;
import com.response.UsuarioWrapper;
import com.services.PersonaService;
import com.services.UsuarioService;
import com.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Cecilia on 7/6/2017.
 */
@RestController
@RequestMapping(
        value = "/",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    SessionData sessionData;
    @Autowired
    PersonaService personaService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<LoginResponseWrapper> getById(@RequestParam(value = "user", required = true) String nombreUsuario, @RequestParam(value = "pwd", required = true) String pwd) {
        Usuario u = new Usuario();
        try {
            u = usuarioService.login(nombreUsuario, pwd);
        } catch (Exception e) {
            return new ResponseEntity<LoginResponseWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (u.getDireccion_correo().equals(""))
        {
            return new ResponseEntity<LoginResponseWrapper>(HttpStatus.FORBIDDEN);
        }
        if (null != u) {
            String sessionId = sessionData.addSession(u);
            return new ResponseEntity<LoginResponseWrapper>(new LoginResponseWrapper(sessionId), HttpStatus.OK);
        }
        return new ResponseEntity<LoginResponseWrapper>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping("/logout")
    public
    @ResponseBody
    ResponseEntity getById(@RequestHeader("sessionid") String sessionId) {
        sessionData.removeSession(sessionId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity saveUsr(@RequestBody PersonaRequest req) {
        try {                                            // Crear Usuario (y Persona)
            personaService.newPersona(req.getNombre(), req.getApellido(), req.getDireccion(), req.getTelefono(),
                    req.getCiudad(), req.getProvincia(), req.getPais(), req.getNick(), req.getPass(), req.getMail());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<UsuarioWrapper> getUsrByName(@RequestParam(value = "name") String name) {                                                   // Traer Usuario por nombre
        try {
            UsuarioWrapper usuarioWrapper = usuarioService.getByName(name);
            if (usuarioWrapper.getNombre() == null) {
                return new ResponseEntity<UsuarioWrapper>(HttpStatus.NO_CONTENT);
            }else
            {
                return new ResponseEntity<UsuarioWrapper>(usuarioWrapper, HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<UsuarioWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/user", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public                               // Borrar usuario
    @ResponseBody
    ResponseEntity deleteUser(@RequestParam("id_usr") int id_usrToDelete) {
        try {
            usuarioService.deleteUser(id_usrToDelete);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<ArrayList<UsuarioWrapper>> listUsers() {
        try {                                            // Listar usuarios
            ArrayList<UsuarioWrapper> list = usuarioService.getAll();
            if (list.isEmpty()) {
                return new ResponseEntity<ArrayList<UsuarioWrapper>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<ArrayList<UsuarioWrapper>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ArrayList<UsuarioWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

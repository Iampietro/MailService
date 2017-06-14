package com.controller;

import com.model.Usuario;
import com.request.PersonaRequest;
import com.response.LoginResponseWrapper;
import com.response.PersonaWrapper;
import com.services.PersonaService;
import com.services.UsuarioService;
import com.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody
    ResponseEntity<LoginResponseWrapper> getById(@RequestParam("user") String nombreUsuario, @RequestParam("pwd") String pwd){
        System.out.println("-------------"+nombreUsuario + pwd);
        Usuario u = usuarioService.login(nombreUsuario, pwd);
        System.out.println(u);
        if (null != u) {
            String sessionId = sessionData.addSession(u);
            return new ResponseEntity<LoginResponseWrapper>(new LoginResponseWrapper(sessionId), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping("/logout")
    public @ResponseBody ResponseEntity getById(@RequestHeader("sessionid") String sessionId) {
        sessionData.removeSession(sessionId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity saveUsr (@RequestBody PersonaRequest req){
        try{
            personaService.newPersona(req.getNombre(),req.getApellido(),req.getDireccion(),req.getTelefono(),
                    req.getCiudad(),req.getProvincia(),req.getPais(),req.getNick(),req.getPass(),req.getMail());
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@RequestMapping(value = "/api", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<PersonaWrapper> getByName (@RequestParam ("name") String name){

    }*/
}

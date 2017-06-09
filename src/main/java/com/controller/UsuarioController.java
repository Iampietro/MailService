package com.controller;

import com.services.UsuarioService;
import com.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cecilia on 7/6/2017.
 */
@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    SessionData sessionData;


}

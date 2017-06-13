package com.controller;


import com.converter.PersonaConverter;
import com.model.Persona;
import com.request.PersonaRequest;
import com.response.PersonaWrapper;
import com.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(
        value = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaConverter converter;

    @RequestMapping("/persona/{id}")
    public @ResponseBody
    ResponseEntity<PersonaWrapper> getById(@RequestHeader("usuario") String userName , @PathVariable("id") int id){
        Persona per = personaService.getPersona(id);
        if (per!=null) {
            PersonaWrapper p = converter.convertir(per);
            return  new ResponseEntity<PersonaWrapper>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/personas/")
    public @ResponseBody  ResponseEntity<ArrayList<PersonaWrapper>> getAll() {
        ArrayList<Persona> list = personaService.getAll();
        if (list.size()>0) {
            return new ResponseEntity<ArrayList<PersonaWrapper>>(this.convertList(list), HttpStatus.OK);
        } else {
            return new ResponseEntity<ArrayList<PersonaWrapper>>(HttpStatus.NO_CONTENT);
        }
    }



    private ArrayList<PersonaWrapper> convertList(ArrayList<Persona> personas ){
        ArrayList<PersonaWrapper> personaWrapperList = new ArrayList<PersonaWrapper>();
        for (Persona p : personas) {
            personaWrapperList.add(converter.convertir(p));
        }
        return personaWrapperList;
    }
}

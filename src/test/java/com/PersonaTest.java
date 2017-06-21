package com;

import com.model.Persona;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;

/**
 * Created by Cecilia on 12/6/2017.
 */
public class PersonaTest extends TestCase {

    @Autowired
    Persona persona;

    @Before
    public void setUp() {
        persona = new Persona();
        persona.setId(1);
        persona.setNombre("Matias");
        persona.setApellido("Iampietro");
        persona.setTelefono(4937214);
        persona.setPais("Macrilandia");
        persona.setProvincia("Tuquoque");
        persona.setCiudad("MDQ");
        persona.setDireccion("Alvear y O'Higgins");
    }



    @Test
    public void testNombre(){
        assertEquals("Checking nombre getter", persona.getNombre(), "Matias");
    }

    @Test
    public void testApellido(){
        assertEquals("Checking apellido getter", persona.getApellido(), "Iampietro");
    }

    @Test
    public void testTelefono(){
        assertEquals((Integer)4937214,persona.getTelefono());
    }

    @Test
    public void testId(){
        assertEquals((Integer)1, persona.getId());
    }

    @Test
    public void testPais(){
        assertEquals("Checking Pais getter", persona.getPais(), "Macrilandia");
    }

    @Test
    public void testProvincia(){
        assertEquals("Checking Provincia getter", persona.getProvincia(), "Tuquoque");
    }

    @Test
    public void testCiudad(){
        assertEquals("Checking Ciudad getter", persona.getCiudad(), "MDQ");
    }

    @Test
    public void testDireccion(){
        assertEquals("Checking Direccion getter", persona.getDireccion(),"Alvear y O'Higgins");
    }
}

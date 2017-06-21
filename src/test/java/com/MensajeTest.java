package com;

import com.model.Mensaje;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Cecilia on 21/6/2017.
 */
public class MensajeTest extends TestCase{
    @Autowired
    Mensaje mensaje;

    @Before
    public void setUp(){
        mensaje = new Mensaje(10, "cuerpo del mensaje", "putas@gmail.com", "thefloorisprogramming@gmail.com",
                "asunto del mensaje", false , false);
    }

    @Test
    public void testId(){
        assertEquals((Integer)10,mensaje.getId());
    }

    @Test
    public void testCuerpo(){
        assertEquals(mensaje.getCuerpo(),"cuerpo del mensaje");
    }

    @Test
    public void testRemitente(){
        assertEquals(mensaje.getRemitente(), "putas@gmail.com");
    }

    @Test
    public void testReceptor(){
        assertEquals(mensaje.getReceptor(),"thefloorisprogramming@gmail.com");
    }

    @Test
    public void testAsunto(){
        assertEquals(mensaje.getAsunto(),"asunto del mensaje");
    }

    @Test
    public void testBorrado(){
        assertEquals(mensaje.isBorrado(),false);
    }

    @Test
    public void testLeido(){
        assertEquals(mensaje.isLeido(),false);
    }
}

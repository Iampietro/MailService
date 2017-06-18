package com.controller;

import com.App;
import com.model.Mensaje;
import com.model.Persona;
import com.model.Usuario;
import com.util.SessionData;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import java.net.URL;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
@WebAppConfiguration
public class MensajeControllerTest extends TestCase{

    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String sessionid;
    private Mensaje mensaje;
    private Usuario usuario;
    private Persona persona;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();

        usuario = new Usuario();
        usuario.setContrasenia("inhackeable");
        usuario.setDireccion_correo("stuborn@gmail.com");
        usuario.setNombreUsuario("nombreCool");
        usuario.setId(1);

        mensaje = new Mensaje();
        mensaje.setId(2);
        mensaje.setAsunto("Asunto gravísimo con la grabación.");
        mensaje.setBorrado(false);
        mensaje.setCuerpo("Oie macarena");
        mensaje.setId_receptor(1);
        mensaje.setId_remitente(3);
        mensaje.setLeido(false);

        this.sessionid = this.sessionData.addSession(usuario);
    }

    @After
    public void tearDown() {
        this.sessionData.removeSession(this.sessionid);
    }

    @Test
    public void testGetInboxOk() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/inbox")
                        .header("sessionid", this.sessionid)
                        .param("id_receptor", mensaje.getId_receptor().toString())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetInboxFail() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/inbox")
                .header("sessionid", this.sessionid)
                .param("id_receptor", "tijuana")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetSendOk() throws Exception {
        mockMvc.perform(
                get("/api/mensaje")
                        .header("sessionid", this.sessionid)
                        .param("id_remitente", mensaje.getId_remitente().toString())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testDeleteMessageOk() throws Exception {
        mockMvc.perform((
                        delete("/api/mensaje")
                                .header("sessionid", this.sessionid)
                                .param("id_msj", mensaje.getId().toString())
                )
        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void testSendMessageOk() throws Exception {
        URL url  = Resources.getResource("mensaje.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        mockMvc.perform(
                post("/api/mensaje")
                        .header("sessionid", this.sessionid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetTrashOk() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/trash")
                .header("sessionid", this.sessionid)
                .param("id_receptor", mensaje.getId_receptor().toString())

        )
                .andExpect(status().isOk());
    }




}

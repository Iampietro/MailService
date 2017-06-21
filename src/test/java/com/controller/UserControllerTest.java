package com.controller;


import com.App;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.model.Persona;
import com.model.Usuario;
import com.response.UsuarioWrapper;
import com.services.UsuarioService;
import com.util.SessionData;
import junit.framework.TestCase;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.mock;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;



/**
 * Created by Cecilia on 17/6/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
@WebAppConfiguration
@ActiveProfiles("default")
public class UserControllerTest extends TestCase{

    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UsuarioService usuarioService;

    private MockMvc mockMvc;
    private String sessionid;
    private Usuario user;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
        Persona persona = new Persona();

        persona.setId(5);
        persona.setNombre("Juancho");
        persona.setApellido("Perez");
        persona.setDireccion("La Habanna");
        persona.setTelefono(666);
        persona.setCiudad("MDQ");
        persona.setPais("Macrilandia");
        persona.setProvincia("Baires");

        user = new Usuario();

        user.setId(1);
        user.setDireccion_correo("asd@gmail.com");
        user.setContrasenia("dsa");

        this.sessionid = this.sessionData.addSession(user);
    }

    @After
    public void tearDown() throws Exception{
        this.sessionData.removeSession(this.sessionid);
    }

    @Test
    public void testAddUserCreated() throws Exception{
        URL url  = Resources.getResource("usuario.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        mockMvc.perform(
                post("/api/user")
                        .header("sessionid", this.sessionid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
        )
                .andExpect(status().isCreated());

    }

    @Test
    public void testGetUserByNameOk() throws Exception{
        mockMvc.perform(
                get("/api/user")
                .header("sessionid", this.sessionid)
                .param("name", "this")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserByNameNoContent() throws Exception{
        mockMvc.perform(
                get("/api/user")
                        .header("sessionid", this.sessionid)
                        .param("name", "throughout")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteUsr() throws Exception{
        mockMvc.perform(
                delete("/api/user")
                .header("sessionid", this.sessionid)
                .param("id_usr", user.getId().toString())
        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void testListUsr() throws Exception{
        mockMvc.perform(
                get("/api/users")
                .header("sessionid", this.sessionid)

        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testListarUsuariosNoContent() throws Exception {
        usuarioService = mock(UsuarioService.class);
        when(usuarioService.getAll()).thenReturn(new ArrayList<UsuarioWrapper>());
    }

    @Test
    public void testDeleteUserBadRequest() throws Exception{

        mockMvc.perform(delete
                ("/api/user/")
                .header("sessionid", this.sessionid)
                .param("id_usr", "estoSeVaDescontrolah")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLoginOk() throws Exception{
        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(EntityUtils.toString(new UrlEncodedFormEntity(asList(
                                new BasicNameValuePair("user", "NombreCool"),
                                new BasicNameValuePair("pwd", "213")
                        ))))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginForbidden() throws Exception{
        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(EntityUtils.toString(new UrlEncodedFormEntity(asList(
                                new BasicNameValuePair("user", "EstoDeberiaFallar"),
                                new BasicNameValuePair("pwd", "HopeSo")
                        ))))
        )
                .andExpect(status().isForbidden());
    }


    @Test
    public void testLogOUtOk() throws Exception{

        mockMvc.perform(post("/logout")
                .header("sessionid", this.sessionid)

        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldReturnHttpCode405OnPUT() throws Exception{
        mockMvc.perform(
                put("/api/user")
        )
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void shouldReturnHttpCode400PostWithoutParameter() throws Exception{
        mockMvc.perform(
                post("/login")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400getByNameWithoutParameter() throws Exception{
        mockMvc.perform(
                get("/api/user")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void checkSessionTest() throws Exception {
        this.sessionData.checkSessions();
    }




}

package com.persistence;

import com.model.Mensaje;
import com.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cecilia on 7/6/2017.
 */
public class UsuarioDao {
    Connection c;

    /*
    public UsuarioDao(@Value("${db.Name}") String dbName)
    * */

    public UsuarioDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/apimail", "usrMail", "666");
            //                                                "+host+":"+Port+"/+dbName

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario getById(int idUsr){
        Usuario usr = new Usuario();
        ArrayList<Mensaje> list = new ArrayList<Mensaje>();
        try{
            PreparedStatement ps = c.prepareStatement("SELECT * FROM usuarios u WHERE u.id = ?");
            ps.setInt(1,idUsr);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                                              // Traigo datos del usuario
                Integer userId = rs.getInt("id");
                String userName = rs.getString("nombre_usuario");
                String userPass = rs.getString("pasword");
                String userMail = rs.getString("mail");

                String userRealName = rs.getString("nombre_real");
                String userSurname = rs.getString("apellido");
                String userAdress = rs.getString("direccion");
                Integer userPhone = rs.getInt("telefono");
                String city = rs.getString("ciudad");
                String province = rs.getString("provincia");
                String country = rs.getString("pais");

                usr.setNombre(userRealName);
                usr.setApellido(userSurname);
                usr.setCiudad(city);
                usr.setContrasenia(userPass);
                usr.setDireccion(userAdress);
                usr.setDireccion_correo(userMail);
                usr.setId(userId);
                usr.setNombreUsuario(userName);
                usr.setPais(country);
                usr.setTelefono(userPhone);
                usr.setProvincia(province);
            }
            ps = c.prepareStatement("SELECT * FROM mensajes WHERE receptor = ?");
            ps.setInt(1,idUsr);
            ResultSet rst = ps.executeQuery();
            while(rst.next()){                                              // Traigo todos los mensajes asociados
                Integer id = rst.getInt("id");
                String cuerpo = rst.getString("cuerpo");
                Integer remitente = rst.getInt("remitente");
                Integer receptor = rst.getInt("receptor");
                String asunto = rst.getString("asunto");
                boolean borrado = rst.getBoolean("borrado");
                boolean leido = rst.getBoolean("leido");

                Mensaje msj = new Mensaje(id,cuerpo,remitente,receptor,asunto,borrado,leido);

                list.add(msj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        usr.setListaMensajes(list);
        return usr;
    }


}

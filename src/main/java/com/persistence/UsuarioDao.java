package com.persistence;

import com.model.Mensaje;
import com.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cecilia on 7/6/2017.
 */
@Repository
public class UsuarioDao {

    Connection c;

    /*
    public UsuarioDao(@Value("${db.Name}") String dbName)
    * */

    @Autowired
    public UsuarioDao(@Value("${db.username}") String dbUserName,
                      @Value("${db.name}") String dbName,
                      @Value("${db.password}") String dbPassword,
                      @Value("${db.port}") String dbPort,
                      @Value("${db.host}") String dbHost
                      ){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.c = (Connection) DriverManager.getConnection("jdbc:mysql://" +dbHost+ ":" +dbPort+
                    "/" +dbName+"?serverTimezone=UTC", dbUserName, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UsuarioDao(){}



    public Usuario getById(int idUsr){
        Usuario usr = new Usuario();
        try{
            PreparedStatement ps = c.prepareStatement("SELECT * FROM usuarios u WHERE u.id = ?");
            ps.setInt(1,idUsr);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                                              // Traigo datos del usuario
                Integer userId = rs.getInt("id");
                String userName = rs.getString("nombre_usuario");
                String userPass = rs.getString("pasword");
                String userMail = rs.getString("mail");

                usr.setContrasenia(userPass);
                usr.setDireccion_correo(userMail);
                usr.setId(userId);
                usr.setNombreUsuario(userName);


            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return usr;
    }

    public Usuario get(String nombreUsuario, String password){
        Usuario u = new Usuario();
        try{
            PreparedStatement ps = c.prepareStatement("select id,mail from usuarios where nombre_usuario = ? and contrasenia = ?");
            ps.setString(1,nombreUsuario);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String mail = rs.getString("mail");

                u.setId(id);
                u.setNombreUsuario(nombreUsuario);
                u.setDireccion_correo(mail);
                u.setContrasenia(password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    public void save(Usuario u){
        try{
            PreparedStatement ps = c.prepareStatement("INSERT INTO usuarios VALUES (NULL, ?, ?, ?)");
            ps.setString(1,u.getNombreUsuario());
            ps.setString(2,u.getContrasenia());
            ps.setString(3,u.getDireccion_correo());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

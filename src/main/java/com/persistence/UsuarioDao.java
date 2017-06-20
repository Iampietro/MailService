package com.persistence;

import com.model.Mensaje;
import com.model.Usuario;
import com.response.UsuarioWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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
            /*TimeZone t = TimeZone.getTimeZone("GMT-300");
            TimeZone.setDefault(t);*/
            Class.forName("com.mysql.jdbc.Driver");
            this.c = (Connection) DriverManager.getConnection("jdbc:mysql://" +dbHost+ ":" +dbPort+
                    "/" +dbName+"?serverTimezone=UTC", dbUserName, dbPassword);
            //+"?serverTimezone=UTC"
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

    public Usuario get(String nombreUsuario, String password) throws SQLException{
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
            throw e;
        }
        return u;
    }

    public void save(Usuario u){
        try{
            PreparedStatement ps1 = c.prepareStatement("select MAX(id) as id from personas");
            ResultSet rs = ps1.executeQuery();
            int persona_id = 0;
            while(rs.next()){
                persona_id = rs.getInt("id");
            }
            PreparedStatement ps = c.prepareStatement("INSERT INTO usuarios VALUES (NULL, ?, ?, ?, ?)");
            ps.setString(1,u.getNombreUsuario());
            ps.setString(2,u.getContrasenia());
            ps.setString(3,u.getDireccion_correo());
            ps.setInt(4,persona_id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public UsuarioWrapper getByName(String name) throws SQLException {
        UsuarioWrapper u = new UsuarioWrapper();
        try{
            PreparedStatement ps = c.prepareStatement("select * from usuarios u join personas p on u.persona_id = p.id where u.nombre_usuario = ?");
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                u.setNombre(rs.getString("nombre_real"));
                u.setApellido(rs.getString("apellido"));
                u.setDireccion(rs.getString("direccion"));
                u.setTelefono(rs.getInt("telefono"));
                u.setCiudad(rs.getString("ciudad"));
                u.setProvincia(rs.getString("provincia"));
                u.setPais(rs.getString("pais"));
                u.setMail(rs.getString("mail"));
            }
        }catch (SQLException e){
            throw e;
        }
        return u;
    }


    public ArrayList<UsuarioWrapper> getAll() {
        ArrayList<UsuarioWrapper> listUsers = new ArrayList<UsuarioWrapper>();
        try{
            PreparedStatement ps = c.prepareStatement("select * from usuarios u join personas p on u.persona_id = p.id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                UsuarioWrapper usuarioWrapper = new UsuarioWrapper(rs.getString("nombre_real"), rs.getString("apellido"),
                        rs.getString("direccion"), rs.getInt("telefono"), rs.getString("ciudad"), rs.getString("provincia"),
                        rs.getString("pais"), rs.getString("mail"));
                listUsers.add(usuarioWrapper);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listUsers;
    }

    public void deleteUser(int id_usrToDelete) {
        try{
            PreparedStatement ps = c.prepareStatement("delete from usuarios where id = ?");
            ps.setInt(1,id_usrToDelete);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

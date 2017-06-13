package com.persistence;

import com.model.Persona;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class PersonaDao {
    Connection c;

    public PersonaDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/apimail?serverTimezone=UTC", "usrMail", "666");
            //                                                "+host+":"+Port+"/+dbName

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Persona getById(int idPers){
        Persona p = new Persona();

        try{
            PreparedStatement ps = c.prepareStatement("SELECT * FROM personas u WHERE u.id = ?");
            ps.setInt(1,idPers);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt("id");
                String userRealName = rs.getString("nombre_real");
                String userSurname = rs.getString("apellido");
                String userAdress = rs.getString("direccion");
                Integer userPhone = rs.getInt("telefono");
                String city = rs.getString("ciudad");
                String province = rs.getString("provincia");
                String country = rs.getString("pais");

                p.setId(id);
                p.setNombre(userRealName);
                p.setApellido(userSurname);
                p.setCiudad(city);
                p.setDireccion(userAdress);
                p.setPais(country);
                p.setTelefono(userPhone);
                p.setProvincia(province);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return p;
    }

   public ArrayList<Persona> getAll() {
        ArrayList<Persona> personas = new ArrayList<Persona>();
        try{
            PreparedStatement ps  = c.prepareStatement("SELECT * FROM personas");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_real");
                String ape = rs.getString("apellido");
                String dire = rs.getString("direccion");
                Integer tel = rs.getInt("telefono");
                String ciu = rs.getString("ciudad");
                String prov = rs.getString("provincia");
                String pa = rs.getString("pais");

                Persona unaP = new Persona(id, nombre, ape, dire, tel, ciu, prov, pa);
                personas.add(unaP);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return personas;
    }

    public void save(Persona p ) {
            try {
                PreparedStatement ps = c.prepareStatement("INSERT INTO personas VALUES (null, ?,?,?,?,?,?,?)");
                ps.setString(1,p.getNombre());
                ps.setString(2,p.getApellido());
                ps.setString(3,p.getDireccion());
                ps.setInt(4,p.getTelefono());
                ps.setString(5,p.getCiudad());
                ps.setString(6,p.getProvincia());
                ps.setString(7,p.getPais());
                ps.execute();

            }catch (SQLException e){
                e.printStackTrace();
            }
    }
}

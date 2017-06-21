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

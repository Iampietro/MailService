package com.persistence;

import com.model.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class MensajeDao{
    Connection c;

    @Autowired
    public MensajeDao(@Value("${db.username}") String dbUserName,
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

    public MensajeDao(){}

    public void save(Mensaje msj){
        try{
            PreparedStatement ps = c.prepareStatement("insert into mensajes values (null, ?, ?, ?, ?, false, false)");
            ps.setString(1,msj.getCuerpo());
            ps.setInt(2,msj.getId_remitente());
            ps.setInt(3,msj.getId_receptor());
            ps.setString(4,msj.getAsunto());
            System.out.println(msj.getCuerpo());
            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void delete(int id_mensaje)  {
        try{
            PreparedStatement ps = c.prepareStatement("update mensajes set borrado = true where id = ?");
            ps.setInt(1,id_mensaje);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Mensaje> getDeleted(int id_receptor) {
        ArrayList<Mensaje> listMensajes = new ArrayList<Mensaje>();
        try{
            PreparedStatement ps = c.prepareStatement("select * from mensajes where receptor = ? and borrado = true");
            ps.setInt(1,id_receptor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Mensaje m = new Mensaje(rs.getInt("id"), rs.getString("cuerpo"),
                        rs.getInt("remitente"), rs.getInt("receptor"),
                        rs.getString("asunto"), rs.getBoolean("borrado"),
                        rs.getBoolean("leido"));

                listMensajes.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listMensajes;
    }

    public ArrayList<Mensaje> getInbox(int id_receptor) {
        ArrayList<Mensaje> listMensajes = new ArrayList<Mensaje>();
        try{
            PreparedStatement ps = c.prepareStatement("select * from mensajes where receptor = ? and borrado = false ");
            ps.setInt(1,id_receptor);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Mensaje m = new Mensaje(rs.getInt("id"), rs.getString("cuerpo"),
                        rs.getInt("remitente"), rs.getInt("receptor"),
                        rs.getString("asunto"), rs.getBoolean("borrado"),
                        rs.getBoolean("leido"));

                listMensajes.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listMensajes;
    }

    public ArrayList<Mensaje> getSended(int id_remitente) {
        ArrayList<Mensaje> listMensajes = new ArrayList<Mensaje>();
        try{
            PreparedStatement ps = c.prepareStatement("select * from mensajes where remitente = ?");
            ps.setInt(1,id_remitente);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Mensaje m = new Mensaje(rs.getInt("id"), rs.getString("cuerpo"),
                        rs.getInt("remitente"), rs.getInt("receptor"),
                        rs.getString("asunto"), rs.getBoolean("borrado"),
                        rs.getBoolean("leido"));

                listMensajes.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listMensajes;
    }
}

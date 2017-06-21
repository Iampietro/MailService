package com.persistence;

import com.model.Mensaje;
import com.response.MensajeWrapper;
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

            // Id Receptor
            PreparedStatement ps1 = c.prepareStatement("select id from usuarios where mail like ?");
            ps1.setString(1, msj.getReceptor());
            ResultSet rs1 = ps1.executeQuery();
            int id_receptor = 0;
            while (rs1.next()){
                id_receptor = rs1.getInt("id");
            }

            // Id Remitente
            PreparedStatement ps2 = c.prepareStatement("select id from usuarios where mail like ?");
            ps2.setString(1,msj.getRemitente());
            ResultSet rs2 = ps2.executeQuery();
            int id_remitente = 0;
            while (rs2.next()){
                id_remitente = rs2.getInt("id");
            }

            PreparedStatement ps = c.prepareStatement("insert into mensajes (cuerpo,remitente,receptor,id_remitente,id_receptor,asunto, borrado, leido) values (?, ?, ?, ?, ? ,?, ?, ?)");
            ps.setString(1,msj.getCuerpo());
            ps.setString(2,msj.getRemitente());
            ps.setString(3,msj.getReceptor());
            ps.setInt(4,id_remitente);
            ps.setInt(5,id_receptor);
            ps.setString(6,msj.getAsunto());
            ps.setBoolean(7,false);
            ps.setBoolean(8,false);
            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void delete(int id_mensaje) throws SQLException{
        try{
            PreparedStatement ps = c.prepareStatement("update mensajes set borrado = true where id = ?");
            ps.setInt(1,id_mensaje);
            ps.execute();
        }catch (SQLException e){
            throw e;
        }
    }

    public ArrayList<Mensaje> getDeleted(int id_receptor) throws SQLException {
        ArrayList<Mensaje> listMensajes = new ArrayList<Mensaje>();
        try{
            PreparedStatement ps = c.prepareStatement("select * from mensajes where id_receptor = ? and borrado = true order by id desc");
            ps.setInt(1,id_receptor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Mensaje m = new Mensaje(rs.getInt("id"), rs.getString("cuerpo"),
                        rs.getString("remitente"), rs.getString("receptor"),
                        rs.getString("asunto"), rs.getBoolean("borrado"),
                        rs.getBoolean("leido"));

                listMensajes.add(m);
            }
        }catch (SQLException e){
            throw e;
        }
        return listMensajes;
    }

    public ArrayList<Mensaje> getInbox(int id_receptor) {
        ArrayList<Mensaje> listMensajes = new ArrayList<Mensaje>();
        try{
            PreparedStatement ps = c.prepareStatement("select * from mensajes where id_receptor = ? and borrado = false order by id desc");
            ps.setInt(1,id_receptor);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Mensaje m = new Mensaje(rs.getInt("id"), rs.getString("cuerpo"),
                        rs.getString("remitente"), rs.getString("receptor"),
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
            PreparedStatement ps = c.prepareStatement("select * from mensajes where id_remitente = ? order by id desc");
            ps.setInt(1,id_remitente);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Mensaje m = new Mensaje(rs.getInt("id"), rs.getString("cuerpo"),
                        rs.getString("remitente"), rs.getString("receptor"),
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

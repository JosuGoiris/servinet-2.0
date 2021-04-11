/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author josug
 */
public class ConexionSingleton {
    public static Connection con;
    private static String user = "root";
    private static String pass = "aramiteamo16";
    private static String bd = "servinet";
    private static String url = "jdbc:mysql://192.168.1.10/"+bd;
    
    public static String getUser(){
        return user;
    }
    
    public static String getUrl(){
        return url;
    }
    
    public static String getPass(){
        return pass;
    }
    
    public static String getBD(){
        return bd;
    }
    
    public static Connection getConnection(){
        try {
            if(con == null){
                Runtime.getRuntime().addShutdownHook(new getClose());
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("Entro al if");
            }
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Conexion Fallida", e);
        }
    }
    
    public Connection getConectar(){
        return con;
    }
    
    public void desconectar(){
        con = null;
    }
    
    static class getClose extends Thread{

        @Override
        public void run() {
            try {
                Connection con = ConexionSingleton.getConnection();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
    }
}

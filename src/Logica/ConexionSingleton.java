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
    
    public static Connection getConnection(){
        try {
            if(con == null){
                Runtime.getRuntime().addShutdownHook(new getClose());
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/servinet", "root", "aramiteamo16");
                System.out.println("Entro al if");
            }
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Conexion Fallida", e);
        }
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

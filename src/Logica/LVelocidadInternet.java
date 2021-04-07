/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DVelocidadInternet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josug
 */
public class LVelocidadInternet {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarVelocidad(String buscar) {
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Velocidad", "Estado"};
        String dts [] = new String[3];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select v.idVelocidadCon, v.velocidad, e.estado from tblvelocidadcon \n"
                + "as v inner join tblestadovelocidad as e on v.estadovelocidadId = e.idEstadoVelocidad \n"
                + "where v.idVelocidadCon = '" + buscar + "' or v.velocidad like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                 dts[0] = rs.getString("idVelocidadCon");
                 dts[1] = rs.getString("velocidad");
                 dts[2] = rs.getString("estado");
                 miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarVelocidad(DVelocidadInternet dVel) {
        String msg = null;
        sSQL = "insert into tblvelocidadcon(velocidad, estadovelocidadId) values(?,?)";
        
        try {
             PreparedStatement pst = cn.prepareStatement(sSQL);
             
             pst.setString(1, dVel.getVelocidad());
             pst.setInt(2, dVel.getEstadovelocidadId());
             
             int n = pst.executeUpdate();
             if(n != 0){
                 msg = "si";
                 return msg;
             }else{
                 msg = "no";
             }
             return msg;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return msg;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DCantidadTrabajadores;
import Datos.DCuadrillas;
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
public class LCantidadTrabajadores {
    
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
     public String traervalor(String cantidad){
//       sSQL = "select cantidadTrabajadores from tblcuadrilla where idCuadrilla = '" + id.num + "'";
         try {
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sSQL);
             while(rs.next()){
                 String dts = rs.getString("cantidadTrabajadores");
                 
                 //id.cantidadReal = dts;
             }
             return cantidad;
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
             return null;
         }
     }
     
     public String insertarCantidad(DCuadrillas dCua){
         String msg = null;
         //sSQL = "update tblcuadrilla set cantidadTrabajadores = ? where idCuadrilla = '" + id.num + "'";
         try {
             PreparedStatement pst = cn.prepareStatement(sSQL);
             pst.setInt(1, dCua.getCantidadTrabajadores());
             
             pst.executeUpdate();
             msg = "si";
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
             msg = "no";
         }
         return msg;
     }
}


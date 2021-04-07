/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DZonas;
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
public class LZonas {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarZonas(String buscar){
        DefaultTableModel miModelo = null;
    
        String titulos [] = {"Id", "Barrio", "Descripción"};
        String dts [] = new String[3];
    
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idZona, nombreZona, Descripcion from tblzona where idZona = '" + buscar + "' \n"
                + "or nombreZona like '%" + buscar + "%' && estado = 'Activo' order by idZona";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idZona");
                dts[1] = rs.getString("nombreZona");
                dts[2] = rs.getString("descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarZonasInactivos(String buscar){
        DefaultTableModel miModelo = null;
    
        String titulos [] = {"Id", "Barrio", "Descripción"};
        String dts [] = new String[3];
    
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idZona, nombreZona, Descripcion from tblzona where idZona = '" + buscar + "' \n"
                + "or nombreZona like '%" + buscar + "%' && estado = 'Inactivo' order by idZona";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idZona");
                dts[1] = rs.getString("nombreZona");
                dts[2] = rs.getString("descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarZona(DZonas dZonas){
        sSQL = "insert into tblzona(nombreZona, Descripcion, estado) value(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dZonas.getNombreZona());
            pst.setString(2, dZonas.getDescripcion());
            pst.setString(3, "Activo");
            
            pst.executeUpdate();
            
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String editarZona(DZonas dZonas){
        sSQL = "update tblzona set nombreZona = ?, Descripcion = ? \n"
                + "where idZona = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dZonas.getNombreZona());
            pst.setString(2, dZonas.getDescripcion());
            pst.setInt(3, dZonas.getIdZona());
            
            pst.executeUpdate();
            
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String eliminarZona(DZonas dZonas){
        sSQL = "update tblzona set estado = ? \n"
                + "where idZona = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Inactivo");
            pst.setInt(2, dZonas.getIdZona());
            
            pst.executeUpdate();
            
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String activarZona(DZonas dZonas){
        sSQL = "update tblzona set estado = ? \n"
                + "where idZona = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Activo");
            pst.setInt(2, dZonas.getIdZona());
            
            pst.executeUpdate();
            
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public ResultSet buscarRepetido(String buscar){
        sSQL = "select nombreZona from tblzona where nombreZona = '" + buscar + "' && estado = 'Activo' order by idZona";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DCuadrillas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josug
 */
public class LCuadrillas {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarCuadrillas(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre de la Cuadrilla", "Trabajadores", "Descripcion", "Estado"};
        String dts [] = new String[5];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idCuadrilla, nombreCuadrilla, cantidadTrabajadores, descripcion, estado from tblcuadrilla \n"
             + "where idCuadrilla = '" + buscar + "' or nombreCuadrilla like '%" + buscar + "%' \n"
                + "&& estado = 'Activo' order by idCuadrilla";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idCuadrilla");
                dts[1] = rs.getString("nombreCuadrilla");
                dts[2] = rs.getString("cantidadTrabajadores");
                dts[3] = rs.getString("descripcion");
                dts[4] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarCuadrillasReclamos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre de la Cuadrilla", "Descripcion"};
        String dts [] = new String[3];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idCuadrilla, nombreCuadrilla, cantidadTrabajadores, descripcion, estado from tblcuadrilla \n"
             + "where idCuadrilla = '" + buscar + "' or nombreCuadrilla like '%" + buscar + "%' \n"
                + "&& estado = 'Activo' order by idCuadrilla";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idCuadrilla");
                dts[1] = rs.getString("nombreCuadrilla");;
                dts[2] = rs.getString("descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarCuadrillasInactivas(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre de la Cuadrilla", "Trabajadores", "Descripcion", "Estado"};
        String dts [] = new String[5];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idCuadrilla, nombreCuadrilla, cantidadTrabajadores, descripcion, estado from tblcuadrilla \n"
             + "where idCuadrilla = '" + buscar + "' or nombreCuadrilla like '%" + buscar + "%' \n"
                + "&& estado = 'Inactivo' order by idCuadrilla";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idCuadrilla");
                dts[1] = rs.getString("nombreCuadrilla");
                dts[2] = rs.getString("cantidadTrabajadores");
                dts[3] = rs.getString("descripcion");
                dts[4] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarCuadrillas(DCuadrillas dCuadrillas){
        sSQL = "insert into tblcuadrilla(nombreCuadrilla, cantidadTrabajadores, descripcion, estado) value(?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dCuadrillas.getNombreCuadrilla());
            pst.setInt(2, 0);
            pst.setString(3, dCuadrillas.getDescripcion());
            pst.setString(4, "Activo");
            
            pst.executeUpdate();
            
            System.out.println("Datos Insertados");
            
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String editarCuadrilla(DCuadrillas dCuadrillas){
        sSQL = "update tblcuadrilla set nombreCuadrilla = ?, descripcion = ? where idCuadrilla = ?";
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dCuadrillas.getNombreCuadrilla());
            pst.setString(2, dCuadrillas.getDescripcion());
            pst.setInt(3, dCuadrillas.getIdCuadrilla());
            
            pst.executeUpdate();
            
            System.out.println("Datos Actualizados");
            
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String eliminarCuadrilla(DCuadrillas dCuadrillas){
        sSQL = "update tblcuadrilla set estado = ? where idCuadrilla = ?";
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Inactivo");
            pst.setInt(2, dCuadrillas.getIdCuadrilla());
            
            pst.executeUpdate();
            
            System.out.println("Datos Actualizados");
            
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String activarCuadrilla(DCuadrillas dCuadrillas){
        sSQL = "update tblcuadrilla set estado = ? where idCuadrilla = ?";
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Activo");
            pst.setInt(2, dCuadrillas.getIdCuadrilla());
            
            pst.executeUpdate();
            
            System.out.println("Datos Actualizados");
            
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public ResultSet buscarRepetido(String buscar){
        sSQL = "select nombreCuadrilla from tblcuadrilla where nombreCuadrilla = '" + buscar + "' && estado = 'Activo' order by idCuadrilla";
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

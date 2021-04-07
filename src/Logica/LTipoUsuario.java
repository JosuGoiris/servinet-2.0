/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DTipoUsuario;
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
public class LTipoUsuario {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    
    public DefaultTableModel mostrarTipos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id","Tipo de Usuario", "Descripción"};
        String dts [] = new String[3];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idTipoUsuario, tipoUsuario, Descripcion, estado from tbltipousuario \n"
                + "where idTipoUsuario = '" + buscar + "' or tipoUsuario like '%" + buscar + "' && estado = 'Activo' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTipoUsuario");
                dts[1] = rs.getString("tipoUsuario");
                dts[2] = rs.getString("Descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarTiposInactivos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id","Tipo de Usuario", "Descripción"};
        String dts [] = new String[3];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idTipoUsuario, tipoUsuario, Descripcion, estado from tbltipousuario \n"
                + "where idTipoUsuario = '" + buscar + "' or tipoUsuario like '%" + buscar + "' && estado = 'Inactivo' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTipoUsuario");
                dts[1] = rs.getString("tipoUsuario");
                dts[2] = rs.getString("Descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarTipos(DTipoUsuario dTipo){
        sSQL = "insert into tbltipousuario(tipoUsuario, Descripcion, estado) value(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dTipo.getNombre());
            pst.setString(2, dTipo.getDescripcion());
            pst.setString(3, "Activo");
            
            pst.executeUpdate();
            
            System.out.println("Datos Insertados");
            
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String editarTipos(DTipoUsuario dTipo){
        sSQL = "update tbltipousuario set tipoUsuario = ?, Descripcion = ? \n"
                + "where idTipoUsuario = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dTipo.getNombre());
            pst.setString(2, dTipo.getDescripcion());
            pst.setInt(3, dTipo.getIdTipoUsuario());
            
            pst.executeUpdate();
            
            System.out.println("Datos Actualizados");
            
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String eliminarTipos(DTipoUsuario dTipo){
        sSQL = "update tbltipousuario set estado = ? \n"
                + "where idTipoUsuario = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Inactivo");;
            pst.setInt(2, dTipo.getIdTipoUsuario());
            
            pst.executeUpdate();
            
            System.out.println("Datos Eliminados");
            
        } catch (Exception e) {
            System.out.println("Datos no Eliminados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String activarTipos(DTipoUsuario dTipo){
        sSQL = "update tbltipousuario set estado = ? \n"
                + "where idTipoUsuario = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Activo");;
            pst.setInt(2, dTipo.getIdTipoUsuario());
            
            pst.executeUpdate();
            
            System.out.println("Datos Activados");
            
        } catch (Exception e) {
            System.out.println("Datos no Activados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public ResultSet buscarRepetido(String buscar){
        sSQL = "select tipoUsuario from tbltipousuario where tipoUsuario = '" + buscar + "' && estado = 'Activo' order by idTipoUsuario";
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

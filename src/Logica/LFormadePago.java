/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DFormadePago;
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
public class LFormadePago {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarFormas(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Forma de Pago", "Descripción", "Estado"};
        String dts [] = new String[4];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idFormaPago, nombreForma, descripcion, estado from tblformadepago \n"
             + "where idFormaPago = '" + buscar + "' or nombreForma like '%" + buscar + "%' && estado = 'ACTIVO' order by idFormaPago";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idFormaPago");
                dts[1] = rs.getString("nombreForma");
                dts[2] = rs.getString("descripcion");
                dts[3] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarFormasMini(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Forma de Pago"};
        String dts [] = new String[2];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idFormaPago, nombreForma from tblformadepago \n"
             + "where idFormaPago = '" + buscar + "' or nombreForma like '%" + buscar + "%' && estado = 'ACTIVO' order by idFormaPago";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idFormaPago");
                dts[1] = rs.getString("nombreForma");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarFormasInactivas(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Forma de Pago", "Descripción", "Estado"};
        String dts [] = new String[4];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idFormaPago, nombreForma, descripcion, estado from tblformadepago \n"
             + "where idFormaPago = '" + buscar + "' or nombreForma like '%" + buscar + "%' && estado = 'INACTIVO' order by idFormaPago";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idFormaPago");
                dts[1] = rs.getString("nombreForma");
                dts[2] = rs.getString("descripcion");
                dts[3] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarFormas(DFormadePago dForma){
        String msg = null;
        sSQL = "insert into tblformadepago(nombreForma, descripcion, estado) value(?,?,?)";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dForma.getNombreForma());
            pst.setString(2, dForma.getDescripcion());
            pst.setString(3, "ACTIVO");
            
            pst.executeUpdate();
            
            cn.commit();

            System.out.println("Datos Insertados");

        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LFormadePago.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String editarForma(DFormadePago dFormadePago) {
        sSQL = "update tblformadepago set nombreForma = ?, descripcion = ? where idFormaPago = ?";
        try {
            cn.setAutoCommit(false);

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dFormadePago.getNombreForma());
            pst.setString(2, dFormadePago.getDescripcion());
            pst.setInt(3, dFormadePago.getIdFormadePago());
            
            pst.executeUpdate();

            cn.commit();
            
            System.out.println("Datos Actualizados");

        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String eliminarForma(DFormadePago dFormadePago) {
        sSQL = "update tblformadepago set estado = ? where idFormaPago = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, "INACTIVO");
            pst.setInt(2, dFormadePago.getIdFormadePago());

            pst.executeUpdate();

            System.out.println("Datos Eliminados");
        } catch (Exception e) {
            System.out.println("Datos no Eliminados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LFormadePago.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String activarForma(DFormadePago dFormadePago) {
        sSQL = "update tblformadepago set estado = ? where idFormaPago = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, "ACTIVO");
            pst.setInt(2, dFormadePago.getIdFormadePago());

            pst.executeUpdate();

            System.out.println("Datos Activos");
        } catch (Exception e) {
            System.out.println("Datos no Activados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LFormadePago.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}

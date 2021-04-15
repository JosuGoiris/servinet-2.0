/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DCaja;
import Datos.DDetalleMonto;
import Datos.DMonto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hecto
 */
public class LCaja {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    
    public String AbrirCaja(DCaja dCaja) throws SQLException{
        sSQL = "insert into tblcaja(fechaApertura, montoApertura, \n"
                + "usuarioId, estado) values(?,?,?,?)";
        try {
            cn.setAutoCommit(false);
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setDate(1, dCaja.getFechaApertura());
            pst.setDouble(2, dCaja.getMontoApertura());
            pst.setInt(3, dCaja.getIdUsuario());
            pst.setString(4, "Abierta");
            
            pst.executeUpdate();
            
            cn.commit();
            
            System.out.println("Caja Abierta");
        } catch (Exception e) {
            System.out.println("Error");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public int traerId(){
        int id = 0;
        sSQL = "select idCaja from tblcaja order by idCaja desc limit 1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
               id = rs.getInt("idCaja");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }

    public double traerMonto(){
        double monto = 0;
        sSQL = "select montoApertura from tblcaja order by idCaja desc limit 1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
               monto = rs.getDouble("montoApertura");
            }
            return monto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public String ActualizarMonto(DCaja dCaja){
        sSQL = "update tblcaja set montoApertura = ? order by idCaja desc limit 1";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDouble(1, dCaja.getMontoApertura());
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String traerEstado(){
        String estado = null;
        sSQL = "select estado from tblcaja order by idCaja desc limit 1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
               estado = rs.getString("estado");
            }
            return estado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return estado;
    }
    
    public String RealizarCarga(DCaja dCaja, DDetalleMonto dDetalleMonto){
        sSQL = "insert into tbldetallemonto(idDetalleMonto, cantidadTotal, montoId, cajaId) \n"
                + "values(?,?,?,?)";
        sSQL1 = "update tblcaja set estado = ? where idCaja = ?";
        sSQL2 = "update tblmonto set cantidadMonto = ? where idmonto = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setInt(1, dDetalleMonto.getCajaId());
            pst.setDouble(2, dDetalleMonto.getCantidadTotal());
            pst.setInt(3, dDetalleMonto.getMontoId());
            pst.setInt(4, dDetalleMonto.getCajaId());
            pst.executeUpdate();
            
            pst1.setString(1, "CERRADA");
            pst1.setInt(2, dCaja.getIdCaja());
            pst1.executeUpdate();
            
            pst2.setDouble(1, 0);
            pst2.setInt(2, 1);
            
            pst2.executeUpdate();
            
            cn.commit();
            
            System.out.println("Monto de la caja Guardado");
            JOptionPane.showMessageDialog(null, "La caja ha sido cerrada");
        } catch (Exception e) {
            System.out.println("Error");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public int traerIdMonto(){
        int id = 0;
        sSQL = "select idMonto from tblmonto order by idMonto desc limit 1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
               id = rs.getInt("idMonto");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }
    
    public double traerMontoFinal(){
        double monto = 0;
        sSQL = "select cantidadMonto from tblmonto order by idMonto desc limit 1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
               monto = rs.getDouble("cantidadMonto");
            }
            return monto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
}

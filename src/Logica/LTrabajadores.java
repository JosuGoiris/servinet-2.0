/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DPersona;
import Datos.DTrabajadores;
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
public class LTrabajadores {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    private String sSQL3 = null;
    
    public DefaultTableModel mostrarTrabajadores(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombres", "Apellidos", "Cédula", "Teléfono", "Puesto"};
        String dts [] = new String[6];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select t.idTrabajador, p.nombres, p.apellidos, d.idDireccion, d.nombreDireccion, pt.idPuestoTrabajo ,pt.nombrePuesto, p.cedulaIdent, p.telefono, \n"
                + "p.estado from tbltrabajador as t \n"
                + "inner join tblpersona as p on t.personaId = p.idPersona \n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId  = pt.idPuestoTrabajo \n"
                + "inner join tbldireccion as d on p.direccionId = d.idDireccion \n"
                + "where t.idTrabajador = '" + buscar + "' or p.nombres like '%" + buscar + "%' && p.estado = 'ACTIVO'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajador");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("telefono");
                dts[5] = rs.getString("nombrePuesto");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarTrabajadoresActivos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombres", "Apellidos", "Cédula", "Teléfono", "Dirección", "Puesto", "Estado"};
        String dts [] = new String[8];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select t.idTrabajador, p.nombres, p.apellidos, d.idDireccion, d.nombreDireccion, pt.idPuestoTrabajo ,pt.nombrePuesto, p.cedulaIdent, p.telefono, \n"
                + "p.estado from tbltrabajador as t \n"
                + "inner join tblpersona as p on t.personaId = p.idPersona \n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId  = pt.idPuestoTrabajo \n"
                + "inner join tbldireccion as d on p.direccionId = d.idDireccion \n"
                + "where t.idTrabajador = '" + buscar + "' or p.nombres like '%" + buscar + "%' && p.estado = 'ACTIVO'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajador");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("telefono");
                dts[5] = rs.getString("nombreDireccion");
                dts[6] = rs.getString("nombrePuesto");
                dts[7] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarTrabajadoresInactivos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombres", "Apellidos", "Cédula", "Teléfono", "Dirección", "Puesto", "Estado"};
        String dts [] = new String[8];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select t.idTrabajador, p.nombres, p.apellidos, d.idDireccion, d.nombreDireccion, pt.idPuestoTrabajo ,pt.nombrePuesto, p.cedulaIdent, p.telefono, \n"
                + "p.estado from tbltrabajador as t \n"
                + "inner join tblpersona as p on t.personaId = p.idPersona \n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId  = pt.idPuestoTrabajo \n"
                + "inner join tbldireccion as d on p.direccionId = d.idDireccion \n"
                + "where t.idTrabajador = '" + buscar + "' or p.nombres like '%" + buscar + "%' && p.estado = 'INACTIVO'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajador");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("telefono");
                dts[5] = rs.getString("nombreDireccion");
                dts[6] = rs.getString("nombrePuesto");
                dts[7] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarTrabajador(DTrabajadores dTrab, DPersona dPer){
        sSQL = "insert into tblpersona(nombres, apellidos, cedulaIdent, telefono, direccionId, estado) value(?,?,?,?,?,?)";
        sSQL1 = "insert into tbltrabajador(idTrabajador, puestotrabajoId, personaId) value((select idPersona from tblpersona order by idPersona desc limit 1),?, (select idPersona from tblpersona order by idPersona desc limit 1))";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            
            pst.setString(1, dPer.getNombre());
            pst.setString(2, dPer.getApellido());
            pst.setString(3, dPer.getCedulaIdent());
            pst.setString(4, dPer.getTelefono());
            pst.setInt(5, dPer.getDireccionId());
            pst.setString(6, "Activo");
            
            pst.executeUpdate();
            
            pst1.setInt(1, dTrab.getPuestotrabajadorId());
            
            pst1.executeUpdate();
            
            cn.commit();

            System.out.println("Datos Insertados");

        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LTrabajadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String editarTrabajador(DTrabajadores dTrab, DPersona dPer){
        sSQL = "update tblpersona set nombres = ?, apellidos = ?, cedulaIdent = ?, telefono = ?, direccionId = ? \n"
                + "where idPersona = ?";
        sSQL1 = "update tbltrabajador set puestotrabajoId = ?, personaId = ? where idTrabajador = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            
            pst.setString(1, dPer.getNombre());
            pst.setString(2, dPer.getApellido());
            pst.setString(3, dPer.getCedulaIdent());
            pst.setString(4, dPer.getTelefono());
            pst.setInt(5, dPer.getDireccionId());
            pst.setInt(6, dPer.getIdPersona());
            
            pst.executeUpdate();
            
            pst1.setInt(1, dTrab.getPuestotrabajadorId());
            pst1.setInt(2, dTrab.getPersonaId());
            pst1.setInt(3, dTrab.getIdTrabajador());
            
            pst1.executeUpdate();
            
            cn.commit();

            System.out.println("Datos Actualizados");

        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LTrabajadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String eliminarTrabajador(DPersona dPer){
        sSQL = "update tblpersona set estado = ? \n"
                + "where idPersona = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Inactivo");
            pst.setInt(2, dPer.getIdPersona());
            
            pst.executeUpdate();

            System.out.println("Datos Actualizados");

        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String activarTrabajador(DPersona dPer){
        sSQL = "update tblpersona set estado = ? \n"
                + "where idPersona = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Activo");
            pst.setInt(2, dPer.getIdPersona());
            
            pst.executeUpdate();

            System.out.println("Datos Actualizados");

        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public int traerIdDireccion(int ids) {
        int id = 0;
        sSQL = "select direccionId from tblpersona where idPersona = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("direccionId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdPuesto(int ids) {
        int id = 0;
        sSQL = "select puestotrabajoId from tbltrabajador where idTrabajador = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("puestotrabajoId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public ResultSet buscarRepetido(String buscar){
        sSQL = "select cedulaIdent from tblpersona where cedulaIdent = '" + buscar + "' && estado = 'Activo' order by idPersona";
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

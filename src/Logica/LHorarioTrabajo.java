/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DHorarioTrabajo;
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
 * @author hecto
 */
public class LHorarioTrabajo {
    Connection cn = ConexionSingleton.getConnection();
    
    public String msg = null;
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    private String sSQL3 = null;
    private String sSQL4 = null;
    private String sSQL5 = null;
    private String sSQL6 = null;
    
    public DefaultTableModel mostrarEntrada(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Nro. Cédula", "Cuadrilla", "Puesto", "Fecha Entrada", "Hora Entrada"};
        String dts [] = new String[8];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ht.idHorarioTrabajo, t.idTrabajador, pt.idPuestoTrabajo, pt.nombrePuesto, dc.idDetalleCuadrilla, c.nombreCuadrilla, p.nombres, p.apellidos, p.cedulaIdent, ht.fechaEntrada, \n"
                + "ht.horaEntrada, ht.horaSalida, ht.Observacion from tblhorariotrabajo as ht \n"
                + "inner join tbldetallecuadrilla as dc on ht.detallecuadrillaId = dc.idDetalleCuadrilla \n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador \n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo \n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla \n"
                + "inner join tblpersona as p on t.personaId = p.idPersona \n"
                + "where ht.idHorarioTrabajo = '" + buscar + "' or p.nombres like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idHorarioTrabajo");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("nombreCuadrilla");
                dts[5] = rs.getString("nombrePuesto");
                dts[6] = rs.getString("fechaEntrada");
                dts[7] = rs.getString("horaEntrada");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarSalida(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Nro. Cédula", "Cuadrilla", "Puesto", "Fecha Entrada", "Hora Salida"};
        String dts [] = new String[8];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ht.idHorarioTrabajo, t.idTrabajador, pt.idPuestoTrabajo, pt.nombrePuesto, dc.idDetalleCuadrilla, c.nombreCuadrilla, p.nombres, p.apellidos, p.cedulaIdent, ht.fechaEntrada, \n"
                + "ht.horaEntrada, ht.horaSalida, ht.Observacion from tblhorariotrabajo as ht \n"
                + "inner join tbldetallecuadrilla as dc on ht.detallecuadrillaId = dc.idDetalleCuadrilla \n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador \n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo \n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla \n"
                + "inner join tblpersona as p on t.personaId = p.idPersona \n"
                + "where ht.idHorarioTrabajo = '" + buscar + "' or p.nombres like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idHorarioTrabajo");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("nombreCuadrilla");
                dts[5] = rs.getString("nombrePuesto");
                dts[6] = rs.getString("fechaEntrada");
                dts[7] = rs.getString("horaSalida");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    public String insertarHorario(DHorarioTrabajo dHorarioTrabajo){
        sSQL = "insert into tblhorariotrabajo(horaEntrada, fechaEntrada, detallecuadrillaId) \n"
                + "value(?,?,?)";
        try {
            
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setTime(1, dHorarioTrabajo.getHoraEntrada());
            pst.setDate(2, dHorarioTrabajo.getFechaEntrada());
            pst.setInt(3, dHorarioTrabajo.getDetallecuadrillaId());
            
            pst.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Insertados");
            
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String insertarHorarioSalida(DHorarioTrabajo dHorarioTrabajo){
        sSQL = "update tblhorariotrabajo set horaSalida = ?, observacion = ? \n"
                + "where idHorarioTrabajo = ?";
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setTime(1, dHorarioTrabajo.getHoraSalida());
            pst.setString(2, dHorarioTrabajo.getObservacion());
            pst.setInt(3, dHorarioTrabajo.getIdHorarioTrabajo());
            
            pst.executeUpdate();
            
            System.out.println("Datos Insertados");
            
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public int traerIdTrabajador(String cedula){
        int id = 0;
        String idDetalle = "";
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        String idPuesto = "";
        String idCuadrilla = "";
        String Cuadrilla = "";
        sSQL = "select idTrabajador, p.cedulaIdent from tbltrabajador as t \n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idTrabajador");
            }
            return id; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }
    
    public int traerIdHorario(int ids){
        int id = 0;
        sSQL = "select idHorarioTrabajo from tblhorariotrabajo\n"
                + "where detallecuadrillaId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idHorarioTrabajo");
            }
            return id; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }
    
    public int traerIdDetalleCuadrilla(String cedula){
        String id = "";
        int idDetalle = 0;
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        String idPuesto = "";
        String idCuadrilla = "";
        String Cuadrilla = "";
        sSQL = "select dc.idDetalleCuadrilla, t.idTrabajador, p.cedulaIdent from tbldetallecuadrilla as dc\n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador\n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                idDetalle = rs.getInt("idDetalleCuadrilla");
            }
            return idDetalle; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return idDetalle;
    }
    
    public String traerNombreTrabajador(String cedula){
        String id = "";
        String idDetalle = "";
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        String idPuesto = "";
        String idCuadrilla = "";
        String Cuadrilla = "";
        sSQL = "select dc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, pt.idPuestoTrabajo, pt.nombrePuesto,\n"
                + "c.idCuadrilla, c.nombreCuadrilla from tbldetallecuadrilla as dc\n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador\n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo\n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                nombre = rs.getString("nombres");
            }
            return nombre; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String traerApellidoTrabajador(String cedula){
        String id = "";
        String idDetalle = "";
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        String idPuesto = "";
        String idCuadrilla = "";
        String Cuadrilla = "";
        sSQL = "select dc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, pt.idPuestoTrabajo, pt.nombrePuesto,\n"
                + "c.idCuadrilla, c.nombreCuadrilla from tbldetallecuadrilla as dc\n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador\n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo\n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                apellido = rs.getString("apellidos");
            }
            return apellido; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String traerCedulaIdentidad(String cedula){
        String id = "";
        String idDetalle = "";
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        String idPuesto = "";
        String idCuadrilla = "";
        String Cuadrilla = "";
        sSQL = "select dc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, pt.idPuestoTrabajo, pt.nombrePuesto,\n"
                + "c.idCuadrilla, c.nombreCuadrilla from tbldetallecuadrilla as dc\n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador\n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo\n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                cedulaIdent = rs.getString("cedulaIdent");
            }
            return cedulaIdent; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String traerPuesto(String cedula){
        String id = "";
        String idDetalle = "";
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        String idPuesto = "";
        String idCuadrilla = "";
        String Cuadrilla = "";
        sSQL = "select dc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, pt.idPuestoTrabajo, pt.nombrePuesto,\n"
                + "c.idCuadrilla, c.nombreCuadrilla from tbldetallecuadrilla as dc\n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador\n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo\n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                puesto = rs.getString("nombrePuesto");
            }
            return puesto; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public int traerIdPuesto(String cedula){
        String id = "";
        String idDetalle = "";
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        int idPuesto = 0;
        String idCuadrilla = "";
        String Cuadrilla = "";
        sSQL = "select dc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, pt.idPuestoTrabajo, pt.nombrePuesto,\n"
                + "c.idCuadrilla, c.nombreCuadrilla from tbldetallecuadrilla as dc\n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador\n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo\n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                idPuesto = rs.getInt("idPuestoTrabajo");
            }
            return idPuesto; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return idPuesto;
    }
    
    public int traerIdCuadrilla(String cedula){
        String id = "";
        String idDetalle = "";
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        String idPuesto = "";
        int idCuadrilla = 0;
        String Cuadrilla = "";
        sSQL = "select dc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, pt.idPuestoTrabajo, pt.nombrePuesto,\n"
                + "c.idCuadrilla, c.nombreCuadrilla from tbldetallecuadrilla as dc\n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador\n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo\n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                idCuadrilla = rs.getInt("idCuadrilla");
            }
            return idCuadrilla; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return idCuadrilla;
    }
    
    public String traerCuadrilla(String cedula){
        String id = "";
        String idDetalle = "";
        String nombre = "";
        String apellido = "";
        String cedulaIdent = "";
        String puesto = "";
        String idPuesto = "";
        String idCuadrilla = "";
        String Cuadrilla = "";
        sSQL = "select dc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, pt.idPuestoTrabajo, pt.nombrePuesto,\n"
                + "c.idCuadrilla, c.nombreCuadrilla from tbldetallecuadrilla as dc\n"
                + "inner join tbltrabajador as t on dc.trabajadorId = t.idTrabajador\n"
                + "inner join tblpersona as p on t.personaId = p.idPersona\n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo\n"
                + "inner join tblcuadrilla as c on dc.cuadrillaId = c.idCuadrilla\n"
                + "where p.cedulaIdent = '" + cedula + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                Cuadrilla = rs.getString("nombreCuadrilla");
            }
            return Cuadrilla; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}

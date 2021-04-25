/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DReclamos;
import Datos.DTrabajos;
import Datos.DTrabajosReclamos;
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
public class LClientes {
    Connection cn = ConexionSingleton.getConnection();
    public String msg = null;
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    private String sSQL3 = null;
    
    public DefaultTableModel mostrarReclamosPendientes(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Nro Cédula", "Fecha Reclamo", "IdServicio", "Servicio Cliente", "Descripción", "Estado"};
        String dts [] = new String[10];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select sc.idServiciodelCliente, r.idreclamos, p.nombres, p.apellidos, p.cedulaIdent,\n" 
                + "s.idServicio, s.nombreServicio, r.fechaReclamo, r.descripcion, r.estado from tblserviciodelcliente as sc \n" 
                + "inner join tblcliente as c on sc.clienteId = c.idCliente inner join tblpersona as p on \n" 
                + "c.personaId = p.idPersona inner join tbldetalleservicio as ds on sc.detalleservicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "inner join tblreclamos as r on sc.idServiciodelCliente = r.serviciodelclienteId \n"
                + "where sc.idServiciodelCliente = '" + buscar + "' or p.nombres like '%" + buscar + "%' && r.estado = 'PENDIENTE'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idreclamos");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaReclamo");
                dts[5] = rs.getString("idServicio");
                dts[6] = rs.getString("nombreServicio");
                dts[7] = rs.getString("descripcion");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarReclamosAtendidos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Nro Cédula", "Fecha Reclamo", "IdServicio", "Servicio Cliente", "Descripción", "Estado"};
        String dts [] = new String[10];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select sc.idServiciodelCliente, r.idreclamos, p.nombres, p.apellidos, p.cedulaIdent,\n" 
                + "s.idServicio, s.nombreServicio, r.fechaReclamo, r.descripcion, r.estado from tblserviciodelcliente as sc \n" 
                + "inner join tblcliente as c on sc.clienteId = c.idCliente inner join tblpersona as p on \n" 
                + "c.personaId = p.idPersona inner join tbldetalleservicio as ds on sc.detalleservicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "inner join tblreclamos as r on sc.idServiciodelCliente = r.serviciodelclienteId \n"
                + "where sc.idServiciodelCliente = '" + buscar + "' or p.nombres like '%" + buscar + "%' && r.estado = 'ATENDIDO'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idreclamos");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaReclamo");
                dts[5] = rs.getString("idServicio");
                dts[6] = rs.getString("nombreServicio");
                dts[7] = rs.getString("descripcion");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarClientes(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Nro Cédula", "IdServicio", "Servicio Cliente"};
        String dts [] = new String[6];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select sc.idServiciodelCliente, p.nombres, p.apellidos, p.cedulaIdent,\n" 
                + "s.idServicio, s.nombreServicio from tblserviciodelcliente as sc \n" 
                + "inner join tblcliente as c on sc.clienteId = c.idCliente inner join tblpersona as p on \n" 
                + "c.personaId = p.idPersona inner join tbldetalleservicio as ds on sc.detalleservicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "where sc.idServiciodelCliente = '" + buscar + "' or p.nombres like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idServiciodelCliente");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("idServicio");
                dts[5] = rs.getString("nombreServicio");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    public String insertarReclamos(DReclamos dReclamos){
        sSQL = "insert into tblreclamos(descripcion, fechaReclamo, estado, serviciodelclienteId, detallereclamosId) \n"
                + "value(?,?,?,?,?)";
        sSQL1 = "update tbldetallereclamos set reclamosSinAtender = reclamosSinAtender + 1, \n"
                + "totaldeReclamos = reclamosSinAtender + reclamosAtendidos where idDetalleReclamos = ?";
        sSQL2 = "insert into tbltrabajosreclamos(reclamosId, cuadrillaId) values((select idreclamos from \n"
                + "tblreclamos order by idReclamos desc limit 1), ?)";
        sSQL3 = "insert into tbltrabajos(trabajosreclamosId, detalletrabajoId, estado) values((select idTrabajosReclamos from \n"
                + "tbltrabajosreclamos order by idTrabajosReclamos desc limit 1), ?, ?)";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            
            pst.setString(1, dReclamos.getDescripcion());
            pst.setDate(2, dReclamos.getFechaReclamo());
            pst.setString(3, "PENDIENTE");
            pst.setInt(4, dReclamos.getServiciodelclientId());
            pst.setInt(5, 1);
            
            pst.executeUpdate();
            
            pst1.setInt(1, 1);
            
            pst1.executeUpdate();
            
            pst2.setInt(1, 1);
            
            pst2.executeUpdate();
            
            pst3.setInt(1, 1);
            pst3.setString(2, "PENDIENTE");
            
            pst3.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Insertados");
            JOptionPane.showMessageDialog(null, "Reclamo Enviado");
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
    
    public String enviarReclamos(DTrabajosReclamos dTrabajosReclamos, DTrabajos dTrabajos, DReclamos dReclamos){
        sSQL = "update tbltrabajos set estado = ? where idTrabajos = ?";
        sSQL1 = "update tblreclamos set estado = ? where idreclamos = ?";
        sSQL2 = "update tbltrabajosreclamos set cuadrillaId = ? where idTrabajosReclamos = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, "TRABAJANDO");
            pst.setInt(2, dTrabajos.getIdTrabajos());
            
            pst.executeUpdate();
            
            pst1.setString(1, "EN REVISION");
            pst1.setInt(2, dReclamos.getIdReclamos());
            
            pst1.executeUpdate();
            
            pst2.setInt(1, dTrabajosReclamos.getCuadrillasId());
            pst2.setInt(2, dTrabajosReclamos.getIdTrabajosReclamos());
            
            pst2.executeUpdate();
            
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
    
    public String atenderReclamos(DTrabajos dTrabajos, DReclamos dReclamos){
        sSQL = "update tbltrabajos set estado = ? where idTrabajos = ?";
        sSQL1 = "update tblreclamos set estado = ? where idreclamos = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            
            pst.setString(1, "TERMINADO");
            pst.setInt(2, dTrabajos.getIdTrabajos());
            
            pst.executeUpdate();
            
            pst1.setString(1, "ATENDIDO");
            pst1.setInt(2, dReclamos.getIdReclamos());
            
            pst1.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Insertados");
            JOptionPane.showMessageDialog(null, "El reclamo se ha atendido con éxito!");
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
    
    public String eliminarReclamos(DTrabajosReclamos dTrabajosReclamos, DTrabajos dTrabajos, DReclamos dReclamos){
        sSQL = "update tbltrabajos set estado = ? where idTrabajos = ?";
        sSQL1 = "update tblreclamos set estado = ? where idreclamos = ?";
        sSQL2 = "update tbltrabajosreclamos set cuadrillaId = ? where idTrabajosReclamos = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, "ELIMINADO");
            pst.setInt(2, dTrabajos.getIdTrabajos());
            
            pst.executeUpdate();
            
            pst1.setString(1, "PENDIENTE");
            pst1.setInt(2, dReclamos.getIdReclamos());
            
            pst1.executeUpdate();
            
            pst2.setInt(1, 1);
            pst2.setInt(2, dTrabajosReclamos.getIdTrabajosReclamos());
            
            pst2.executeUpdate();
            
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
    
    public int traerIdReclamos(int ids){
        int id = 0;
        sSQL = "select idreclamos from tblreclamos where serviciodelclienteId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idreclamos");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdTrabajosReclamos(int ids){
        int id = 0;
        sSQL = "select idTrabajosReclamos from tbltrabajosreclamos where reclamosId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idTrabajosReclamos");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
}

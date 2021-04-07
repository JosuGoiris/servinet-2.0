/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DServiciodelCliente;
import Datos.DSolicitud;
import Datos.DTrabajos;
import Datos.DTrabajosSolicitud;
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
public class LTrabajos {
    Connection cn = ConexionSingleton.getConnection();
    public String msg = null;
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    private String sSQL3 = null;
    
    public DefaultTableModel mostrarSolicitudesPendientes(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Cédula", "Fecha Solicitud", "Fecha Trabajo", "Servicio Solicitado", "Cuadrilla", "Estado"};
        String dts [] = new String[9];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ts.idTrabajosSolicitud, sd.idSolicitud, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, sd.fechaSolicitud, \n"
                + "sd.fechaActualizado, s.idServicio, s.nombreServicio, cd.idCuadrilla, cd.nombreCuadrilla, t.estado from tbltrabajossolicitud \n"
                + "as ts inner join tblsolicitud as sd on ts.solicitudId = sd.idSolicitud inner join \n"
                + "tblcliente as c on sd.clienteId = c.idCliente inner join tblpersona as p on \n"
                + "c.personaId = p.idPersona inner join tblservicio as s on sd.servicioId = s.idServicio \n"
                + "inner join tblcuadrilla as cd on ts.cuadrillaId = cd.idCuadrilla \n"
                + "inner join tbltrabajos as t on ts.idTrabajosSolicitud = t.trabajossolicitudId \n"
                + "where ts.idTrabajosSolicitud = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && t.estado = 'Pendiente'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajosSolicitud");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaSolicitud");
                dts[5] = rs.getString("fechaActualizado");
                dts[6] = rs.getString("nombreServicio");
                dts[7] = rs.getString("nombreCuadrilla");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarSolicitudesTrabajando(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Cédula", "Fecha Solicitud", "Fecha Trabajo", "Servicio Solicitado", "Cuadrilla", "Estado"};
        String dts [] = new String[9];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ts.idTrabajosSolicitud, sd.idSolicitud, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, sd.fechaSolicitud, \n"
                + "sd.fechaActualizado, s.idServicio, s.nombreServicio, cd.idCuadrilla, cd.nombreCuadrilla, t.estado from tbltrabajossolicitud \n"
                + "as ts inner join tblsolicitud as sd on ts.solicitudId = sd.idSolicitud inner join \n"
                + "tblcliente as c on sd.clienteId = c.idCliente inner join tblpersona as p on \n"
                + "c.personaId = p.idPersona inner join tblservicio as s on sd.servicioId = s.idServicio \n"
                + "inner join tblcuadrilla as cd on ts.cuadrillaId = cd.idCuadrilla \n"
                + "inner join tbltrabajos as t on ts.idTrabajosSolicitud = t.trabajossolicitudId \n"
                + "where ts.idTrabajosSolicitud = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && t.estado = 'Trabajando'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajosSolicitud");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaSolicitud");
                dts[5] = rs.getString("fechaActualizado");
                dts[6] = rs.getString("nombreServicio");
                dts[7] = rs.getString("nombreCuadrilla");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarSolicitudesTerminadas(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Cédula", "Fecha Solicitud", "Fecha Trabajo", "Servicio Solicitado", "Cuadrilla", "Estado"};
        String dts [] = new String[9];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ts.idTrabajosSolicitud, sd.idSolicitud, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, sd.fechaSolicitud, \n"
                + "sd.fechaActualizado, s.idServicio, s.nombreServicio, cd.idCuadrilla, cd.nombreCuadrilla, t.estado from tbltrabajossolicitud \n"
                + "as ts inner join tblsolicitud as sd on ts.solicitudId = sd.idSolicitud inner join \n"
                + "tblcliente as c on sd.clienteId = c.idCliente inner join tblpersona as p on \n"
                + "c.personaId = p.idPersona inner join tblservicio as s on sd.servicioId = s.idServicio \n"
                + "inner join tblcuadrilla as cd on ts.cuadrillaId = cd.idCuadrilla \n"
                + "inner join tbltrabajos as t on ts.idTrabajosSolicitud = t.trabajossolicitudId \n"
                + "where ts.idTrabajosSolicitud = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && t.estado = 'Terminado'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajosSolicitud");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaSolicitud");
                dts[5] = rs.getString("fechaActualizado");
                dts[6] = rs.getString("nombreServicio");
                dts[7] = rs.getString("nombreCuadrilla");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarSolicitudesAceptadas(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Cédula", "Fecha Solicitud", "Fecha Trabajo", "Servicio Solicitado", "Cuadrilla", "Estado"};
        String dts [] = new String[9];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ts.idTrabajosSolicitud, sd.idSolicitud, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, sd.fechaSolicitud, \n"
                + "sd.fechaActualizado, s.idServicio, s.nombreServicio, cd.idCuadrilla, cd.nombreCuadrilla, sd.estado from tbltrabajossolicitud \n"
                + "as ts inner join tblsolicitud as sd on ts.solicitudId = sd.idSolicitud inner join \n"
                + "tblcliente as c on sd.clienteId = c.idCliente inner join tblpersona as p on \n"
                + "c.personaId = p.idPersona inner join tblservicio as s on sd.servicioId = s.idServicio \n"
                + "inner join tblcuadrilla as cd on ts.cuadrillaId = cd.idCuadrilla \n"
                + "inner join tbltrabajos as t on ts.idTrabajosSolicitud = t.trabajossolicitudId \n"
                + "where ts.idTrabajosSolicitud = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && sd.estado = 'Aceptada'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajosSolicitud");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaSolicitud");
                dts[5] = rs.getString("fechaActualizado");
                dts[6] = rs.getString("nombreServicio");
                dts[7] = rs.getString("nombreCuadrilla");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarReclamosPendientes(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Cédula", "Fecha Reclamos", "Servicio Solicitado", "Cuadrilla", "Estado"};
        String dts [] = new String[8];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select tr.idTrabajosReclamos, rs.idReclamos, sc.idServiciodelCliente, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, rs.fechaReclamo, \n"
                + "s.idServicio, s.nombreServicio, cd.idCuadrilla, cd.nombreCuadrilla, rs.estado from tbltrabajosreclamos \n"
                + "as tr inner join tblreclamos as rs on tr.reclamosId = rs.idReclamos \n"
                + "inner join tblserviciodelcliente as sc on rs.serviciodelclienteId = sc.idServiciodelCliente \n"
                + "inner join tblcliente as c on sc.clienteId = c.idCliente inner join tblpersona as p on \n"
                + "c.personaId = p.idPersona inner join tbldetalleservicio as ds on sc.detalleservicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "inner join tblcuadrilla as cd on tr.cuadrillaId = cd.idCuadrilla \n"
                + "inner join tbltrabajos as t on tr.idTrabajosReclamos = t.trabajosreclamosId \n"
                + "where tr.idTrabajosReclamos = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && rs.estado = 'Pendiente'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajosReclamos");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaReclamo");
                dts[5] = rs.getString("nombreServicio");
                dts[6] = rs.getString("nombreCuadrilla");
                dts[7] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarReclamosEnRevision(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Cédula", "Fecha Reclamos", "Servicio Solicitado", "Cuadrilla", "Estado"};
        String dts [] = new String[8];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select tr.idTrabajosReclamos, rs.idReclamos, sc.idServiciodelCliente, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, rs.fechaReclamo, \n"
                + "s.idServicio, s.nombreServicio, cd.idCuadrilla, cd.nombreCuadrilla, t.estado from tbltrabajosreclamos \n"
                + "as tr inner join tblreclamos as rs on tr.reclamosId = rs.idReclamos \n"
                + "inner join tblserviciodelcliente as sc on rs.serviciodelclienteId = sc.idServiciodelCliente \n"
                + "inner join tblcliente as c on sc.clienteId = c.idCliente inner join tblpersona as p on \n"
                + "c.personaId = p.idPersona inner join tbldetalleservicio as ds on sc.detalleservicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "inner join tblcuadrilla as cd on tr.cuadrillaId = cd.idCuadrilla \n"
                + "inner join tbltrabajos as t on tr.idTrabajosReclamos = t.trabajosreclamosId \n"
                + "where tr.idTrabajosReclamos = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && t.estado = 'Trabajando'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajosReclamos");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaReclamo");
                dts[5] = rs.getString("nombreServicio");
                dts[6] = rs.getString("nombreCuadrilla");
                dts[7] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarReclamosTerminados(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Cédula", "Fecha Reclamos", "Servicio Solicitado", "Cuadrilla", "Estado"};
        String dts [] = new String[8];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select tr.idTrabajosReclamos, rs.idReclamos, sc.idServiciodelCliente, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, rs.fechaReclamo, \n"
                + "s.idServicio, s.nombreServicio, cd.idCuadrilla, cd.nombreCuadrilla, t.estado from tbltrabajosreclamos \n"
                + "as tr inner join tblreclamos as rs on tr.reclamosId = rs.idReclamos \n"
                + "inner join tblserviciodelcliente as sc on rs.serviciodelclienteId = sc.idServiciodelCliente \n"
                + "inner join tblcliente as c on sc.clienteId = c.idCliente inner join tblpersona as p on \n"
                + "c.personaId = p.idPersona inner join tbldetalleservicio as ds on sc.detalleservicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "inner join tblcuadrilla as cd on tr.cuadrillaId = cd.idCuadrilla \n"
                + "inner join tbltrabajos as t on tr.idTrabajosReclamos = t.trabajosreclamosId \n"
                + "where tr.idTrabajosReclamos = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && t.estado = 'Terminado'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajosReclamos");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaReclamo");
                dts[5] = rs.getString("nombreServicio");
                dts[6] = rs.getString("nombreCuadrilla");
                dts[7] = rs.getString("estado");
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
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Cédula", "Fecha Reclamos", "Servicio Solicitado", "Cuadrilla", "Estado"};
        String dts [] = new String[8];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select tr.idTrabajosReclamos, rs.idReclamos, sc.idServiciodelCliente, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, rs.fechaReclamo, \n"
                + "s.idServicio, s.nombreServicio, cd.idCuadrilla, cd.nombreCuadrilla, rs.estado from tbltrabajosreclamos \n"
                + "as tr inner join tblreclamos as rs on tr.reclamosId = rs.idReclamos \n"
                + "inner join tblserviciodelcliente as sc on rs.serviciodelclienteId = sc.idServiciodelCliente \n"
                + "inner join tblcliente as c on sc.clienteId = c.idCliente inner join tblpersona as p on \n"
                + "c.personaId = p.idPersona inner join tbldetalleservicio as ds on sc.detalleservicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "inner join tblcuadrilla as cd on tr.cuadrillaId = cd.idCuadrilla \n"
                + "inner join tbltrabajos as t on tr.idTrabajosReclamos = t.trabajosreclamosId \n"
                + "where tr.idTrabajosReclamos = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && rs.estado = 'En Revisión'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTrabajosReclamos");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaReclamo");
                dts[5] = rs.getString("nombreServicio");
                dts[6] = rs.getString("nombreCuadrilla");
                dts[7] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarTrabajos(DTrabajos dTrabajos, DTrabajosSolicitud dTrabajosSolicitud){
        sSQL = "update tbltrabajossolicitud set cuadrillaId = ? where idTrabajosSolicitud = ?";
        sSQL1 = "update tbltrabajos set estado = ? where idTrabajos = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            
            pst.setInt(1, dTrabajosSolicitud.getCuadrillaId());
            pst.setInt(2, dTrabajosSolicitud.getIdTrabajosSolicitud());
            
            pst.executeUpdate();
            
            pst1.setString(1, "Trabajando");
            pst1.setInt(2, dTrabajos.getIdTrabajos());
            
            pst1.executeUpdate();
            
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
    
    public String eliminarTrabajoSolicitud(DSolicitud dSolicitud, DServiciodelCliente dServiciodelCliente, DTrabajos dTrabajos, DTrabajosSolicitud dTrabajosSolicitud){
        sSQL = "update tbltrabajossolicitud set cuadrillaId = ? where idTrabajosSolicitud = ?";
        sSQL1 = "update tbltrabajos set estado = ? where idTrabajos = ?";
        sSQL2 = "update tblserviciodelcliente set estado = ? where idServiciodelCliente = ?";
        sSQL3 = "update tblsolicitud set estado = ? where idSolicitud = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            
            pst.setInt(1, 1);
            pst.setInt(2, dTrabajosSolicitud.getIdTrabajosSolicitud());
            
            pst.executeUpdate();
            
            pst1.setString(1, "Eliminado");
            pst1.setInt(2, dTrabajos.getIdTrabajos());
            
            pst1.executeUpdate();
            
            pst2.setString(1, "Pendiente");
            pst2.setInt(2, dServiciodelCliente.getIdServiciodelCliente());
            
            pst2.executeUpdate();
            
            pst3.setString(1, "Rechazada");
            pst3.setInt(2, dSolicitud.getIdSolicitud());
            
            pst3.executeUpdate();
            
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
    
    public int traerIdDetalleServicio(int ids){
        int id = 0;
        sSQL = "select detalleservicioId from tblserviciodelcliente where IdServiciodelCliente = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("detalleservicioId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdServicio(int ids){
        int id = 0;
        sSQL = "select servicioId from tbldetalleservicio where IdDetalleServicio = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("servicioId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdCuadrilla(String nombre){
        int id = 0;
        sSQL = "select idCuadrilla from tblcuadrilla where nombreCuadrilla = '" + nombre + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idCuadrilla");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdSolicitud(int ids){
        int id = 0;
        sSQL = "select solicitudId from tbltrabajossolicitud where idTrabajosSolicitud = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("solicitudId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdReclamos(int ids){
        int id = 0;
        sSQL = "select reclamosId from tbltrabajosreclamos where idTrabajosReclamos = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("reclamosId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdCliente(int ids){
        int id = 0;
        sSQL = "select clienteId from tblsolicitud where idSolicitud = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("clienteId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdClienteConReclamos(int ids){
        int id = 0;
        sSQL = "select serviciodelclienteId from tblreclamos where idReclamos = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("serviciodelclienteId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdTrabajos(int ids){
        int id = 0;
        sSQL = "select idTrabajos from tbltrabajos where trabajossolicitudId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idTrabajos");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdTrabajosConReclamos(int ids){
        int id = 0;
        sSQL = "select idTrabajos from tbltrabajos where trabajosreclamosId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idTrabajos");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public String traerDetalle(int ids){
        String detalle = "";
        sSQL = "select descripcion from tblreclamos where idReclamos = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                detalle = rs.getString("descripcion");
            }
            return detalle;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String realizarTrabajos(DSolicitud dSolicitud, DServiciodelCliente dServiciodelCliente, DTrabajos dTrabajos){
        sSQL = "update tbltrabajos set estado = ? where idTrabajos = ?";
        sSQL1 = "update tblserviciodelcliente set estado = ? where idServiciodelCliente = ?";
        sSQL2 = "update tblsolicitud set estado = ? where idSolicitud = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, "Terminado");
            pst.setInt(2, dTrabajos.getIdTrabajos());
            
            pst.executeUpdate();
            
            pst1.setString(1, "Activo");
            pst1.setInt(2, dServiciodelCliente.getIdServiciodelCliente());
            
            pst1.executeUpdate();
            
            pst2.setString(1, "Aceptada");
            pst2.setInt(2, dSolicitud.getIdSolicitud());
            
            pst2.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Insertados");
            JOptionPane.showMessageDialog(null, "El Trabajo se ha realizado con éxito!");
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
}

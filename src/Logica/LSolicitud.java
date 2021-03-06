/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DClientes;
import Datos.DDetalleSolicitud;
import Datos.DPersona;
import Datos.DServiciodelCliente;
import Datos.DSolicitud;
import Datos.DTrabajos;
import static Logica.ConexionSingleton.con;
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
public class LSolicitud {
    Connection cn = ConexionSingleton.getConnection();
    
    public String msg = null;
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    private String sSQL3 = null;
    private String sSQL4 = null;
    private String sSQL5 = null;
    private String sSQL6 = null;
    
    public DefaultTableModel mostrarSolicitud(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Nro. Cédula", "Fecha de Solicitud", "Fecha Actualizada", "Id", "Servicio Solicitado", "Estado"};
        String dts [] = new String[9];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select sd.idSolicitud, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, sd.fechaSolicitud, \n"
                + "sd.fechaActualizado, s.idServicio, s.nombreServicio, sd.estado from tblsolicitud as sd \n"
                + "inner join tblcliente as c on sd.clienteId = c.idCliente \n"
                + "inner join tblpersona as p on c.personaId = p.idPersona \n"
                + "inner join tblservicio as s on sd.servicioId = s.idServicio \n"
                + "where sd.idSolicitud = '" + buscar + "' or p.nombres like '%" + buscar + "%' and sd.estado = 'PENDIENTE'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idSolicitud");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaSolicitud");
                dts[5] = rs.getString("fechaActualizado");
                dts[6] = rs.getString("idServicio");
                dts[7] = rs.getString("nombreServicio");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarSolicitudesAceptada(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Apellido", "Nro. Cédula", "Fecha de Solicitud", "Fecha Actualizada", "Id", "Servicio Solicitado", "Estado"};
        String dts [] = new String[9];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select sd.idSolicitud, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, sd.fechaSolicitud, \n"
                + "sd.fechaActualizado, s.idServicio, s.nombreServicio, sd.estado from tblsolicitud as sd \n"
                + "inner join tblcliente as c on sd.clienteId = c.idCliente \n"
                + "inner join tblpersona as p on c.personaId = p.idPersona \n"
                + "inner join tblservicio as s on sd.servicioId = s.idServicio \n"
                + "where p.cedulaIdent = '" + buscar + "' or p.apellidos like '%" + buscar + "%' and sd.estado = 'ACEPTADA'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idSolicitud");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaSolicitud");
                dts[5] = rs.getString("fechaActualizado");
                dts[6] = rs.getString("idServicio");
                dts[7] = rs.getString("nombreServicio");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarSolicitud(DSolicitud dSolicitud, DPersona dPersona, DClientes dClientes, DServiciodelCliente dServiciodelCliente){
        sSQL = "insert into tblpersona(nombres, apellidos, cedulaIdent, telefono, \n"
                + "direccionId, estado) value(?,?,?,?,?,?)";
        sSQL1 = "insert into tblcliente(idCliente, Ruc, personaId) values((select \n"
                + "idPersona from tblpersona order by idPersona desc limit 1),?,(select idPersona \n"
                + "from tblpersona order by idPersona desc limit 1))";
        sSQL2 = "insert into tblsolicitud(idSolicitud, fechaSolicitud, fechaActualizado, clienteId, servicioId, detallesolicitudId, estado) \n"
                + "values((select idCliente from tblcliente order by idCliente desc limit 1),?,?, \n"
                + "(select idCliente from tblcliente order by idCliente desc limit 1),?,?,?)";
        sSQL5 = "update tbldetallesolicitud set solicitudesPendientes = solicitudesPendientes + 1, \n"
                + "totaldesolicitudes = solicitudesPendientes + solicitudesAceptadas + solicitudesCanceladas \n"
                + "where idDetalleSolicitud = ?";
        sSQL3 = "insert into tbltrabajossolicitud(idTrabajosSolicitud, solicitudId, cuadrillaId) values((select idSolicitud from tblsolicitud \n"
                + "order by idSolicitud desc limit 1), (select idSolicitud from tblsolicitud order by idSolicitud desc limit 1), ?)";
        sSQL6 = "insert into tbltrabajos(idTrabajos, trabajossolicitudId, detalletrabajoId, estado) values((select idTrabajosSolicitud from \n"
                + "tbltrabajossolicitud order by idTrabajosSolicitud desc limit 1),(select idTrabajosSolicitud from \n"
                + "tbltrabajossolicitud order by idTrabajosSolicitud desc limit 1),?,?)";
        sSQL4 = "insert into tblserviciodelcliente(idServiciodelCliente, fechaInicio, fechaPago, clienteId, detalleservicioId, estado, estadoFactura, multa, estadoMulta) \n"
                + "values((select idSolicitud from tblsolicitud order by idSolicitud desc limit 1),?,?, \n"
                + "(select idCliente from tblcliente order by idCliente desc limit 1),?,?,?,?,?)";
        
        try {
            
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            PreparedStatement pst4 = cn.prepareStatement(sSQL4);
            PreparedStatement pst5 = cn.prepareStatement(sSQL5);
            PreparedStatement pst6 = cn.prepareStatement(sSQL6);
            
            pst.setString(1, dPersona.getNombre());
            pst.setString(2, dPersona.getApellido());
            pst.setString(3, dPersona.getCedulaIdent());
            pst.setString(4, dPersona.getTelefono());
            pst.setInt(5, dPersona.getDireccionId());
            pst.setString(6, "PENDIENTE");
            
            pst.executeUpdate();
            
            pst1.setString(1, dClientes.getRuc());
            
            pst1.executeUpdate();
            
            pst2.setDate(1, dSolicitud.getFechaSolicitud());
            pst2.setDate(2, dSolicitud.getFechaActualizado());
            pst2.setInt(3, dSolicitud.getServicioId());
            pst2.setInt(4, 1);
            pst2.setString(5, "PENDIENTE");
            
            pst2.executeUpdate();
            
            pst3.setInt(1, 1);
            
            pst3.executeUpdate();
            
            pst4.setDate(1,  dServiciodelCliente.getFechaInicio());
            pst4.setDate(2, dServiciodelCliente.getFechaPago());
            pst4.setInt(3, dServiciodelCliente.getDetalleservicioId());
            pst4.setString(4, "PENDIENTE");
            pst4.setString(5, "SIN FACTURA");
            pst4.setDouble(6, 0);
            pst4.setString(7, "NO");
            
            pst4.executeUpdate();
            
            pst5.setInt(1, 1);
            
            pst5.executeUpdate();
            
            pst6.setInt(1, 1);
            
            pst6.setString(2, "PENDIENTE");
            
            pst6.executeUpdate();
            
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
    
    public String editarSolicitud(DSolicitud dSolicitud, DPersona dPersona, DClientes dClientes, DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblpersona set nombres = ?, apellidos = ?, cedulaIdent = ?, telefono = ?, \n"
                + "direccionId = ? where idPersona = ?";
        sSQL1 = "update tblcliente set Ruc = ?\n"
                + "where idCliente = ?";
        sSQL2 = "update tblsolicitud set servicioId = ? \n"
                + "where idSolicitud = ?";
        sSQL4 = "update tblserviciodelcliente set detalleservicioId = ? \n"
                + "where idServiciodelCliente = ?";
        
        try {
            
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            PreparedStatement pst4 = cn.prepareStatement(sSQL4);
            
            pst.setString(1, dPersona.getNombre());
            pst.setString(2, dPersona.getApellido());
            pst.setString(3, dPersona.getCedulaIdent());
            pst.setString(4, dPersona.getTelefono());
            pst.setInt(5, dPersona.getDireccionId());
            pst.setInt(6, dPersona.getIdPersona());
            
            pst.executeUpdate();
            
            pst1.setString(1, dClientes.getRuc());
            pst1.setInt(2, dClientes.getId());
            
            pst1.executeUpdate();
            
            pst2.setInt(1, dSolicitud.getServicioId());
            pst2.setInt(2, dSolicitud.getIdSolicitud());
            
            pst2.executeUpdate();
            
            pst4.setInt(1, dServiciodelCliente.getDetalleservicioId());
            pst4.setInt(2, dServiciodelCliente.getIdServiciodelCliente());
            
            pst4.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Actualizados");
            
            JOptionPane.showMessageDialog(null, "Datos Actualizados");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String updateSolicitud(DSolicitud dSolicitud, DServiciodelCliente dSer){
        sSQL = "update tblsolicitud set fechaActualizado = ?, estado = ? where idSolicitud = ?";
        sSQL1 = "update tblserviciodelcliente set estado = 'EN ESPERA' where idServiciodelCliente = ?";
        sSQL2 = "update tbldetallesolicitud set solicitudesAceptadas = solicitudesAceptadas + 1, \n"
                + "solicitudesPendientes = solicitudesPendientes - 1 \n"
                + "where iddetallesolicitud = ?";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setDate(1, dSolicitud.getFechaActualizado());
            pst.setString(2, "TRABAJANDO");
            pst.setInt(3, dSolicitud.getIdSolicitud());
            
            pst.executeUpdate();
            
            pst1.setInt(1, dSer.getIdServiciodelCliente());
            
            pst1.executeUpdate();
            
            pst2.setInt(1, 1);
            
            pst2.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Actualizados");
            
            JOptionPane.showMessageDialog(null, "La Solicitud se está trabajando...");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String editarSolicitud(DSolicitud dSolicitud, DPersona dPersona, DClientes dClientes){
        sSQL = "update tblsolicitud set clienteId = ?, servicioId = ?, estado = 'PENDIENTE' where idSolicitud = ?";
        sSQL1 = "update tblpersona set nombres = ?, apellidos = ?, cedulaIdent = ?, estado = 'PENDIENTE' where idPersona = ?";
        sSQL2 = "update tblcliente set personaId = ? where idCliente = ?";
        
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setInt(1, dSolicitud.getClienteId());
            pst.setInt(2, dSolicitud.getServicioId());
            pst.setInt(3, dSolicitud.getIdSolicitud());
            
            pst.executeUpdate();
            
            pst1.setString(1, dPersona.getNombre());
            pst1.setString(2, dPersona.getApellido());
            pst1.setString(3, dPersona.getCedulaIdent());
            pst1.setInt(4, dPersona.getIdPersona());
            
            pst1.executeUpdate();
            
            pst2.setInt(1, dClientes.getPersonaId());
            pst2.setInt(2, dClientes.getId());
            
            pst2.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Actualizados");
            
            JOptionPane.showMessageDialog(null, "Se ha editado la Solicitud!");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String eliminarSolicitudPendiente(DSolicitud dSolicitud, DPersona dPersona, DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblsolicitud set estado = 'INACTIVO' where idSolicitud = ?";
        sSQL1 = "update tblpersona set estado = 'INACTIVO' where idPersona = ?";
        sSQL2 = "update tblserviciodelcliente set estado = 'INACTIVO' where idServiciodelCliente = ?";
        sSQL3 = "update tbldetallesolicitud set solicitudesPendientes = solicitudesPendientes - 1, \n"
                + "solicitudesCanceladas = solicitudesCanceladas + 1, \n"
                + "totaldesolicitudes = solicitudesPendientes + solicitudesAceptadas + solicitudesCanceladas";
        
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            
            pst.setInt(1, dSolicitud.getIdSolicitud());
            
            pst.executeUpdate();
            
            pst1.setInt(1, dPersona.getIdPersona());
            
            pst1.executeUpdate();
            
            pst2.setInt(1 , dServiciodelCliente.getIdServiciodelCliente());
            
            pst2.executeUpdate();
            
            pst3.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Actualizados");
            
            JOptionPane.showMessageDialog(null, "La Solicitud ha sido Eliminada con exito!");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String eliminarSolicitudAceptada(DSolicitud dSolicitud){
        sSQL = "update tblsolicitud set estado = 'INACTIVO' where idSolicitud = ?";
        sSQL3 = "update tbldetallesolicitud set solicitudesAceptadas = solicitudesAceptadas - 1, \n"
                + "solicitudesCanceladas = solicitudesCanceladas + 1, \n"
                + "totaldesolicitudes = solicitudesPendientes + solicitudesAceptadas + solicitudesCanceladas";
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            
            pst.setInt(1, dSolicitud.getIdSolicitud());
            
            pst.executeUpdate();
            
            pst3.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Actualizados");
            
            JOptionPane.showMessageDialog(null, "La Solicitud ha sido Eliminada con exito!");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String DeshacerPendiente(DSolicitud dSolicitud, DPersona dPersona, DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblsolicitud set estado = 'PENDIENTE' where idSolicitud = ?";
        sSQL1 = "update tblpersona set estado = 'PENDIENTE' where idPersona = ?";
        sSQL2 = "update tblserviciodelcliente set estado = 'PENDIENTE' where idServiciodelCliente = ?";
        sSQL3 = "update tbldetallesolicitud set solicitudesAceptadas = solicitudesAceptadas + 1, \n"
                + "solicitudesCanceladas = solicitudesCanceladas - 1, \n"
                + "totaldesolicitudes = solicitudesPendientes + solicitudesAceptadas + solicitudesCanceladas";
        
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            
            pst.setInt(1, dSolicitud.getIdSolicitud());
            
            pst.executeUpdate();
            
            pst1.setInt(1, dPersona.getIdPersona());
            
            pst1.executeUpdate();
            
            pst2.setInt(1 , dServiciodelCliente.getIdServiciodelCliente());
            
            pst2.executeUpdate();
            
            pst3.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Actualizados");
            
            JOptionPane.showMessageDialog(null, "La solicitud eliminada se ha traido de vuelta");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String DeshacerActivo(DSolicitud dSolicitud){
        sSQL = "update tblsolicitud set estado = 'ACEPTADA' where idSolicitud = ?";
        sSQL3 = "update tbldetallesolicitud set solicitudesAceptadas = solicitudesAceptadas + 1, \n"
                + "solicitudesCanceladas = solicitudesCanceladas - 1, \n"
                + "totaldesolicitudes = solicitudesPendientes + solicitudesAceptadas + solicitudesCanceladas";
        
        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            
            pst.setInt(1, dSolicitud.getIdSolicitud());
            
            pst.executeUpdate();
            
            pst3.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Actualizados");
            
            JOptionPane.showMessageDialog(null, "La solicitud eliminada se ha traido de vuelta");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
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
        sSQL = "select idDetalleServicio from tbldetalleservicio where servicioId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idDetalleServicio");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public String traerTelefono(int ids){
        String telefono = null;
        sSQL = "select telefono from tblpersona where idPersona = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                telefono = rs.getString("telefono");
            }
            return telefono;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String traerRUC(int ids){
        String RUC = null;
        sSQL = "select Ruc from tblcliente where idCliente = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                RUC = rs.getString("Ruc");
            }
            return RUC;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public int traerIdDireccion(int ids){
        int id = 0;
        sSQL = "select direccionId from tblpersona where idPersona = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("direccionId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public String traerDireccion(int ids){
        String direccion = null;
        sSQL = "select nombreDireccion from tbldireccion where idDireccion = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                direccion = rs.getString("nombreDireccion");
            }
            return direccion;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public int traerIdBarrio(int ids){
        int id = 0;
        sSQL = "select idZona from tbldireccion where idDireccion = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idZona");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public String traerBarrio(int ids){
        String barrio = null;
        sSQL = "select nombreZona from tblzona where idZona = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                barrio = rs.getString("nombreZona");
            }
            return barrio;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}

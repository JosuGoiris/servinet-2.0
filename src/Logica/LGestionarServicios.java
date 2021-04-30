/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DServiciodelCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hecto
 */
public class LGestionarServicios {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    
    public DefaultTableModel mostrarServiciosdelCliente(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombres", "Apellidos", "Cédula", "Nombre Servicio", "Conexion", "Precio", "Inicio", "Pago", "Estado"};
        String dts [] = new String[10];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select sc.idServiciodelCliente, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, \n"
                + "s.idServicio, s.nombreServicio, ds.conexion, ds.precio, sc.fechaInicio, sc.fechaPago, sc.estado from \n"
                + "tblserviciodelcliente as sc \n"
                + "inner join tblcliente as c on sc.clienteId = c.idCliente \n"
                + "inner join tblpersona as p on c.personaId = p.idPersona \n"
                + "inner join tbldetalleservicio as ds on sc.detalleServicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "where sc.idServiciodelCliente = '" + buscar + "' or p.cedulaIdent like '%" + buscar + "%' && sc.estado = 'Activo'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idserviciodelcliente");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("nombreServicio");
                dts[5] = rs.getString("conexion");
                dts[6] = rs.getString("precio");
                dts[7] = rs.getString("fechaInicio");
                dts[8] = rs.getString("fechaPago");
                dts[9] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarServiciosInactivos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombres", "Apellidos", "Cédula", "Nombre Servicio", "Conexion", "Precio", "Inicio", "Pago", "Estado"};
        String dts [] = new String[10];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select sc.idServiciodelCliente, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, \n"
                + "s.idServicio, s.nombreServicio, ds.conexion, ds.precio, sc.fechaInicio, sc.fechaPago, sc.estado from \n"
                + "tblserviciodelcliente as sc \n"
                + "inner join tblcliente as c on sc.clienteId = c.idCliente \n"
                + "inner join tblpersona as p on c.personaId = p.idPersona \n"
                + "inner join tbldetalleservicio as ds on sc.detalleServicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "where sc.idServiciodelCliente = '" + buscar + "' or p.cedulaIdent like '%" + buscar + "%' && sc.estado = 'Inactivo'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idserviciodelcliente");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("nombreServicio");
                dts[5] = rs.getString("conexion");
                dts[6] = rs.getString("precio");
                dts[7] = rs.getString("fechaInicio");
                dts[8] = rs.getString("fechaPago");
                dts[9] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String actualizarServicios(DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblserviciodelcliente set estado = ? where idServiciodelCliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, "INACTIVO");
            pst.setInt(2, dServiciodelCliente.getIdServiciodelCliente());
            pst.executeUpdate();
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String EstadoActivo(DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblserviciodelcliente set fechaPago = ?, estado = ?, estadoFactura = ? where idServiciodelCliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDate(1, dServiciodelCliente.getFechaPago());
            pst.setString(2, "ACTIVO");
            pst.setString(3, "FACTURA PAGADA");
            pst.setInt(4, dServiciodelCliente.getIdServiciodelCliente());
            pst.executeUpdate();
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String multa(DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblserviciodelcliente set multa = ?, estadoMulta = ? where idServiciodelCliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDouble(1, 0);
            pst.setString(2, "NO");
            pst.setInt(3, dServiciodelCliente.getIdServiciodelCliente());
            pst.executeUpdate();
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String actualizarEstados(DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblserviciodelcliente set estado = ?, estadoFactura = ?, multa = ?, estadoMulta = ? where idServiciodelCliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, "INACTIVO");
            pst.setString(2, "FACTURA SIN PAGAR");
            pst.setDouble(3, 50000);
            pst.setString(4, "SI");
            pst.setInt(5, dServiciodelCliente.getIdServiciodelCliente());
            pst.executeUpdate();
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public int traerId(int ids){
        int id = 0;
        sSQL = "select detalleservicioId from tblserviciodelcliente where idServiciodelCliente = '" + ids + "'";
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
    
    public String traerEstadoFactura(int id){
        String estado = null;
        sSQL = "select estadoFactura from tblserviciodelcliente where idServiciodelCliente = '" + id + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                estado = rs.getString("estadoFactura");
            }
            return estado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String traerEstadoMulta(int id){
        String estado = null;
        sSQL = "select estadoMulta from tblserviciodelcliente where idServiciodelCliente = '" + id + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                estado = rs.getString("estadoMulta");
            }
            return estado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    
    public double traerSobrecargo(int ids){
        double sobrecargo = 0;
        sSQL = "select multa from tblserviciodelcliente where idServiciodelCliente = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                sobrecargo = rs.getDouble("multa");
            }
            return sobrecargo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public double traerPrecio(int ids){
        double precio = 0;
        sSQL = "select precio from tbldetalleservicio where idDetalleServicio = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                precio = rs.getDouble("precio");
            }
            return precio;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public String actualizarMulta(DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblserviciodelcliente set multa = ? where idServiciodelCliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDouble(1, dServiciodelCliente.getMulta());
            pst.setInt(2, dServiciodelCliente.getIdServiciodelCliente());
            pst.executeUpdate();
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String estadoMulta(DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblserviciodelcliente set estadoMulta = ? where idServiciodelCliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dServiciodelCliente.getEstadoMulta());
            pst.setInt(2, dServiciodelCliente.getIdServiciodelCliente());
            pst.executeUpdate();
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}

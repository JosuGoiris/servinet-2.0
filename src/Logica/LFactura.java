/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DDetalleFactura;
import Datos.DFactura;
import Datos.DServiciodelCliente;
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
public class LFactura {
    Connection cn = ConexionSingleton.getConnection();
    
    public String msg = null;
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    private String sSQL3 = null;
    
    public DefaultTableModel mostrarFactura(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"idFactura", "Nombre", "Apellido", "Nro de Cedula", "Fecha Emision", "Fecha Vencimiento", "Precio", "Sobrecargo", "Total a Pagar", "Estado"};
        String dts [] = new String[10];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select f.idfactura, c.idCliente, p.nombres, p.apellidos, p.cedulaIdent, f.fechaEmision, f.fechaVencimiento, df.precio, df.sobrecargos, df.total, df.estado from \n"
                + "from tbldetallefactura as df inner join tblfactura as f on df.facturaId = f.idFactura \n"
                + "inner join tblcliente as c on f.clienteId = c.idCliente \n"
                + "inner join tblpersona as p on c.personaId = p.idPersona \n"
                + "where f.idFactura = '" + buscar + "' or p.cedulaIdent like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idfactura");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaEmision");
                dts[5] = rs.getString("fechaVencimiento");
                dts[6] = rs.getString("precio");
                dts[7] = rs.getString("sobrecargos");
                dts[8] = rs.getString("total");
                dts[9] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarFactura(DFactura dFactura, DDetalleFactura dDetalleFactura, DServiciodelCliente dServiciodelCliente){
        sSQL = "insert into tblfactura(fechaEmision, fechaVencimiento, clienteId) \n"
                + "values(?,?,?)";
        sSQL1 = "insert into tbldetallefactura(iddetallefactura, descripcion, mesPago, precio, sobrecargos, total, \n"
                + "facturaId, serviciodelclienteId, cantidadfacturasId, montoId, estado) values((select idfactura from tblfactura order by idfactura \n"
                + "desc limit 1),?,?,?,?,?, (select idfactura from tblfactura order by idfactura desc limit 1), ?, ?, ?, ?)";
        sSQL2 = "update tblcantidadfacturas set facturasPendientes = facturasPendientes + 1, \n"
                + "totaldeFacturas = facturasPendientes + facturasPagadas + facturasVencidas";
        sSQL3 = "update tblserviciodelcliente set estadoFactura = ? where idServiciodelCliente = ?";
        try {
            cn.setAutoCommit(false);
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            
            pst.setDate(1, dFactura.getFechaEmision());
            pst.setDate(2, dFactura.getFechaVencimiento());
            pst.setInt(3, dFactura.getClienteId());
            
            pst.executeUpdate();
            
            pst1.setString(1, dDetalleFactura.getDescripcion());
            pst1.setString(2, dDetalleFactura.getMesPago());
            pst1.setDouble(3, dDetalleFactura.getPrecio());
            pst1.setDouble(4, dDetalleFactura.getSobrecargos());
            pst1.setDouble(5, dDetalleFactura.getTotal());
            pst1.setInt(6, dDetalleFactura.getServiciodelclienteId());
            pst1.setInt(7, 1);
            pst1.setInt(8, 1);
            pst1.setString(9, "Pendiente");
            
            pst1.executeUpdate();
            
            pst2.executeUpdate();
            
            pst3.setString(1, "Con Factura");
            pst3.setInt(2, dServiciodelCliente.getIdServiciodelCliente());
            
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
    
    public String actualizarFactura(DDetalleFactura dDetalleFactura){
        sSQL = "update tbldetallefactura set estado = ? where idDetalleFactura = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, "Factura Sin Pagar");
            pst.setInt(2, dDetalleFactura.getIdDetalleFactura());
            pst.executeUpdate();
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public int traerIdFactura(int ids){
        int id = 0;
        sSQL = "select idFactura from tblfactura where clienteId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                id = rs.getInt("idFactura");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
}

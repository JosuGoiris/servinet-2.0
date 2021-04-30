/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DCaja;
import Datos.DDetalleFactura;
import Datos.DDetallePagos;
import Datos.DMonto;
import Datos.DPagos;
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
public class LPagos {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    private String sSQL3 = null;
    private String sSQL4 = null;
    private String sSQL5 = null;
    
    public DefaultTableModel mostrarPagos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"IdFactura", "IdCliente", "Nombre Cliente", "Nro. C.I.", "Emisión", "Vencimiento", "Nombre Servicio", "Total", "Estado"};
        String dts [] = new String[9];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select f.idfactura, c.idCliente, concat(p.nombres,' ', p.apellidos) as 'cliente', \n"
                + "p.cedulaIdent, f.fechaEmision, f.fechaVencimiento, sc.idServiciodelCliente, s.nombreServicio, df.total, df.estado from tbldetallefactura as df \n"
                + "inner join tblfactura as f on df.facturaId = f.idfactura \n"
                + "inner join tblcliente as c on f.clienteId = c.idCliente \n" 
                + "inner join tblpersona as p on c.personaId = p.idPersona \n"
                + "inner join tblserviciodelcliente as sc on df.serviciodelclienteId = sc.idServiciodelCliente \n"
                + "inner join tbldetalleservicio as ds on sc.detalleServicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "where f.idfactura = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && df.estado = 'PENDIENTE'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idfactura");
                dts[1] = rs.getString("idCliente");
                dts[2] = rs.getString("cliente");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaEmision");
                dts[5] = rs.getString("fechaVencimiento");
                dts[6] = rs.getString("nombreServicio");
                dts[7] = rs.getString("total");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarFacturaNoPagada(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"IdFactura", "IdCliente", "Nombre Cliente", "Nro. C.I.", "Emisión", "Vencimiento", "Nombre Servicio", "Total", "Estado"};
        String dts [] = new String[9];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select f.idfactura, c.idCliente, concat(p.nombres,' ', p.apellidos) as 'cliente', \n"
                + "p.cedulaIdent, f.fechaEmision, f.fechaVencimiento, sc.idServiciodelCliente, s.nombreServicio, df.total, df.estado from tbldetallefactura as df \n"
                + "inner join tblfactura as f on df.facturaId = f.idfactura \n"
                + "inner join tblcliente as c on f.clienteId = c.idCliente \n" 
                + "inner join tblpersona as p on c.personaId = p.idPersona \n"
                + "inner join tblserviciodelcliente as sc on df.serviciodelclienteId = sc.idServiciodelCliente \n"
                + "inner join tbldetalleservicio as ds on sc.detalleServicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "where f.idfactura = '" + buscar + "' or s.nombreServicio like '%" + buscar + "%' && df.estado = 'FACTURA SIN PAGAR'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idfactura");
                dts[1] = rs.getString("idCliente");
                dts[2] = rs.getString("cliente");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("fechaEmision");
                dts[5] = rs.getString("fechaVencimiento");
                dts[6] = rs.getString("nombreServicio");
                dts[7] = rs.getString("total");
                dts[8] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarFacturasPagadas(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"IdPago", "Nombre Cliente", "Nro. C.I.", "Nombre Servicio", "Fecha Pago", "Total", "Estado"};
        String dts [] = new String[7];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select pa.idpagos, c.idCliente, concat(p.nombres,' ', p.apellidos) as 'cliente', \n"
                + "p.cedulaIdent, pa.fechaRealizado, sc.idServiciodelCliente, s.nombreServicio, df.total, pa.estado from tblpagos as pa \n"
                + "inner join tbldetallefactura as df on pa.detallefacturaId = df.idDetalleFactura \n"
                + "inner join tblserviciodelcliente as sc on pa.serviciodelclienteId = sc.idServiciodelCliente \n"
                + "inner join tblcliente as c on sc.clienteId = c.idCliente \n" 
                + "inner join tblpersona as p on c.personaId = p.idPersona \n"
                + "inner join tbldetalleservicio as ds on sc.detalleServicioId = ds.idDetalleServicio \n"
                + "inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "where pa.idpagos = '" + buscar + "' or p.cedulaIdent like '%" + buscar + "%' && pa.estado = 'PAGADO'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idpagos");
                dts[1] = rs.getString("cliente");
                dts[2] = rs.getString("cedulaIdent");
                dts[3] = rs.getString("nombreServicio");
                dts[4] = rs.getString("fechaRealizado");
                dts[5] = rs.getString("total");
                dts[6] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
     
    
    
    public String RealizarPago(DPagos dPagos, DDetalleFactura dDetalleFactura){
        sSQL = "insert into tblpagos(fechaPago, fechaRealizado, estado, serviciodelclienteId, formapagoId, \n"
                + "detallefacturaId) values(?,?,?,?,?,?)";
        sSQL1 = "update tbldetallepagos set cantidad = cantidad + 1 where iddetallepagos = ?";
        sSQL2 = "update tbldetallefactura set estado = ? where iddetallefactura = ?";
        sSQL3 = "update tblcantidadfacturas set facturasPendientes = facturasPendientes - 1, \n"
                + "facturasPagadas = facturasPagadas + 1, totaldeFacturas = facturasPendientes + facturasPagadas + facturasVencidas \n"
                + "where idCantidadFacturas = ?";
        sSQL4 = "update tblmonto as m \n" 
                + "inner join tbldetallefactura as df on m.idMonto = df.montoId \n" 
                + "set m.cantidadMonto = m.cantidadMonto + df.total \n" 
                + "where df.iddetallefactura = ?";
        
        try {
            cn.setAutoCommit(false);
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            PreparedStatement pst3 = cn.prepareStatement(sSQL3);
            PreparedStatement pst4 = cn.prepareStatement(sSQL4);
            
            pst.setDate(1, dPagos.getFechaPago());
            pst.setDate(2, dPagos.getFechaRealizado());
            pst.setString(3, "PAGADO");
            pst.setInt(4, dPagos.getServiciodelclienteId());
            pst.setInt(5, dPagos.getFormadepagoId());
            pst.setInt(6, dPagos.getDetallefacturaId());
            
            pst.executeUpdate();
            
            pst1.setInt(1, 1);
            
            pst1.executeUpdate();
            
            pst2.setString(1, "PAGADO");
            pst2.setInt(2, dDetalleFactura.getIdDetalleFactura());
            
            pst2.executeUpdate();
            
            pst3.setInt(1, 1);
            pst3.executeUpdate();
            
            pst4.setInt(1, dDetalleFactura.getIdDetalleFactura());
            pst4.executeUpdate();
            
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
    
    public String restarMonto(DCaja dCaja){
        sSQL = "update tblcaja set montoApertura = ? where idCaja = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setDouble(1, dCaja.getMontoApertura());
            pst.setInt(2, dCaja.getIdCaja());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    
}

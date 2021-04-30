/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DDetalleCuadrilla;
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
public class LConfiguracionCuadrilla {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;

    public DefaultTableModel mostrarConfiguracion(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"Id", "Nombre", "Apellido", "Cédula", "Telefono", "Puesto", "Cuadrilla"};
        String dts[] = new String[7];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select dtc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, p.telefono, pt.nombrePuesto, \n"
                + "c.nombreCuadrilla from tbldetallecuadrilla as dtc \n"
                + "inner join tbltrabajador as t on dtc.trabajadorId = t.idTrabajador \n"
                + "inner join tblcuadrilla as c on dtc.cuadrillaId = c.idCuadrilla \n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo \n"
                + "inner join tblpersona as p on t.personaId = p.idPersona \n"
                + "where dtc.idDetalleCuadrilla = '" + buscar + "' or dtc.cuadrillaId = '" + buscar + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idDetalleCuadrilla");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("telefono");
                dts[5] = rs.getString("nombrePuesto");
                dts[6] = rs.getString("nombreCuadrilla");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
    
     public DefaultTableModel mostrarConfiguracionNula(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"Id", "Nombre", "Apellido", "Cédula", "Telefono", "Puesto", "Cuadrilla"};
        String dts[] = new String[7];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select dtc.idDetalleCuadrilla, t.idTrabajador, p.nombres, p.apellidos, p.cedulaIdent, p.telefono, pt.nombrePuesto, \n"
                + "c.nombreCuadrilla from tbldetallecuadrilla as dtc \n"
                + "inner join tbltrabajador as t on dtc.trabajadorId = t.idTrabajador \n"
                + "inner join tblcuadrilla as c on dtc.cuadrillaId = c.idCuadrilla \n"
                + "inner join tblpuestotrabajo as pt on t.puestotrabajoId = pt.idPuestoTrabajo \n"
                + "inner join tblpersona as p on t.personaId = p.idPersona \n"
                + "where dtc.idDetalleCuadrilla = '" + buscar + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idDetalleCuadrilla");
                dts[1] = rs.getString("nombres");
                dts[2] = rs.getString("apellidos");
                dts[3] = rs.getString("cedulaIdent");
                dts[4] = rs.getString("telefono");
                dts[5] = rs.getString("nombresPuesto");
                dts[6] = rs.getString("nombreCuadrilla");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
    
    public String insertarConfig(DDetalleCuadrilla dDetalleCuadrilla){
        sSQL = "insert into tbldetallecuadrilla(cuadrillaId, trabajadorId) \n"
                + "value(?,?)";
        try {
            
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setInt(1, dDetalleCuadrilla.getCuadrillaId());
            pst.setInt(2, dDetalleCuadrilla.getTrabajadorId());
            
            pst.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Insertados");
            
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LConfiguracionCuadrilla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    } 
     
    public int traerIdDetalle(int ids) {
        int id = 0;
        sSQL = "select idDetalleCuadrilla from tbldetallecuadrilla where cuadrillaId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("idDetalleCuadrilla");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public int traerIdDetalleconTrabajador(int ids) {
        int id = 0;
        sSQL = "select idDetalleCuadrilla from tbldetallecuadrilla where trabajadorId = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("idDetalleCuadrilla");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public String eliminarTrabajador(DDetalleCuadrilla dDetalleCuadrilla){
        sSQL = "delete from tbldetallecuadrilla where idDetalleCuadrilla = ? \n"
                + "order by idDetalleCuadrilla";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setInt(1, dDetalleCuadrilla.getIdDetalleCuadrilla());
            
            pst.executeUpdate();

            System.out.println("Datos Eliminados");

        } catch (Exception e) {
            System.out.println("Datos no Eliminados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}

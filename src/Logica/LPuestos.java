/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DEstadoPuesto;
import Datos.DPuestos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josug
 */
public class LPuestos {

    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;

    public DefaultTableModel mostrarPuestos(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"Id", "Nombre", "Descripción"};
        String dts[] = new String[3];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idPuestoTrabajo, nombrePuesto, Descripcion, estado from tblpuestotrabajo \n"
                + "where idPuestoTrabajo = '" + buscar + "' or nombrePuesto like '%" + buscar + "%' && estado = 'Activo'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idPuestoTrabajo");
                dts[1] = rs.getString("nombrePuesto");
                dts[2] = rs.getString("Descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarPuestosInactivos(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"Id", "Nombre", "Descripción"};
        String dts[] = new String[3];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idPuestoTrabajo, nombrePuesto, Descripcion, estado from tblpuestotrabajo \n"
                + "where idPuestoTrabajo = '" + buscar + "' or nombrePuesto like '%" + buscar + "%' && estado = 'Inactivo'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idPuestoTrabajo");
                dts[1] = rs.getString("nombrePuesto");
                dts[2] = rs.getString("Descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public String insertarPuestos(DPuestos dPuestos) {
        sSQL = "insert into tblpuestotrabajo(nombrePuesto, Descripcion, estado) value(?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dPuestos.getNombrePuesto());
            pst.setString(2, dPuestos.getDescripcion());
            pst.setString(3, "Activo");

            pst.executeUpdate();

            System.out.println("Datos Insertados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Datos no Insertados");
        }
        return null;
    }
    
    public String editarPuestos(DPuestos dPuestos) {
        sSQL = "update tblpuestotrabajo set nombrePuesto = ?, Descripcion = ? \n"
                + "where idPuestoTrabajo = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dPuestos.getNombrePuesto());
            pst.setString(2, dPuestos.getDescripcion());
            pst.setInt(3, dPuestos.getIdPuestoTrabajo());

            pst.executeUpdate();

            System.out.println("Datos Actualizados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Datos no Actualizados");
        }
        return null;
    }
    
    public String eliminarPuesto(DPuestos dPuestos){
        sSQL = "update tblpuestotrabajo set estado = ? \n"
                + "where idPuestoTrabajo = ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Inactivo");
            pst.setInt(2, dPuestos.getIdPuestoTrabajo());
            
            pst.executeUpdate();
            
            System.out.println("Datos Eliminados");
        } catch (Exception e) {
            System.out.println("Datos no Eliminados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String editarPuesto(DPuestos dPuestos){
        sSQL = "update tblpuestotrabajo set estado = ? \n"
                + "where idPuestoTrabajo = ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Activo");
            pst.setInt(2, dPuestos.getIdPuestoTrabajo());
            
            pst.executeUpdate();
            
            System.out.println("Datos Activados");
        } catch (Exception e) {
            System.out.println("Datos no Activados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public ResultSet buscarRepetido(String buscar){
        sSQL = "select nombrePuesto from tblpuestotrabajo where nombrePuesto = '" + buscar + "' && estado = 'Activo' order by idPuestoTrabajo";
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

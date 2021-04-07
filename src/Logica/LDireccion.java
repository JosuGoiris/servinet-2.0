/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DDireccion;
import Datos.DEstadoDireccion;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josug
 */
public class LDireccion {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    
    public DefaultTableModel mostrarDireccion(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Dirección", "Barrio", "Descripción"};
        String dts [] = new String[4];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select d.idDireccion, d.nombreDireccion, z.idZona, z.nombreZona, d.descripcion, d.estado \n"
             + "from tbldireccion as d inner join tblzona as z on d.idZona = z.idZona \n"
             + "where d.idDireccion = '" + buscar + "' or d.nombreDireccion like '%" + buscar + "%' && d.estado = 'Activo' order by d.idDireccion";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idDireccion");
                dts[1] = rs.getString("nombreDireccion");
                dts[2] = rs.getString("nombreZona");
                dts[3] = rs.getString("descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarDireccionInactiva(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Dirección", "Barrio", "Descripción"};
        String dts [] = new String[4];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select d.idDireccion, d.nombreDireccion, z.idZona, z.nombreZona, d.descripcion, d.estado \n"
             + "from tbldireccion as d inner join tblzona as z on d.idZona = z.idZona \n"
             + "where d.idDireccion = '" + buscar + "' or d.nombreDireccion like '%" + buscar + "%' && d.estado = 'Inactivo' order by d.idDireccion";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idDireccion");
                dts[1] = rs.getString("nombreDireccion");
                dts[2] = rs.getString("nombreZona");
                dts[3] = rs.getString("descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarDireccion(DDireccion dir){
        sSQL = "insert into tbldireccion(nombreDireccion, descripcion, idZona, estado) value(?,?,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dir.getNombreDireccion());
            pst.setString(2, dir.getDescripcion());
            pst.setInt(3, dir.getZonaId());
            pst.setString(4, "Activo");
            
            pst.executeUpdate();
            
            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String editarDireccion(DDireccion dir){
        sSQL = "update tbldireccion set nombreDireccion = ?, descripcion = ?, idZona = ? \n"
                + "where idDireccion = ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dir.getNombreDireccion());
            pst.setString(2, dir.getDescripcion());
            pst.setInt(3, dir.getZonaId());
            pst.setInt(4, dir.getIdDireccion());
            
            pst.executeUpdate();
            
            System.out.println("Datos Actualizados");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String eliminarDireccion(DDireccion dir){
        sSQL = "update tbldireccion set estado = ? \n"
                + "where idDireccion = ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Inactivo");
            pst.setInt(2, dir.getIdDireccion());
            
            pst.executeUpdate();
            
            System.out.println("Datos Eliminados");
        } catch (Exception e) {
            System.out.println("Datos no Eliminados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public String activarDireccion(DDireccion dir){
        sSQL = "update tbldireccion set estado = ? \n"
                + "where idDireccion = ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, "Activo");
            pst.setInt(2, dir.getIdDireccion());
            
            pst.executeUpdate();
            
            System.out.println("Datos Eliminados");
        } catch (Exception e) {
            System.out.println("Datos no Eliminados");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public int traerIdZona(int ids) {
        int id = 0;
        sSQL = "select idZona from tbldireccion where idDireccion = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("idZona");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public ResultSet buscarRepetido(String buscar){
        sSQL = "select idDireccion, nombreDireccion from tbldireccion \n"
                + "where nombreDireccion = '" + buscar + "' && estado = 'Activo' order by idDireccion";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DZonas;
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
public class LZonas {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarZonas(String buscar){
        DefaultTableModel miModelo = null;
    
        String titulos [] = {"Id", "Barrio", "Descripción", "Estado"};
        String dts [] = new String[4];
    
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select z.idZona, z.nombreZona, z.descripcion, e.estado \n"
            + "from tblzona as z inner join tblestadozona as e on z.estadozonaId = e.idEstadoZona \n"
            + "where z.idZona = '" + buscar + "' or z.nombreZona like '%" + buscar + "%' order by z.idZona";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idZona");
                dts[1] = rs.getString("nombreZona");
                dts[2] = rs.getString("descripcion");
                dts[3] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarZona(DZonas dZonas){
        String msg = null;
        sSQL = "insert into tblzona(nombreZona, descripcion, estadozonaId) value(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dZonas.getNombreZona());
            pst.setString(2, dZonas.getDescripcion());
            pst.setInt(3, dZonas.getEstadozonaId());
            
            int n = pst.executeUpdate();
            if(n != 0){
                msg = "si";
                return msg;
            }else{
                msg = "no";
                return msg;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            msg = "no";
        }
        return msg;
    }
}

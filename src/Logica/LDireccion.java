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
    Connection cn = LConnection.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    
    public DefaultTableModel mostrarDireccion(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Dirección", "Descripción", "Estado"};
        String dts [] = new String[4];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select d.idDireccion, d.nombreDireccion, d.descripcion, e.estado from tbldireccion as d inner join tblestadodireccion as e on d.estadodireccionId = e.idEstadoDireccion where d.idDireccion = '" + buscar + "' or d.nombreDireccion like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idDireccion");
                dts[1] = rs.getString("nombreDireccion");
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
    
    public String insertarDireccion(DDireccion dir, DEstadoDireccion est){
        String msg = null;
        sSQL = "insert into tblestadodireccion(estado) value(?)";
        sSQL1 = "insert into tbldireccion(nombreDireccion, descripcion, estadodireccionId) value(?,?, (select idEstadoDireccion from tblestadodireccion order by idEstadoDireccion desc limit 1))";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            
            pst.setString(1, est.getEstado());
            pst1.setString(1, dir.getNombreDireccion());
            pst1.setString(2, dir.getDescripcion());
            
            int n = pst.executeUpdate();
            if(n != 0){
                int n2 = pst1.executeUpdate();
                msg = "si";
                return msg;
            }else{
                msg = "no";
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "no";
        }
        return msg;
    }
}

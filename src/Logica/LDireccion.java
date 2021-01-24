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
        
        String titulos [] = {"Id", "Dirección", "Id", "Barrio", "Descripción", "Estado"};
        String dts [] = new String[6];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select d.idDireccion, d.nombreDireccion, z.idZona, z.nombreZona, d.descripcion, e.estado \n"
                + "from tbldireccion as d inner join tblzona as z on d.idZona = z.idZona \n"
                + "tblestadodireccion as e on d.estadodireccionId = e.idEstadoDireccion where d.idDireccion = '" + buscar + "' or d.nombreDireccion like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idDireccion");
                dts[1] = rs.getString("nombreDireccion");
                dts[2] = rs.getString("idZona");
                dts[3] = rs.getString("nombreZona");
                dts[4] = rs.getString("descripcion");
                dts[5] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarDireccion(DDireccion dir){
        String msg = null;
        sSQL = "insert into tbldireccion(nombreDireccion, descripcion, idZona, estadodireccionId) value(?,?,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dir.getNombreDireccion());
            pst.setString(2, dir.getDescripcion());
            pst.setInt(3, dir.getZonaId());
            pst.setInt(4, dir.getEstadodireccionId());
            
            int n = pst.executeUpdate();
            if(n != 0){
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

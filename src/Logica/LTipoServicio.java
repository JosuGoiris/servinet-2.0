/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DTipoServicio;
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
public class LTipoServicio {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarTipoServicio(String buscar) {
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre del Servicio", "Estado"};
        String dts [];
        dts = new String[3];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ts.idTipoServicio, ts.nombre, e.estado from tbltiposervicio \n"
                + "as ts inner join tblestadotiposervicio as e on ts.estadotiposerId = e.idEstadoTipoSer \n"
                + "where ts.idTipoServicio = '" + buscar + "' or ts.nombre like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTipoServicio");
                dts[1] = rs.getString("nombre");
                dts[2] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarTipos(DTipoServicio dTipo){
        String msg = null;
        sSQL = "insert into tbltiposervicio(nombre, estadotiposerId) value(?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dTipo.getNombre());
            pst.setInt(2, dTipo.getEstadotiposerId());
            
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
            return msg;
        }
    }
}

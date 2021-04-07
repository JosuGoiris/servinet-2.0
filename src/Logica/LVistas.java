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
 * @author jotz
 */
public class LVistas {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarVelocidades(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Velocidad", "Precio", "Descripcion"};
        String dts [] = new String[4];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select idDetalleServicio, conexion, precio, descripcion from \n"
                + "tbldetalleservicio where idDetalleServicio = '" + buscar + "' \n"
                + "or conexion like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idDetalleServicio");
                dts[1] = rs.getString("conexion");
                dts[2] = rs.getString("precio");
                dts[3] = rs.getString("descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String actualizarVelocidad(DServiciodelCliente dServiciodelCliente){
        sSQL = "update tblserviciodelcliente set detalleservicioId = ? where idServiciodelCliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dServiciodelCliente.getDetalleservicioId());
            pst.setInt(2, dServiciodelCliente.getIdServiciodelCliente());
            pst.executeUpdate();
            System.out.println("Velocidad Actualizada");
        } catch (Exception e) {
            System.out.println("Hubo un error");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DFormadePago;
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
public class LFormadePago {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarFormas(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Forma de Pago", "Descripción", "Estado"};
        String dts [] = new String[4];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select f.idFormaPago, f.nombreForma, f.descripcion, e.estado from tblformadepago \n"
             + "as f inner join tblestadoforma as e on f.estadoformaId = e.idEstadoForma \n"
             + "where f.idFormaPago = '" + buscar + "' or f.nombreForma like '%" + buscar + "%' order by f.idFormaPago";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idFormaPago");
                dts[1] = rs.getString("nombreForma");
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
    
    public String insertarFormas(DFormadePago dForma){
        String msg = null;
        sSQL = "insert into tblformadepago(nombreForma, descripcion, estadoformaId) value(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dForma.getNombreForma());
            pst.setString(2, dForma.getDescripcion());
            pst.setString(3, "Activo");
            int n = pst.executeUpdate();
            if(n != 0){
                msg = "si";
                return msg;
            }else{
                msg= "no";
            }
            return msg;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return msg;
        }
    }
}

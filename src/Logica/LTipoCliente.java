/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DTipoCliente;
import Datos.DTipoUsuario;
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
public class LTipoCliente {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    
    public DefaultTableModel mostrarTipos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id","Nombre del Tipo", "Estado"};
        String dts [] = new String[3];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select tc.idTipoCliente, tc.tipoCliente, e.estado from tbltipocliente \n"
                + "as tc inner join tblestadotipocliente as e on tc.estadotipoclienteId = e.idEstadoTipoCliente \n"
                + "where tc.idTipoCliente = '" + buscar + "' or tc.tipoCliente like '%" + buscar + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTipoCliente");
                dts[1] = rs.getString("tipoCliente");
                dts[2] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarTipos(DTipoCliente dTipo){
        String msg = null;
        sSQL = "insert into tbltipocliente(tipoCliente, estadotipoclienteId) value(?, ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dTipo.getTipoCliente());
            pst.setInt(2, dTipo.getEstadotipoclienteId());
            
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

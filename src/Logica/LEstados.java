/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author josug
 */
public class LEstados {
    Connection cn = ConexionSingleton.getConnection();
    public String sSQL = null;
    
    public ResultSet consulta(String sql){
        ResultSet rs = null;
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            rs = st.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
    public DefaultComboBoxModel llenarComboFormadePago(){
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        listaModelo.addElement("Seleccione una forma de pago");
        ResultSet rs = this.consulta("select * from tblformadepago order by idFormaPago");
        try {
            while(rs.next()){
                listaModelo.addElement(rs.getString("nombreForma"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaModelo;
    }
}

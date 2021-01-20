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
    
    public DefaultComboBoxModel llenarComboPersona(){
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        listaModelo.addElement("Seleccione un Estado");
        ResultSet rs = this.consulta("select * from tblestadopersona order by idEstadoPersona");
        try {
            while(rs.next()){
                listaModelo.addElement(rs.getString("estado"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaModelo;
    }
    
    public DefaultComboBoxModel llenarComboPuestos(){
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        listaModelo.addElement("Seleccione un Estado");
        ResultSet rs = this.consulta("select * from tblestadopuesto order by estado");
        try {
            while(rs.next()){
                listaModelo.addElement(rs.getString("estado"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaModelo;
    }
    
    public DefaultComboBoxModel llenarComboServicio(){
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        listaModelo.addElement("Seleccione un Estado");
        ResultSet rs = this.consulta("select * from tblestadoservicio order by estado");
        try {
            while(rs.next()){
                listaModelo.addElement(rs.getString("estado"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaModelo;
    }
    
    public DefaultComboBoxModel llenarComboTipoUsuario(){
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        listaModelo.addElement("Seleccione un Estado");
        ResultSet rs = this.consulta("select * from tblestadotipousuario order by estado");
        try {
            while(rs.next()){
                listaModelo.addElement(rs.getString("estado"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaModelo;
    }
    
    public DefaultComboBoxModel llenarComboEstadoVelocidad(){
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        listaModelo.addElement("Seleccione un Estado");
        ResultSet rs = this.consulta("select * from tblestadovelocidad order by estado");
        try {
            while(rs.next()){
                listaModelo.addElement(rs.getString("estado"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaModelo;
    }
}

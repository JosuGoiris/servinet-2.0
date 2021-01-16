/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DEstadoTipoU;
import Datos.DPersona;
import Datos.DTipoUsuario;
import java.sql.CallableStatement;
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
public class LTipoUsuario {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    
    public DefaultTableModel mostrarTipos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id","Nombre del Tipo", "Estado"};
        String dts [] = new String[3];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select tu.idTipoUsuario, tu.nombre, etu.estado from tbltipousuario as tu inner join tblestadotipousuario as etu on tu.estadotipoId = etu.idEstadoTipoU where tu.idTipoUsuario = '" + buscar + "' or tu.nombre like '%" + buscar + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idTipoUsuario");
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
    
    public String insertarTipos(DTipoUsuario dTipo, DEstadoTipoU dEstado){
        String msg = null;
        sSQL = "insert into tblestadotipousuario(estado) value(?)";
        sSQL1 = "insert into tbltipousuario(nombre, estadotipoId) value(?, (select idEstadoTipoU from tblestadotipousuario order by idEstadoTipoU desc limit 1))";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            
            pst.setString(1, dEstado.getEstado());
            
            pst1.setString(1, dTipo.getNombre());
            
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
            JOptionPane.showMessageDialog(null, e);
            return msg;
        }
    }
}

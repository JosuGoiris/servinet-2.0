/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DEstadoPuesto;
import Datos.DPuestos;
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
public class LPuestos {
    Connection cn = LConnection.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    
    public DefaultTableModel mostrarPuestos(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Estado"};
        String dts [] = new String[3];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select pt.idPuestoTrabajo, pt.nombrePuesto, e.estado from tblpuestotrabajo as pt inner join tblestadopuesto as e on pt.puestoestadoId = e.idEstadoPuesto where pt.idPuestoTrabajo = '" + buscar + "' or pt.nombrePuesto like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idPuestoTrabajo");
                dts[1] = rs.getString("nombrePuesto");
                dts[2] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarPuestos(DPuestos dPuestos, DEstadoPuesto dEstadoPuesto){
        String msg = null;
        sSQL = "insert into tblestadopuesto(estado) value(?)";
        sSQL1 = "insert into tblpuestotrabajo(nombrePuesto, estadopuestoId) value(?, (select idEstadoPuesto from tblestadopuesto order by idEstadoPuesto desc limit 1))";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            
            pst.setString(1, dEstadoPuesto.getEstado());
            
            pst1.setString(1, dPuestos.getNombrePuesto());
            
            int n = pst.executeUpdate();
            if(n != 0){
                int n2 = pst1.executeUpdate();
                msg = "si";
                return msg;
            }else{
                msg = "no";
            }
            return msg;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return msg;
        }
    }
}

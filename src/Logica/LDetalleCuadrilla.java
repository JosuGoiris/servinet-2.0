/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DDetalleCuadrilla;
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
public class LDetalleCuadrilla {
    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    
    public DefaultTableModel mostrarDetalles(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"IdDetalle", "Id", "Nombre", "Apellido", "id", "Cuadrilla"};
        String dts [] = new String[6];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select d.idDetalleCuadrilla, d.trabajadorId, p.nombres, p.apellidos, d.cuadrillaId, c.nombreCuadrilla \n"
                + "from tbldetallecuadrilla as d inner join tbltrabajador as t on d.trabajadorId = t.idTrabajador inner join \n"
                + "tblpersona as p on t.personaId = p.idPersona inner join tblcuadrilla as c on d.cuadrillaId = c.idCuadrilla \n"
                + "where d.idDetalleCuadrilla = '" + buscar + "' or c.nombreCuadrilla like '%" + buscar + "%' order by d.idDetalleCuadrilla";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idDetalleCuadrilla");
                dts[1] = rs.getString("trabajadorId");
                dts[2] = rs.getString("nombres");
                dts[3] = rs.getString("apellidos");
                dts[4] = rs.getString("cuadrillaId");
                dts[5] = rs.getString("nombreCuadrilla");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarDetalle(DDetalleCuadrilla dDetalle){
        String msg = null;
        sSQL = "insert into tbldetallecuadrilla(cuadrillaId, trabajadorId) value(?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setInt(1, dDetalle.getCuadrillaId());
            pst.setInt(2, dDetalle.getTrabajadorId());
            
            int n = pst.executeUpdate();
            if(n != 0){
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DEstadoServicio;
import Datos.DServicios;
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
public class LServicios {
    Connection cn = LConnection.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    
    public DefaultTableModel mostrarServicios(String buscar){
        DefaultTableModel miModelo = null;
        
        String titulos [] = {"Id", "Nombre", "Id", "Velocidad", "Precio", "Descripción", "Id", "Tipo", "Estado"};
        String dts [] = new String[7];
        
        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select s.idServicio, s.nombreServicio, v.idVelocidadCon, v.velocidad, s.precio, s.descripcion, t.idTipoServicio, t.nombre, \n"
                + "e.estado from tblservicio as s inner join tbltiposervicio as t on s.tiposervicioId = t.idTipoServicio \n"
                + "inner join tblestadoservicio as e on s.estadoservicioId = e.idEstadoServicio \n" 
                + "inner join tblvelocidadcon as v on s.velocidadconId = v.idVelocidadCon \n" 
                + "where s.idServicio = '" + buscar + "' \n"
                + "or s.nombreServicio like '%" + buscar + "%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()){
                dts[0] = rs.getString("idServicio");
                dts[1] = rs.getString("nombreServicio");
                dts[2] = rs.getString("velocidadCon");
                dts[3] = rs.getString("precio");
                dts[4] = rs.getString("descripcion");
                dts[5] = rs.getString("nombre");
                dts[6] = rs.getString("estado");
                miModelo.addRow(dts);
            } 
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarServicios(DServicios misDServicios, DTipoServicio misDTipoServicio, DEstadoServicio misDEstadoServicio){
        String msg = null;
        sSQL = "insert into tbltiposervicio(nombre) value(?)";
        sSQL1 = "insert into tblestadoservicio(estado) value(?)";
        sSQL2 = "insert into tblservicio(nombreServicio, velocidadCon, precio, descripcion, estadoservicioId, tiposervicioId) value(?,?,?,?, (select idEstadoServicio from tblestadoservicio order by idEstadoServicio desc limit 1), (select idTipoServicio from tbltiposervicio order by idTipoServicio desc limit 1))";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, misDTipoServicio.getNombre());
            pst1.setString(1, misDEstadoServicio.getEstado());
            pst2.setString(1, misDServicios.getNombreServicio());
            pst2.setString(2, misDServicios.getVelocidadCon());
            pst2.setString(3, misDServicios.getPrecio());
            pst2.setString(4, misDServicios.getDescripcio());
            
            int n = pst.executeUpdate();
            if(n != 0){
                int n2 = pst1.executeUpdate();
                if (n2 != 0){
                    int n3 = pst2.executeUpdate();
                    msg = "si";
                    return msg;
                }else {
                    msg = "no";
                    return msg;
                }
            }else {
                msg = "no";
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "no";
        }
        return msg;
    }
    
    public String editarServicio(DServicios misDServicios, DTipoServicio misDTipoServicio, DEstadoServicio misDEstadoServicio){
        String msg = null;
        sSQL = "update tbltiposervicio set nombre = ? where idTipoServicio = ?";
        sSQL1 = "update tblestadoservicio set estado = ? where idEstadoServicio = ?";
        sSQL2 = "update tblservicio set nombreServicio = ?, velocidadCon = ?, precio = ?, descripcion = ? where idServicio = ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, misDTipoServicio.getNombre());
            pst.setInt(2, misDTipoServicio.getIdTipoServicio());
            
            pst1.setString(1, misDEstadoServicio.getEstado());
            pst1.setInt(2, misDEstadoServicio.getIdestadoServicio());
            
            pst2.setString(1, misDServicios.getNombreServicio());
            pst2.setString(2, misDServicios.getVelocidadCon());
            pst2.setString(3, misDServicios.getPrecio());
            pst2.setString(4, misDServicios.getDescripcio());
            pst2.setInt(5, misDServicios.getIdServicio());
            
            pst.executeUpdate();
            pst1.executeUpdate();
            pst2.executeUpdate();
            msg = "si";
        } catch (Exception e) {
            msg = "no";
        }
        return msg;
    }
    
    public String eliminarServicios(DServicios misDServicios, DTipoServicio misDTipoServicio, DEstadoServicio misDEstadoServicio){
        String msg = null;
        sSQL = "delete from tblservicio where idServicio = ?";
        sSQL1 = "delete from tbltiposervicio where idTipoServicio = ?";
        sSQL2 = "delete from tblestadoservicio where idEstadoServicio = ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setInt(1, misDServicios.getIdServicio());
            pst1.setInt(1, misDTipoServicio.getIdTipoServicio());
            pst2.setInt(1, misDEstadoServicio.getIdestadoServicio());
            
            
            int n = pst.executeUpdate();
            if(n != 0){
                int n2 = pst1.executeUpdate();
                if (n2 != 0){
                    int n3 = pst2.executeUpdate();
                    msg = "si";
                    return msg;
                }else {
                    msg = "no";
                    return msg;
                }
            }else {
                msg = "no";
                return msg;
            }
        } catch (Exception e) {
            msg = "no";
        }
        return msg;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DPersona;
import Datos.DTipoUsuario;
import Datos.DUsuarios;
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
public class LUsuarios {
    Connection cn = LConnection.getConnection();
    private String sSQL  = null;
    private String sSQL1 = null;
    private String sSQL2 = null;
    
    public DefaultTableModel mostrarUsuarios(String buscar) {
        DefaultTableModel miModelo = null;
        
            String titulos [] = {"Id", "Usuario", "Clave", "Nombre", "Apellido", "Cédula", "Dirección", "Telefono", "Tipo", "Estado"};
            String dts [] = new String[10];
            
            miModelo = new DefaultTableModel(null, titulos);
            sSQL = "select p.idPersona, u.loginUsuario, u.passwordUsuario, p.nombres, p.apellidos, p.cedulaIdent, \n"
                    + "d.nombreDireccion, p.telefono, t.nombre, e.estado from tblpersona as p inner join tblusuario as u on p.idPersona = u.personaId inner join tbldireccion as d on p.direccionId = d.idDireccion inner join tbltipousuario as t on u.tipoUsuarioId = t.idTipoUsuario inner join tblestadopersona as e on p.estadopersonaId = e.idEstadoPersona \n"
                    + "where p.idPersona = '" + buscar + "' or p.nombres like '%" + buscar + "%'\n"
                    + "or p.apellidos like '%" + buscar + "%' or p.cedulaIdent like '%" + buscar + "%' order by p.idPersona";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL);
                while(rs.next()){
                    dts[0] = rs.getString("idPersona");
                    dts[1] = rs.getString("loginUsuario");
                    dts[2] = rs.getString("passwordUsuario");
                    dts[3] = rs.getString("nombres");
                    dts[4] = rs.getString("apellidos");
                    dts[5] = rs.getString("cedulaIdent");
                    dts[6] = rs.getString("nombreDireccion");
                    dts[7] = rs.getString("telefono");
                    dts[8] = rs.getString("nombre");
                    dts[9] = rs.getString("estado");
                    miModelo.addRow(dts);
                }
                return miModelo;
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String insertarUsuarios(DUsuarios misDUsuarios, DPersona misDPersona, DTipoUsuario misDTipoUsuario) {
        String msg = null;
        sSQL = "insert into tblpersona(nombres, apellidos, cedulaIdent, telefono, direccionId, estadopersonaId) value(?,?,?,?, \n"
                + "(select idDireccion from tbldireccion order by idDireccion desc limit 1) \n"
                + "(select idEstadoPersona from tblestadopersona order by idEstadoPersona desc limit 1))";
        sSQL1 = "insert into tbltipousuario(nombre, estadotipoId) value(?, (select idEstadoTipoU from tblestadotipousuario order by idEstadoTipoU desc limit 1))";
        sSQL2 = "insert into tblusuario(loginUsuario, passwordUsuario, personaId, tipoUsuarioId) "
                + "value(?,?, (select idPersona from tblPersona order by idPersona desc limit 1), "
                + "(select idTipoUsuario from tbltipousuario order by idTipoUsuario desc limit 1))";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            //CallableStatement cst = cn.prepareCall("{ call sp_insertar_usuariopersona(?,?,?,?,?,?,?) }");
            pst.setString(1, misDPersona.getNombre());
            pst.setString(2, misDPersona.getApellido());
            pst.setString(3, misDPersona.getCedulaIdent());
            pst.setString(4, misDPersona.getTelefono());
            
            pst1.setString(1, misDTipoUsuario.getNombre());
            pst2.setString(1, misDUsuarios.getLoginUsuario());
            pst2.setString(2, misDUsuarios.getPasswordUsuario());
            
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
           
        } catch (Exception ex) {
            ex.printStackTrace();
            msg = "no";
        }
        return msg;
    }
    
    public String editarUsuarios(DUsuarios misDUsuarios, DPersona misDPersona, DTipoUsuario misDTipoUsuario) {
        String msg = null;
        sSQL = "update tblpersona set nombres = ?, apellidos = ?, cedulaIdent = ?,\n"
                + "telefono = ?, direccionId = ? where idPersona = ?";
        sSQL1 = "update tbltipousuario set nombre = ? where idtipoUsuario = ?";
        sSQL2 = "update tblusuario set loginUsuario = ?, passwordUsuario = ? where idUsuario = ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setString(1, misDPersona.getNombre());
            pst.setString(2, misDPersona.getApellido());
            pst.setString(3, misDPersona.getCedulaIdent());
            pst.setString(4, misDPersona.getTelefono());
            pst.setInt(5, misDPersona.getDireccionId());
            pst.setInt(6, misDPersona.getIdPersona());
            
            pst1.setString(1, misDTipoUsuario.getNombre());
            pst1.setInt(2, misDTipoUsuario.getIdTipoUsuario());
            
            pst2.setString(1, misDUsuarios.getLoginUsuario());
            pst2.setString(2, misDUsuarios.getPasswordUsuario());
            pst2.setInt(3, misDUsuarios.getIdUsuario());
            
            pst.executeUpdate();
            pst1.executeUpdate();
            pst2.executeUpdate();
            msg = "si";
        } catch (Exception e) {
            msg = "no";
        }
        return msg;
    }
    
    public String eliminarUsuarios(DUsuarios miDUsuarios, DPersona misDPersona, DTipoUsuario misDTipoUsuario){
            String msg = null;
            sSQL = "delete from tblusuario where idUsuario = ?";
            sSQL2 = "delete from tblpersona where idPersona = ?";
            try {
                PreparedStatement pst = cn.prepareStatement(sSQL);
                PreparedStatement pst1 = cn.prepareStatement(sSQL1);
                PreparedStatement pst2 = cn.prepareStatement(sSQL2);
                
                pst.setInt(1, miDUsuarios.getIdUsuario());
                pst2.setInt(1, misDPersona.getIdPersona());
                
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

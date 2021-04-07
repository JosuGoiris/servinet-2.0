/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DPersona;
import Datos.DUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josug
 */
public class LUsuarios {

    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;

    public DefaultTableModel mostrarUsuarios(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"Id", "Usuario", "Clave", "Nombre", "Apellido", "Cédula", "Dirección", "Telefono", "Tipo", "Estado"};
        String dts[] = new String[10];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select p.idPersona, u.loginUsuario, u.passwordUsuario, p.nombres, p.apellidos, p.cedulaIdent, \n"
                + "d.idDireccion, d.nombreDireccion, p.telefono, t.idTipoUsuario, t.tipoUsuario, p.estado from tblpersona \n"
                + "as p inner join tblusuario as u on p.idPersona = u.personaId inner join tbldireccion as d on \n"
                + "p.direccionId = d.idDireccion inner join tbltipousuario as t on u.tipoUsuarioId = t.idTipoUsuario \n"
                + "where p.idPersona = '" + buscar + "' or p.cedulaIdent like '%" + buscar + "%' \n"
                + "&& p.estado = 'Activo'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idPersona");
                dts[1] = rs.getString("loginUsuario");
                dts[2] = rs.getString("passwordUsuario");
                dts[3] = rs.getString("nombres");
                dts[4] = rs.getString("apellidos");
                dts[5] = rs.getString("cedulaIdent");
                dts[6] = rs.getString("nombreDireccion");
                dts[7] = rs.getString("telefono");
                dts[8] = rs.getString("tipoUsuario");
                dts[9] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
    
    public DefaultTableModel mostrarUsuariosInactivos(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"Id", "Usuario", "Clave", "Nombre", "Apellido", "Cédula", "Dirección", "Telefono", "Tipo", "Estado"};
        String dts[] = new String[10];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select p.idPersona, u.loginUsuario, u.passwordUsuario, p.nombres, p.apellidos, p.cedulaIdent, \n"
                + "d.idDireccion, d.nombreDireccion, p.telefono, t.idTipoUsuario, t.tipoUsuario, p.estado from tblpersona \n"
                + "as p inner join tblusuario as u on p.idPersona = u.personaId inner join tbldireccion as d on \n"
                + "p.direccionId = d.idDireccion inner join tbltipousuario as t on u.tipoUsuarioId = t.idTipoUsuario \n"
                + "where p.idPersona = '" + buscar + "' or p.cedulaIdent like '%" + buscar + "%' \n"
                + "&& p.estado = 'Inactivo'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idPersona");
                dts[1] = rs.getString("loginUsuario");
                dts[2] = rs.getString("passwordUsuario");
                dts[3] = rs.getString("nombres");
                dts[4] = rs.getString("apellidos");
                dts[5] = rs.getString("cedulaIdent");
                dts[6] = rs.getString("nombreDireccion");
                dts[7] = rs.getString("telefono");
                dts[8] = rs.getString("tipoUsuario");
                dts[9] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarUsuariosMini(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"Id", "Nombre Usuario", "Tipo", "Estado"};
        String dts[] = new String[4];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select p.idPersona, concat(p.nombres,' ',p.apellidos) as usuario, \n"
                + "t.idTipoUsuario, t.tipoUsuario, p.estado from tblpersona \n"
                + "as p inner join tblusuario as u on p.idPersona = u.personaId \n"
                + "inner join tbltipousuario as t on u.tipoUsuarioId = t.idTipoUsuario \n"
                + "where p.idPersona = '" + buscar + "' or p.nombres like '%" + buscar + "%'\n"
                + "or p.apellidos like '%" + buscar + "%' order by p.idPersona";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idPersona");
                dts[1] = rs.getString("usuario");
                dts[2] = rs.getString("tipoUsuario");
                dts[3] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public String insertarUsuarios(DUsuarios misDUsuarios, DPersona misDPersona) {
        String msg = null;
        sSQL = "insert into tblpersona(nombres, apellidos, cedulaIdent, telefono, direccionId, estado) value(?,?,?,?,?,?)";
        sSQL1 = "insert into tblusuario(idUsuario, loginUsuario, passwordUsuario, personaId, tipoUsuarioId) \n"
                + "value((select idPersona from tblpersona order by idPersona desc limit 1),?,?, (select idPersona \n"
                + "from tblpersona order by idPersona desc limit 1),?)";
        try {
            cn.setAutoCommit(false);

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);

            pst.setString(1, misDPersona.getNombre());
            pst.setString(2, misDPersona.getApellido());
            pst.setString(3, misDPersona.getCedulaIdent());
            pst.setString(4, misDPersona.getTelefono());
            pst.setInt(5, misDPersona.getDireccionId());
            pst.setString(6, "Activo");

            pst.executeUpdate();

            pst1.setString(1, misDUsuarios.getLoginUsuario());
            pst1.setString(2, misDUsuarios.getPasswordUsuario());
            pst1.setInt(3, misDUsuarios.getTipoUsuarioId());

            pst1.executeUpdate();

            cn.commit();

            System.out.println("Datos Insertados");

        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return msg;
    }

    public String editarUsuarios(DUsuarios dUsuarios, DPersona dPersona) {
        sSQL = "update tblpersona set nombres = ?, apellidos = ?, cedulaIdent = ?,\n"
                + "telefono = ?, direccionId = ? where idPersona = ?";
        sSQL1 = "update tblusuario set loginUsuario = ?, passwordUsuario = ?, tipousuarioId = ? where idUsuario = ?";

        try {
            cn.setAutoCommit(false);
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            
            
            pst.setString(1, dPersona.getNombre());
            pst.setString(2, dPersona.getApellido());
            pst.setString(3, dPersona.getCedulaIdent());
            pst.setString(4, dPersona.getTelefono());
            pst.setInt(5, dPersona.getDireccionId());
            pst.setInt(6, dPersona.getIdPersona());

            pst.executeUpdate();
            
            pst1.setString(1, dUsuarios.getLoginUsuario());
            pst1.setString(2, dUsuarios.getPasswordUsuario());
            pst1.setInt(3, dUsuarios.getTipoUsuarioId());
            pst1.setInt(4, dUsuarios.getIdUsuario());

            pst1.executeUpdate();
            
            cn.commit();
            
            System.out.println("Datos Actualizados");

        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String eliminarUsuarios(DPersona misDPersona) {
        String msg = null;
        sSQL = "update tblpersona set estado = ? where idPersona = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, "Inactivo");
            pst.setInt(2, misDPersona.getIdPersona());

            pst.executeUpdate();

            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String activarUsuarios(DPersona misDPersona) {
        String msg = null;
        sSQL = "update tblpersona set estado = ? where idPersona = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, "Activo");
            pst.setInt(2, misDPersona.getIdPersona());

            pst.executeUpdate();

            System.out.println("Datos Insertados");
        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int traerIdDireccion(int ids) {
        int id = 0;
        sSQL = "select direccionId from tblpersona where idPersona = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("direccionId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }

    public int traerIdTipo(int ids) {
        int id = 0;
        sSQL = "select tipousuarioId from tblusuario where idUsuario = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("tipousuarioId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public ResultSet buscarUsuarioRepetido(String buscar){
        sSQL = "select loginUsuario from tblusuario where loginUsuario = '" + buscar + "' order by idUsuario";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public ResultSet buscarPersonaRepetido(String buscar){
        sSQL = "select cedulaIdent from tblpersona where cedulaIdent = '" + buscar + "' && estado = 'Activo' order by idPersona";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}

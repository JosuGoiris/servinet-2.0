/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DDetalleServicio;
import Datos.DServicios;
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
public class LServicios {

    Connection cn = ConexionSingleton.getConnection();
    private String sSQL = null;
    private String sSQL1 = null;
    private String sSQL2 = null;

    public DefaultTableModel mostrarServicios(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"IdServicio", "Nombre", "Velocidad", "Precio", "Descripción", "Barrio", "Estado"};
        String dts[] = new String[7];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ds.idDetalleServicio, s.idServicio, s.nombreServicio, ds.conexion, ds.precio, ds.descripcion, z.idZona, z.nombreZona, \n"
                + "ds.estado from tbldetalleservicio as ds inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "inner join tblzona as z on ds.zonaId = z.idZona \n"
                + "where s.idServicio = '" + buscar + "' \n"
                + "or s.nombreServicio like '%" + buscar + "%' && ds.estado = 'Activo'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idDetalleServicio");
                dts[1] = rs.getString("nombreServicio");
                dts[2] = rs.getString("conexion");
                dts[3] = rs.getString("precio");
                dts[4] = rs.getString("descripcion");
                dts[5] = rs.getString("nombreZona");
                dts[6] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarInactivos(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"IdServicio", "Nombre", "Velocidad", "Precio", "Descripción", "Barrio", "Estado"};
        String dts[] = new String[7];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ds.idDetalleServicio, s.idServicio, s.nombreServicio, ds.conexion, ds.precio, ds.descripcion, z.idZona, z.nombreZona, \n"
                + "ds.estado from tbldetalleservicio as ds inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "inner join tblzona as z on ds.zonaId = z.idZona \n"
                + "where s.idServicio = '" + buscar + "' \n"
                + "or s.nombreServicio like '%" + buscar + "%' && ds.estado = 'Inactivo'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idDetalleServicio");
                dts[1] = rs.getString("nombreServicio");
                dts[2] = rs.getString("conexion");
                dts[3] = rs.getString("precio");
                dts[4] = rs.getString("descripcion");
                dts[5] = rs.getString("nombreZona");
                dts[6] = rs.getString("estado");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarServiciosAgregar(String buscar) {
        DefaultTableModel miModelo = null;

        String titulos[] = {"Id", "Nombre", "Precio", "Velocidad", "Descripción"};
        String dts[] = new String[5];

        miModelo = new DefaultTableModel(null, titulos);
        sSQL = "select ds.idDetalleServicio, s.idServicio, s.nombreServicio, ds.conexion, ds.precio, ds.descripcion, \n"
                + "ds.estado from tbldetalleservicio as ds inner join tblservicio as s on ds.servicioId = s.idServicio \n"
                + "where s.idServicio = '" + buscar + "' \n"
                + "or s.nombreServicio like '%" + buscar + "%' && ds.estado = 'Activo'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                dts[0] = rs.getString("idDetalleServicio");
                dts[1] = rs.getString("nombreServicio");
                dts[2] = rs.getString("precio");
                dts[3] = rs.getString("conexion");
                dts[4] = rs.getString("descripcion");
                miModelo.addRow(dts);
            }
            return miModelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public String insertarServicios(DServicios dServicios, DDetalleServicio dDetalleServicio) {
        sSQL = "insert into tblservicio(nombreServicio) values(?)";
        sSQL1 = "insert into tbldetalleservicio(conexion, precio, descripcion, zonaId, servicioId, estado) \n"
                + "values(?,?,?,?, (select idServicio from tblservicio order by idServicio desc limit 1),?)";
        try {
            cn.setAutoCommit(false);

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);

            pst.setString(1, dServicios.getNombreServicio());

            pst.executeUpdate();

            pst1.setInt(1, dDetalleServicio.getConexion());
            pst1.setDouble(2, dDetalleServicio.getPrecio());
            pst1.setString(3, dDetalleServicio.getDescripcion());
            pst1.setInt(4, dDetalleServicio.getZonaId());
            pst1.setString(5, "ACTIVO");
            
            pst1.executeUpdate();

            cn.commit();

            System.out.println("Datos Insertados");

        } catch (Exception e) {
            System.out.println("Datos no Insertados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String editarServicio(DServicios dServicios, DDetalleServicio dDetalleServicio) {
        sSQL = "update tblservicio set nombreServicio = ? where idServicio = ?";
        sSQL1 = "update tbldetalleservicio set conexion = ?, precio = ?, descripcion = ?, zonaId = ? \n"
                + "where idDetalleServicio = ?";
        try {
            cn.setAutoCommit(false);

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);

            pst.setString(1, dServicios.getNombreServicio());
            pst.setInt(2, dServicios.getIdServicio());
            
            pst.executeUpdate();

            pst1.setInt(1, dDetalleServicio.getConexion());
            pst1.setDouble(2, dDetalleServicio.getPrecio());
            pst1.setString(3, dDetalleServicio.getDescripcion());
            pst1.setInt(4, dDetalleServicio.getZonaId());
            pst1.setInt(5, dDetalleServicio.getIdDetalleServicio());
            
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

    public String eliminarServicios(DDetalleServicio dDetalleServicio) {
        sSQL = "update tbldetalleservicio set estado = ? where idDetalleServicio = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, "Inactivo");
            pst.setInt(2, dDetalleServicio.getIdDetalleServicio());

            pst.executeUpdate();

            System.out.println("Datos Actualizados");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String activarServicio(DDetalleServicio dDetalleServicio) {
        sSQL = "update tbldetalleservicio set estado = ? where idDetalleServicio = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, "Activo");
            pst.setInt(2, dDetalleServicio.getIdDetalleServicio());

            pst.executeUpdate();

            System.out.println("Datos Actualizados");
        } catch (Exception e) {
            System.out.println("Datos no Actualizados");
            JOptionPane.showMessageDialog(null, e);
            try {
                cn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int traerIdZona(int ids) {
        int id = 0;
        sSQL = "select zonaId from tbldetalleservicio where idDetalleServicio = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("zonaId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }

    public int traerIdServicio(int ids) {
        int id = 0;
        sSQL = "select servicioId from tbldetalleservicio where idDetalleServicio = '" + ids + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                id = rs.getInt("servicioId");
            }
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }

    public ResultSet buscarRepetido(String buscar){
        sSQL = "select nombreServicio from tblservicio where nombreServicio = '" + buscar + "' order by idServicio";
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

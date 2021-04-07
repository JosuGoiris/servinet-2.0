/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author josug
 */
public class DDetalleServicio {
    int idDetalleServicio;
    int conexion;
    double precio;
    String descripcion;
    int zonaId;
    String estado;

    public DDetalleServicio() {
    }

    public DDetalleServicio(int idDetalleServicio, int conexion, double precio, String descripcion, int zonaId, String estado) {
        this.idDetalleServicio = idDetalleServicio;
        this.conexion = conexion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.zonaId = zonaId;
        this.estado = estado;
    }

    public int getIdDetalleServicio() {
        return idDetalleServicio;
    }

    public void setIdDetalleServicio(int idDetalleServicio) {
        this.idDetalleServicio = idDetalleServicio;
    }

    public int getConexion() {
        return conexion;
    }

    public void setConexion(int conexion) {
        this.conexion = conexion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getZonaId() {
        return zonaId;
    }

    public void setZonaId(int zonaId) {
        this.zonaId = zonaId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}

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
public class DServicios {
    int idServicio;
    String nombreServicio;
    String estado;
    int detalleservicioId;

    public DServicios() {
    }

    public DServicios(int idServicio, String nombreServicio, String estado, int detalleservicioId) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.estado = estado;
        this.detalleservicioId = detalleservicioId;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getDetalleservicioId() {
        return detalleservicioId;
    }

    public void setDetalleservicioId(int detalleservicioId) {
        this.detalleservicioId = detalleservicioId;
    }

    
}

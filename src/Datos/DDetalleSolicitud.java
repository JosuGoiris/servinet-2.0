/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author hecto
 */
public class DDetalleSolicitud {
    int idDetalleSolicitud;
    int cantidad;

    public DDetalleSolicitud() {
    }

    public DDetalleSolicitud(int idDetalleSolicitud, int cantidad) {
        this.idDetalleSolicitud = idDetalleSolicitud;
        this.cantidad = cantidad;
    }

    public int getIdDetalleSolicitud() {
        return idDetalleSolicitud;
    }

    public void setIdDetalleSolicitud(int idDetalleSolicitud) {
        this.idDetalleSolicitud = idDetalleSolicitud;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}

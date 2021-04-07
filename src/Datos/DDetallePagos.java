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
public class DDetallePagos {
    int idDetallePagos;
    int cantidad;

    public DDetallePagos() {
    }

    public DDetallePagos(int idDetallePagos, int cantidad) {
        this.idDetallePagos = idDetallePagos;
        this.cantidad = cantidad;
    }

    public int getIdDetallePagos() {
        return idDetallePagos;
    }

    public void setIdDetallePagos(int idDetallePagos) {
        this.idDetallePagos = idDetallePagos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}

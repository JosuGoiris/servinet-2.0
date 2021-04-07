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
public class DDetalleMonto {
    int idDetalleMonto;
    double cantidadTotal;
    int montoId;
    int cajaId;

    public DDetalleMonto() {
    }

    public DDetalleMonto(int idDetalleMonto, double cantidadTotal, int montoId, int cajaId) {
        this.idDetalleMonto = idDetalleMonto;
        this.cantidadTotal = cantidadTotal;
        this.montoId = montoId;
        this.cajaId = cajaId;
    }

    public int getIdDetalleMonto() {
        return idDetalleMonto;
    }

    public void setIdDetalleMonto(int idDetalleMonto) {
        this.idDetalleMonto = idDetalleMonto;
    }

    public double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getMontoId() {
        return montoId;
    }

    public void setMontoId(int montoId) {
        this.montoId = montoId;
    }

    public int getCajaId() {
        return cajaId;
    }

    public void setCajaId(int cajaId) {
        this.cajaId = cajaId;
    }
    
       
}

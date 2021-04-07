/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Date;

/**
 *
 * @author hecto
 */
public class DCaja {
    int idCaja;
    Date fechaApertura;
    double montoApertura;
    Date fechaCierre;
    double montoCierre;
    int idUsuario;

    public DCaja() {
    }

    public DCaja(int idCaja, Date fechaApertura, double montoApertura, Date fechaCierre, double montoCierre, int idUsuario) {
        this.idCaja = idCaja;
        this.fechaApertura = fechaApertura;
        this.montoApertura = montoApertura;
        this.fechaCierre = fechaCierre;
        this.montoCierre = montoCierre;
        this.idUsuario = idUsuario;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getMontoApertura() {
        return montoApertura;
    }

    public void setMontoApertura(double montoApertura) {
        this.montoApertura = montoApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public double getMontoCierre() {
        return montoCierre;
    }

    public void setMontoCierre(double montoCierre) {
        this.montoCierre = montoCierre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author hecto
 */
public class DHorarioTrabajo {
    int idHorarioTrabajo;
    Time horaEntrada;
    Date fechaEntrada;
    Time horaSalida;
    Date fechaSalida;
    String observacion;
    int detallecuadrillaId;

    public DHorarioTrabajo() {
    }

    public DHorarioTrabajo(int idHorarioTrabajo, Time horaEntrada, Date fechaEntrada, Time horaSalida, Date fechaSalida, String observacion, int detallecuadrillaId) {
        this.idHorarioTrabajo = idHorarioTrabajo;
        this.horaEntrada = horaEntrada;
        this.fechaEntrada = fechaEntrada;
        this.horaSalida = horaSalida;
        this.fechaSalida = fechaSalida;
        this.observacion = observacion;
        this.detallecuadrillaId = detallecuadrillaId;
    }

    public int getIdHorarioTrabajo() {
        return idHorarioTrabajo;
    }

    public void setIdHorarioTrabajo(int idHorarioTrabajo) {
        this.idHorarioTrabajo = idHorarioTrabajo;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getDetallecuadrillaId() {
        return detallecuadrillaId;
    }

    public void setDetallecuadrillaId(int detallecuadrillaId) {
        this.detallecuadrillaId = detallecuadrillaId;
    }

    
}

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
public class DReclamos {
    int idReclamos;
    String descripcion;
    Date fechaReclamo;
    int serviciodelclientId;
    String estado;

    public DReclamos() {
    }

    public DReclamos(int idReclamos, String descripcion, Date fechaReclamo, int serviciodelclientId, String estado) {
        this.idReclamos = idReclamos;
        this.descripcion = descripcion;
        this.fechaReclamo = fechaReclamo;
        this.serviciodelclientId = serviciodelclientId;
        this.estado = estado;
    }

    public int getIdReclamos() {
        return idReclamos;
    }

    public void setIdReclamos(int idReclamos) {
        this.idReclamos = idReclamos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(Date fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public int getServiciodelclientId() {
        return serviciodelclientId;
    }

    public void setServiciodelclientId(int serviciodelclientId) {
        this.serviciodelclientId = serviciodelclientId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Date;

/**
 *
 * @author josug
 */
public class DSolicitud {
    int idSolicitud;
    Date fechaSolicitud;
    Date fechaActualizado;
    int clienteId;
    int servicioId;
    int detallesolicitudId;
    String estado;

    public DSolicitud() {
    }

    public DSolicitud(int idSolicitud, Date fechaSolicitud, Date fechaActualizado, int clienteId, int servicioId, int detallesolicitudId, String estado) {
        this.idSolicitud = idSolicitud;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaActualizado = fechaActualizado;
        this.clienteId = clienteId;
        this.servicioId = servicioId;
        this.detallesolicitudId = detallesolicitudId;
        this.estado = estado;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaActualizado() {
        return fechaActualizado;
    }

    public void setFechaActualizado(Date fechaActualizado) {
        this.fechaActualizado = fechaActualizado;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public int getDetallesolicitudId() {
        return detallesolicitudId;
    }

    public void setDetallesolicitudId(int detallesolicitudId) {
        this.detallesolicitudId = detallesolicitudId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}

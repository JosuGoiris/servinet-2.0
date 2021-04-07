/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Date;

/**
 *
 * @author jotz
 */
public class DGestionarServicios {
    int idGestionarServicios;
    Date fechaInicio;
    Date fechaPago;
    int clienteId;
    int servicioId;
    String estado;

    public DGestionarServicios() {
    }

    public DGestionarServicios(int idGestionarServicios, Date fechaInicio, Date fechaPago, int clienteId, int servicioId, String estado) {
        this.idGestionarServicios = idGestionarServicios;
        this.fechaInicio = fechaInicio;
        this.fechaPago = fechaPago;
        this.clienteId = clienteId;
        this.servicioId = servicioId;
        this.estado = estado;
    }

    public int getIdGestionarServicios() {
        return idGestionarServicios;
    }

    public void setIdGestionarServicios(int idGestionarServicios) {
        this.idGestionarServicios = idGestionarServicios;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

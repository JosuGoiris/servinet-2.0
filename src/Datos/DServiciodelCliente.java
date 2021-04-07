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
public class DServiciodelCliente {
    int idServiciodelCliente;
    Date fechaInicio;
    Date fechaPago;
    int clienteId;
    int detalleservicioId;
    String estado;
    String estadoFactura;
    double multa;
    String estadoMulta;

    public DServiciodelCliente() {
    }

    public DServiciodelCliente(int idServiciodelCliente, Date fechaInicio, Date fechaPago, int clienteId, int detalleservicioId, String estado, String estadoFactura, double multa, String estadoMulta) {
        this.idServiciodelCliente = idServiciodelCliente;
        this.fechaInicio = fechaInicio;
        this.fechaPago = fechaPago;
        this.clienteId = clienteId;
        this.detalleservicioId = detalleservicioId;
        this.estado = estado;
        this.estadoFactura = estadoFactura;
        this.multa = multa;
        this.estadoMulta = estadoMulta;
    }

    public int getIdServiciodelCliente() {
        return idServiciodelCliente;
    }

    public void setIdServiciodelCliente(int idServiciodelCliente) {
        this.idServiciodelCliente = idServiciodelCliente;
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

    public int getDetalleservicioId() {
        return detalleservicioId;
    }

    public void setDetalleservicioId(int detalleservicioId) {
        this.detalleservicioId = detalleservicioId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public String getEstadoMulta() {
        return estadoMulta;
    }

    public void setEstadoMulta(String estadoMulta) {
        this.estadoMulta = estadoMulta;
    }

    
}

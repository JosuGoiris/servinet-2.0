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
public class DPagos {
    int idPagos;
    Date fechaPago;
    Date fechaRealizado;
    String estado;
    int serviciodelclienteId;
    int formadepagoId;
    int detallefacturaId;

    public DPagos() {
    }

    public DPagos(int idPagos, Date fechaPago, Date fechaRealizado, String estado, int serviciodelclienteId, int formadepagoId, int detallefacturaId) {
        this.idPagos = idPagos;
        this.fechaPago = fechaPago;
        this.fechaRealizado = fechaRealizado;
        this.estado = estado;
        this.serviciodelclienteId = serviciodelclienteId;
        this.formadepagoId = formadepagoId;
        this.detallefacturaId = detallefacturaId;
    }

    public int getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(int idPagos) {
        this.idPagos = idPagos;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaRealizado() {
        return fechaRealizado;
    }

    public void setFechaRealizado(Date fechaRealizado) {
        this.fechaRealizado = fechaRealizado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getServiciodelclienteId() {
        return serviciodelclienteId;
    }

    public void setServiciodelclienteId(int serviciodelclienteId) {
        this.serviciodelclienteId = serviciodelclienteId;
    }

    public int getFormadepagoId() {
        return formadepagoId;
    }

    public void setFormadepagoId(int formadepagoId) {
        this.formadepagoId = formadepagoId;
    }

    public int getDetallefacturaId() {
        return detallefacturaId;
    }

    public void setDetallefacturaId(int detallefacturaId) {
        this.detallefacturaId = detallefacturaId;
    }

    
}

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
public class DDetalleFactura {
    int idDetalleFactura;
    String descripcion;
    String mesPago;
    double precio;
    double sobrecargos;
    double total;
    int facturaId;
    int serviciodelclienteId;
    int cantidadfacturasId;
    int montoId;
    String estado;

    public DDetalleFactura() {
    }

    public DDetalleFactura(int idDetalleFactura, String descripcion, String mesPago, double precio, double sobrecargos, double total, int facturaId, int serviciodelclienteId, int cantidadfacturasId, int montoId, String estado) {
        this.idDetalleFactura = idDetalleFactura;
        this.descripcion = descripcion;
        this.mesPago = mesPago;
        this.precio = precio;
        this.sobrecargos = sobrecargos;
        this.total = total;
        this.facturaId = facturaId;
        this.serviciodelclienteId = serviciodelclienteId;
        this.cantidadfacturasId = cantidadfacturasId;
        this.montoId = montoId;
        this.estado = estado;
    }

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMesPago() {
        return mesPago;
    }

    public void setMesPago(String mesPago) {
        this.mesPago = mesPago;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSobrecargos() {
        return sobrecargos;
    }

    public void setSobrecargos(double sobrecargos) {
        this.sobrecargos = sobrecargos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getServiciodelclienteId() {
        return serviciodelclienteId;
    }

    public void setServiciodelclienteId(int serviciodelclienteId) {
        this.serviciodelclienteId = serviciodelclienteId;
    }

    public int getCantidadfacturasId() {
        return cantidadfacturasId;
    }

    public void setCantidadfacturasId(int cantidadfacturasId) {
        this.cantidadfacturasId = cantidadfacturasId;
    }

    public int getMontoId() {
        return montoId;
    }

    public void setMontoId(int montoId) {
        this.montoId = montoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

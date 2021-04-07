/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author josug
 */
public class DFormadePago {
    int idFormadePago;
    String nombreForma;
    String descripcion;
    String estado;

    public DFormadePago() {
    }

    public DFormadePago(int idFormadePago, String nombreForma, String descripcion, String estado) {
        this.idFormadePago = idFormadePago;
        this.nombreForma = nombreForma;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdFormadePago() {
        return idFormadePago;
    }

    public void setIdFormadePago(int idFormadePago) {
        this.idFormadePago = idFormadePago;
    }

    public String getNombreForma() {
        return nombreForma;
    }

    public void setNombreForma(String nombreForma) {
        this.nombreForma = nombreForma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}

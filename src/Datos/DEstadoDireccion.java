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
public class DEstadoDireccion {
    int idEstadoDireccion;
    String estado;

    public DEstadoDireccion() {
    }

    public DEstadoDireccion(int idEstadoDireccion, String estado) {
        this.idEstadoDireccion = idEstadoDireccion;
        this.estado = estado;
    }

    public int getIdEstadoDireccion() {
        return idEstadoDireccion;
    }

    public void setIdEstadoDireccion(int idEstadoDireccion) {
        this.idEstadoDireccion = idEstadoDireccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

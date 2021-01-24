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
public class DEstadoZonas {
    int idEstadoZona;
    String estado;

    public DEstadoZonas() {
    }

    public DEstadoZonas(int idEstadoZona, String estado) {
        this.idEstadoZona = idEstadoZona;
        this.estado = estado;
    }

    public int getIdEstadoZona() {
        return idEstadoZona;
    }

    public void setIdEstadoZona(int idEstadoZona) {
        this.idEstadoZona = idEstadoZona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

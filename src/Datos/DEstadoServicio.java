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
public class DEstadoServicio {
    int idestadoServicio;
    String estado;

    public DEstadoServicio() {
    }

    public DEstadoServicio(int idestadoServicio, String estado) {
        this.idestadoServicio = idestadoServicio;
        this.estado = estado;
    }

    public int getIdestadoServicio() {
        return idestadoServicio;
    }

    public void setIdestadoServicio(int idestadoServicio) {
        this.idestadoServicio = idestadoServicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

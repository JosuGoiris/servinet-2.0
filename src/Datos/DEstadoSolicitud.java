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
public class DEstadoSolicitud {
    int IdEstadoSolicitud;
    String estado;

    public DEstadoSolicitud() {
    }

    public DEstadoSolicitud(int IdEstadoSolicitud, String estado) {
        this.IdEstadoSolicitud = IdEstadoSolicitud;
        this.estado = estado;
    }

    public int getIdEstadoSolicitud() {
        return IdEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(int IdEstadoSolicitud) {
        this.IdEstadoSolicitud = IdEstadoSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

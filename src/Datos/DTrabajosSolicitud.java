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
public class DTrabajosSolicitud {
    int idTrabajosSolicitud;
    int solicitudId;
    int cuadrillaId;

    public DTrabajosSolicitud() {
    }

    public DTrabajosSolicitud(int idTrabajosSolicitud, int solicitudId, int cuadrillaId) {
        this.idTrabajosSolicitud = idTrabajosSolicitud;
        this.solicitudId = solicitudId;
        this.cuadrillaId = cuadrillaId;
    }

    public int getIdTrabajosSolicitud() {
        return idTrabajosSolicitud;
    }

    public void setIdTrabajosSolicitud(int idTrabajosSolicitud) {
        this.idTrabajosSolicitud = idTrabajosSolicitud;
    }

    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public int getCuadrillaId() {
        return cuadrillaId;
    }

    public void setCuadrillaId(int cuadrillaId) {
        this.cuadrillaId = cuadrillaId;
    }
    
    
}

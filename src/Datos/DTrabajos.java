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
public class DTrabajos {
    int idTrabajos;
    int trabajossolicitudId;
    int trabajosreclamosId;
    int detalletrabajoId;
    String estado;

    public DTrabajos() {
    }

    public DTrabajos(int idTrabajos, int trabajossolicitudId, int trabajosreclamosId, int detalletrabajoId, String estado) {
        this.idTrabajos = idTrabajos;
        this.trabajossolicitudId = trabajossolicitudId;
        this.trabajosreclamosId = trabajosreclamosId;
        this.detalletrabajoId = detalletrabajoId;
        this.estado = estado;
    }

    public int getIdTrabajos() {
        return idTrabajos;
    }

    public void setIdTrabajos(int idTrabajos) {
        this.idTrabajos = idTrabajos;
    }

    public int getTrabajossolicitudId() {
        return trabajossolicitudId;
    }

    public void setTrabajossolicitudId(int trabajossolicitudId) {
        this.trabajossolicitudId = trabajossolicitudId;
    }

    public int getTrabajosreclamosId() {
        return trabajosreclamosId;
    }

    public void setTrabajosreclamosId(int trabajosreclamosId) {
        this.trabajosreclamosId = trabajosreclamosId;
    }

    public int getDetalletrabajoId() {
        return detalletrabajoId;
    }

    public void setDetalletrabajoId(int detalletrabajoId) {
        this.detalletrabajoId = detalletrabajoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}

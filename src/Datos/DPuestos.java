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
public class DPuestos {
    int idPuestoTrabajo;
    String nombrePuesto;
    int estadopuestoId;

    public DPuestos() {
    }

    public DPuestos(int idPuestoTrabajo, String nombrePuesto, int estadopuestoId) {
        this.idPuestoTrabajo = idPuestoTrabajo;
        this.nombrePuesto = nombrePuesto;
        this.estadopuestoId = estadopuestoId;
    }

    public int getEstadopuestoId() {
        return estadopuestoId;
    }

    public void setEstadopuestoId(int estadopuestoId) {
        this.estadopuestoId = estadopuestoId;
    }
    
    public int getIdPuestoTrabajo() {
        return idPuestoTrabajo;
    }

    public void setIdPuestoTrabajo(int idPuestoTrabajo) {
        this.idPuestoTrabajo = idPuestoTrabajo;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }
    
    
}

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
    String estado;
    String Descripcion;
    
    public DPuestos() {
    }

    public DPuestos(int idPuestoTrabajo, String nombrePuesto, String estado, String Descripcion) {
        this.idPuestoTrabajo = idPuestoTrabajo;
        this.nombrePuesto = nombrePuesto;
        this.estado = estado;
        this.Descripcion = Descripcion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    
}

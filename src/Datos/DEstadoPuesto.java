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
public class DEstadoPuesto {
    int idEstadoPuesto;
    String estado;

    public DEstadoPuesto() {
    }

    public DEstadoPuesto(int idEstadoPuesto, String estado) {
        this.idEstadoPuesto = idEstadoPuesto;
        this.estado = estado;
    }

    public int getIdEstadoPuesto() {
        return idEstadoPuesto;
    }

    public void setIdEstadoPuesto(int idEstadoPuesto) {
        this.idEstadoPuesto = idEstadoPuesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

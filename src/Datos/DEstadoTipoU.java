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
public class DEstadoTipoU {
    int idEstadoTipoU;
    String estado;

    public DEstadoTipoU() {
    }

    public DEstadoTipoU(int idEstadoTipoU, String estado) {
        this.idEstadoTipoU = idEstadoTipoU;
        this.estado = estado;
    }

    public int getIdEstadoTipoU() {
        return idEstadoTipoU;
    }

    public void setIdEstadoTipoU(int idEstadoTipoU) {
        this.idEstadoTipoU = idEstadoTipoU;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

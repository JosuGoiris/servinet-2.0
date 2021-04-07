/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author jotz
 */
public class DTrabajosReclamos {
    int idTrabajosReclamos;
    int reclamosId;
    int cuadrillasId;

    public DTrabajosReclamos() {
    }

    public DTrabajosReclamos(int idTrabajosReclamos, int reclamosId, int cuadrillasId) {
        this.idTrabajosReclamos = idTrabajosReclamos;
        this.reclamosId = reclamosId;
        this.cuadrillasId = cuadrillasId;
    }

    public int getIdTrabajosReclamos() {
        return idTrabajosReclamos;
    }

    public void setIdTrabajosReclamos(int idTrabajosReclamos) {
        this.idTrabajosReclamos = idTrabajosReclamos;
    }

    public int getReclamosId() {
        return reclamosId;
    }

    public void setReclamosId(int reclamosId) {
        this.reclamosId = reclamosId;
    }

    public int getCuadrillasId() {
        return cuadrillasId;
    }

    public void setCuadrillasId(int cuadrillasId) {
        this.cuadrillasId = cuadrillasId;
    }
    
    
}

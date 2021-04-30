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
public class DDetalleCuadrilla {
    int idDetalleCuadrilla;
    int cuadrillaId;
    int trabajadorId;

    public DDetalleCuadrilla() {
    }

    public DDetalleCuadrilla(int idDetalleCuadrilla, int cuadrillaId, int trabajadorId) {
        this.idDetalleCuadrilla = idDetalleCuadrilla;
        this.cuadrillaId = cuadrillaId;
        this.trabajadorId = trabajadorId;
    }

    public int getIdDetalleCuadrilla() {
        return idDetalleCuadrilla;
    }

    public void setIdDetalleCuadrilla(int idDetalleCuadrilla) {
        this.idDetalleCuadrilla = idDetalleCuadrilla;
    }

    public int getCuadrillaId() {
        return cuadrillaId;
    }

    public void setCuadrillaId(int cuadrillaId) {
        this.cuadrillaId = cuadrillaId;
    }

    public int getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }
    
    
}

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
public class DCantidadTrabajadores {
    int idDetalleCuadrilla;
    int cantidad;

    public DCantidadTrabajadores() {
    }

    public DCantidadTrabajadores(int idDetalleCuadrilla, int cantidad) {
        this.idDetalleCuadrilla = idDetalleCuadrilla;
        this.cantidad = cantidad;
    }

    public int getIdDetalleCuadrilla() {
        return idDetalleCuadrilla;
    }

    public void setIdDetalleCuadrilla(int idDetalleCuadrilla) {
        this.idDetalleCuadrilla = idDetalleCuadrilla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
     
}

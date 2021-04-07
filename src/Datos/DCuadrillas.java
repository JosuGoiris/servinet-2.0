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
public class DCuadrillas {
    int idCuadrilla;
    String nombreCuadrilla;
    int cantidadTrabajadores;
    String descripcion;
    int trabajadorId;
    int estadocuadrillaId;

    public DCuadrillas() {
    }

    public DCuadrillas(int idCuadrilla, String nombreCuadrilla, int cantidadTrabajadores, String descripcion, int trabajadorId, int estadocuadrillaId) {
        this.idCuadrilla = idCuadrilla;
        this.nombreCuadrilla = nombreCuadrilla;
        this.cantidadTrabajadores = cantidadTrabajadores;
        this.descripcion = descripcion;
        this.trabajadorId = trabajadorId;
        this.estadocuadrillaId = estadocuadrillaId;
    }

    public int getIdCuadrilla() {
        return idCuadrilla;
    }

    public void setIdCuadrilla(int idCuadrilla) {
        this.idCuadrilla = idCuadrilla;
    }

    public String getNombreCuadrilla() {
        return nombreCuadrilla;
    }

    public void setNombreCuadrilla(String nombreCuadrilla) {
        this.nombreCuadrilla = nombreCuadrilla;
    }

    public int getCantidadTrabajadores() {
        return cantidadTrabajadores;
    }

    public void setCantidadTrabajadores(int cantidadTrabajadores) {
        this.cantidadTrabajadores = cantidadTrabajadores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public int getEstadocuadrillaId() {
        return estadocuadrillaId;
    }

    public void setEstadocuadrillaId(int estadocuadrillaId) {
        this.estadocuadrillaId = estadocuadrillaId;
    }
    
     
}

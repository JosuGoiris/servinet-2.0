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
public class DDireccion {
    int idDireccion;
    String nombreDireccion;
    String descripcion;
    int estadodireccionId;

    public DDireccion() {
    }

    public DDireccion(int idDireccion, String nombreDireccion, String descripcion, int estadodireccionId) {
        this.idDireccion = idDireccion;
        this.nombreDireccion = nombreDireccion;
        this.descripcion = descripcion;
        this.estadodireccionId = estadodireccionId;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstadodireccionId() {
        return estadodireccionId;
    }

    public void setEstadodireccionId(int estadodireccionId) {
        this.estadodireccionId = estadodireccionId;
    }
    
    
}

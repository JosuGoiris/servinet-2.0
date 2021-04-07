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
public class DTipoServicio {
    int idTipoServicio;
    String nombre;
    int estadotiposerId;

    public DTipoServicio() {
    }

    public DTipoServicio(int idTipoServicio, String nombre, int estadotiposerId) {
        this.idTipoServicio = idTipoServicio;
        this.nombre = nombre;
        this.estadotiposerId = estadotiposerId;
    }

    public int getEstadotiposerId() {
        return estadotiposerId;
    }

    public void setEstadotiposerId(int estadotiposerId) {
        this.estadotiposerId = estadotiposerId;
    }

    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

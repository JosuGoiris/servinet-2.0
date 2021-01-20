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
public class DTipoUsuario {
    int IdTipoUsuario;
    String nombre;
    int estadotipoId;

    public DTipoUsuario() {
    }

    public DTipoUsuario(int IdTipoUsuario, String nombre, int estadotipoId) {
        this.IdTipoUsuario = IdTipoUsuario;
        this.nombre = nombre;
        this.estadotipoId = estadotipoId;
    }

    public int getEstadotipoId() {
        return estadotipoId;
    }

    public void setEstadotipoId(int estadotipoId) {
        this.estadotipoId = estadotipoId;
    }

    public int getIdTipoUsuario() {
        return IdTipoUsuario;
    }

    public void setIdTipoUsuario(int IdTipoUsuario) {
        this.IdTipoUsuario = IdTipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

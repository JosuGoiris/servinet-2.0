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

    public DTipoUsuario() {
    }

    public DTipoUsuario(int IdTipoUsuario, String nombre) {
        this.IdTipoUsuario = IdTipoUsuario;
        this.nombre = nombre;
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

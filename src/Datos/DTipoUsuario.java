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
    String Tipo;

    public DTipoUsuario() {
    }

    public DTipoUsuario(int IdTipoUsuario, String Tipo) {
        this.IdTipoUsuario = IdTipoUsuario;
        this.Tipo = Tipo;
    }

    public int getIdTipoUsuario() {
        return IdTipoUsuario;
    }

    public void setIdTipoUsuario(int IdTipoUsuario) {
        this.IdTipoUsuario = IdTipoUsuario;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    
}

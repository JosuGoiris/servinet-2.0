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
public class DUsuarios {
    int IdUsuario;
    String LoginUsuario;
    String passwordUsuario;
    int PersonaId;
    int TipoUsuarioId;

    public DUsuarios() {
    }

    public DUsuarios(int IdUsuario, String LoginUsuario, String passwordUsuario, int PersonaId, int TipoUsuarioId) {
        this.IdUsuario = IdUsuario;
        this.LoginUsuario = LoginUsuario;
        this.passwordUsuario = passwordUsuario;
        this.PersonaId = PersonaId;
        this.TipoUsuarioId = TipoUsuarioId;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getLoginUsuario() {
        return LoginUsuario;
    }

    public void setLoginUsuario(String LoginUsuario) {
        this.LoginUsuario = LoginUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public int getPersonaId() {
        return PersonaId;
    }

    public void setPersonaId(int PersonaId) {
        this.PersonaId = PersonaId;
    }

    public int getTipoUsuarioId() {
        return TipoUsuarioId;
    }

    public void setTipoUsuarioId(int TipoUsuarioId) {
        this.TipoUsuarioId = TipoUsuarioId;
    }
    
    
}

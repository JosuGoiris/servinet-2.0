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
public class DPersona {
    int IdPersona;
    int DireccionId;
    String Nombre;
    String Apellido;
    String CedulaIdent;
    String Telefono;

    public DPersona() {
    }

    public DPersona(int IdPersona, int DireccionId, String Nombre, String Apellido, String CedulaIdent, String Telefono) {
        this.IdPersona = IdPersona;
        this.DireccionId = DireccionId;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.CedulaIdent = CedulaIdent;
        this.Telefono = Telefono;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }

    public int getDireccionId() {
        return DireccionId;
    }

    public void setDireccionId(int DireccionId) {
        this.DireccionId = DireccionId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCedulaIdent() {
        return CedulaIdent;
    }

    public void setCedulaIdent(String CedulaIdent) {
        this.CedulaIdent = CedulaIdent;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    
}

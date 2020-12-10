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
public class DTrabajadores {
    int idTrabajador;
    String estado;
    int puestotrabajadorId;
    int personaId;
    int cuadrillaId;

    public DTrabajadores() {
    }

    public DTrabajadores(int idTrabajador, String estado, int puestotrabajadorId, int personaId, int cuadrillaId) {
        this.idTrabajador = idTrabajador;
        this.estado = estado;
        this.puestotrabajadorId = puestotrabajadorId;
        this.personaId = personaId;
        this.cuadrillaId = cuadrillaId;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPuestotrabajadorId() {
        return puestotrabajadorId;
    }

    public void setPuestotrabajadorId(int puestotrabajadorId) {
        this.puestotrabajadorId = puestotrabajadorId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getCuadrillaId() {
        return cuadrillaId;
    }

    public void setCuadrillaId(int cuadrillaId) {
        this.cuadrillaId = cuadrillaId;
    }
    
    
}

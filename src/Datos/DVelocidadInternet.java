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
public class DVelocidadInternet {
    int idVelocidadCon;
    String velocidad;
    int estadovelocidadId;

    public DVelocidadInternet() {
    }

    public DVelocidadInternet(int idVelocidadCon, String velocidad, int estadovelocidadId) {
        this.idVelocidadCon = idVelocidadCon;
        this.velocidad = velocidad;
        this.estadovelocidadId = estadovelocidadId;
    }

    public int getIdVelocidadCon() {
        return idVelocidadCon;
    }

    public void setIdVelocidadCon(int idVelocidadCon) {
        this.idVelocidadCon = idVelocidadCon;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public int getEstadovelocidadId() {
        return estadovelocidadId;
    }

    public void setEstadovelocidadId(int estadovelocidadId) {
        this.estadovelocidadId = estadovelocidadId;
    }
    
     
}

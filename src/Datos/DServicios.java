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
public class DServicios {
    int idServicio;
    String nombreServicio;
    int velocidadconId;
    String precio;
    String descripcio;
    int estadoservicioId;
    int tiposervicioId;

    public DServicios() {
    }

    public DServicios(int idServicio, String nombreServicio, int velocidadconId , String precio, String descripcio, int estadoservicioId, int tiposervicioId) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.velocidadconId = velocidadconId;
        this.precio = precio;
        this.descripcio = descripcio;
        this.estadoservicioId = estadoservicioId;
        this.tiposervicioId = tiposervicioId;
    }

    public int getVelocidadconId() {
        return velocidadconId;
    }

    public void setVelocidadconId(int velocidadconId) {
        this.velocidadconId = velocidadconId;
    }
    
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getEstadoservicioId() {
        return estadoservicioId;
    }

    public void setEstadoservicioId(int estadoservicioId) {
        this.estadoservicioId = estadoservicioId;
    }

    public int getTiposervicioId() {
        return tiposervicioId;
    }

    public void setTiposervicioId(int tiposervicioId) {
        this.tiposervicioId = tiposervicioId;
    }
    
    
}

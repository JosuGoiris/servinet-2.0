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
public class DZonas {
    int idZona;
    String nombreZona;
    String descripcion;
    int estadozonaId;

    public DZonas() {
    }

    public DZonas(int idZona, String nombreZona, String descripcion, int estadozonaId) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
        this.descripcion = descripcion;
        this.estadozonaId = estadozonaId;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstadozonaId() {
        return estadozonaId;
    }

    public void setEstadozonaId(int estadozonaId) {
        this.estadozonaId = estadozonaId;
    }
    
    
}

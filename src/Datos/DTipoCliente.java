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
public class DTipoCliente {
    int id;
    String tipoCliente;
    int estadotipoclienteId;

    public DTipoCliente() {
    }

    public DTipoCliente(int id, String tipoCliente, int estadotipoclienteId) {
        this.id = id;
        this.tipoCliente = tipoCliente;
        this.estadotipoclienteId = estadotipoclienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getEstadotipoclienteId() {
        return estadotipoclienteId;
    }

    public void setEstadotipoclienteId(int estadotipoclienteId) {
        this.estadotipoclienteId = estadotipoclienteId;
    }
    
    
}

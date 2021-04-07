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
public class DClientes {
    int id;
    String ruc;
    int personaId;
    int tipoclienteId;

    public DClientes() {
    }

    public DClientes(int id, String ruc, int personaId, int tipoclienteId) {
        this.id = id;
        this.ruc = ruc;
        this.personaId = personaId;
        this.tipoclienteId = tipoclienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getTipoclienteId() {
        return tipoclienteId;
    }

    public void setTipoclienteId(int tipoclienteId) {
        this.tipoclienteId = tipoclienteId;
    }

    

     
}

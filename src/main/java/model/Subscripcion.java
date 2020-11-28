/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author espin
 */
public class Subscripcion implements Serializable{
    protected Lista Lista;
    protected Usuario Usuario;

    public Subscripcion(Lista Lista, Usuario Usuario) {
        this.Lista = Lista;
        this.Usuario = Usuario;
    }

    public Subscripcion() {
        this(null,null);
    }
    

    public Lista getLista() {
        return Lista;
    }

    public void setLista(Lista Lista) {
        this.Lista = Lista;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean igual=false;
        if(obj!=null){
            if(this==obj){
                igual=true;
            }else{
                if(obj instanceof Subscripcion){
                    Subscripcion n=(Subscripcion) obj;
                   if (this.Lista == n.getLista() && this.Usuario == n.getUsuario()) {
                        igual = true;
                    }
                }
            }
        }
        return igual;
    }

    @Override
    public String toString() {
        return "Subscripci\u00f3n{" + "Lista=" + Lista + ", Usuario=" + Usuario + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author espin
 */
public class Subscripción {
    private Lista Lista;
    private Usuario Usuario;

    public Subscripción(Lista Lista, Usuario Usuario) {
        this.Lista = Lista;
        this.Usuario = Usuario;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Subscripción other = (Subscripción) obj;
        if (!Objects.equals(this.Lista, other.Lista)) {
            return false;
        }
        if (!Objects.equals(this.Usuario, other.Usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subscripci\u00f3n{" + "Lista=" + Lista + ", Usuario=" + Usuario + '}';
    }
    
    
}

package model;

import java.util.Objects;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author espin
 */
public class ListaCancion {
    private Lista Lista;
    private Cancion Cancion;

    public ListaCancion(Lista Lista, Cancion Cancion) {
        this.Lista = Lista;
        this.Cancion = Cancion;
    }

    public Lista getLista() {
        return Lista;
    }

    public void setLista(Lista Lista) {
        this.Lista = Lista;
    }

    public Cancion getCancion() {
        return Cancion;
    }

    public void setCancion(Cancion Cancion) {
        this.Cancion = Cancion;
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
        final ListaCancion other = (ListaCancion) obj;
        if (!Objects.equals(this.Lista, other.Lista)) {
            return false;
        }
        if (!Objects.equals(this.Cancion, other.Cancion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ListaCancion{" + "Lista=" + Lista + ", Cancion=" + Cancion + '}';
    }
    
    
    
}

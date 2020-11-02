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

    public ListaCancion() {
        this (null,null);
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
       boolean igual=false;
        if(obj!=null){
            if(this==obj){
                igual=true;
            }else{
                if(obj instanceof ListaCancion){
                    ListaCancion n=(ListaCancion) obj;
                   if (this.Lista == n.getLista() && this.Cancion == n.getCancion() ) {
                        igual = true;
                    }
                }
            }
        }
        return igual;
    }

    @Override
    public String toString() {
        return "ListaCancion{" + "Lista=" + Lista + ", Cancion=" + Cancion + '}';
    }
    
    
    
}

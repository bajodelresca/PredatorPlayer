/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author espin
 */
public class Reproduce {
    private int ID;
    private Usuario Usuario;
    private Cancion Cancion;
    private Timestamp Instante;

    public Reproduce(int ID, Usuario Usuario, Cancion Cancion, Timestamp Instante) {
        this.ID = ID;
        this.Usuario = Usuario;
        this.Cancion = Cancion;
        this.Instante = Instante;
    }

    public Reproduce(int ID, Timestamp Instante) {
        this.ID = ID;
        this.Instante = Instante;
    }

    public Reproduce() {
        this (-1,null,null,null);
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Cancion getCancion() {
        return Cancion;
    }

    public void setCancion(Cancion Cancion) {
        this.Cancion = Cancion;
    }

    public Timestamp getInstante() {
        return Instante;
    }

    public void setInstante(Timestamp Instante) {
        this.Instante = Instante;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean igual=false;
        if(obj!=null){
            if(this==obj){
                igual=true;
            }else{
                if(obj instanceof Reproduce){
                    Reproduce n=(Reproduce) obj;
                   if (this.ID == n.getID()) {
                        igual = true;
                    }
                }
            }
        }
        return igual;
    }

    @Override
    public String toString() {
        return "Reproduce{" + "ID=" + ID + ", Usuario=" + Usuario + ", Cancion=" + Cancion + ", Instante=" + Instante + '}';
    }
    
    
    
}

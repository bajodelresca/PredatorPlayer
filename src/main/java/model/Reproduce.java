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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reproduce other = (Reproduce) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reproduce{" + "ID=" + ID + ", Usuario=" + Usuario + ", Cancion=" + Cancion + ", Instante=" + Instante + '}';
    }
    
    
    
}

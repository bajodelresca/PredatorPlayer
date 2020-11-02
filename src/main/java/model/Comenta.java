/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author espin
 */
public class Comenta {
    private int ID;
    private Usuario Usuario;
    private Lista Lista;
    private String Mensaje;
    private Timestamp Instante;

    public Comenta(int ID, Usuario Usuario, Lista Lista, String Mensaje, Timestamp Instante) {
        this.ID = ID;
        this.Usuario = Usuario;
        this.Lista = Lista;
        this.Mensaje = Mensaje;
        this.Instante = Instante;
    }

    public Comenta(int ID, String Mensaje, Timestamp Instante) {
        this.ID = ID;
        this.Mensaje = Mensaje;
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

    public Lista getLista() {
        return Lista;
    }

    public void setLista(Lista Lista) {
        this.Lista = Lista;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
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
        final Comenta other = (Comenta) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comenta{" + "ID=" + ID + ", Usuario=" + Usuario + ", Lista=" + Lista + ", Mensaje=" + Mensaje + ", Instante=" + Instante + '}';
    }
    
    
}

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

    public Comenta() {
        this(-1,null,null,"",null);
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
        boolean igual=false;
        if(obj!=null){
            if(this==obj){
                igual=true;
            }else{
                if(obj instanceof Comenta){
                    Comenta n=(Comenta) obj;
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
        return "Comenta{" + "ID=" + ID + ", Usuario=" + Usuario + ", Lista=" + Lista + ", Mensaje=" + Mensaje + ", Instante=" + Instante + '}';
    }
    
    
}

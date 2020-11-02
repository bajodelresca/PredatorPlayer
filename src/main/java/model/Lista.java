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
public class Lista {
    private int ID;
    private String Nombre;
    private String Descripcion;
    private Usuario creador;

    public Lista(int ID, String Nombre, String Descripcion, Usuario creador) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.creador = creador;
    }

    public Lista(int ID, String Nombre, String Descripcion) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
    }

    public Lista() {
        this(-1,"","",null);
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
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
                if(obj instanceof Lista){
                    Lista n=(Lista) obj;
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
        return "Lista{" + "ID=" + ID + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion + ", creador=" + creador + '}';
    }
    
    
    
    
}

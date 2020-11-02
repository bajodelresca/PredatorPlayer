/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;

/**
 *
 * @author espin
 */
public class Usuario {
    private int ID;
    private String Nombre;
    private String Correo;
    private Blob Foto;

    public Usuario(int ID, String Nombre, String Correo, Blob Foto) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Foto = Foto;
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

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public Blob getFoto() {
        return Foto;
    }

    public void setFoto(Blob Foto) {
        this.Foto = Foto;
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
        final Usuario other = (Usuario) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "ID=" + ID + ", Nombre=" + Nombre + ", Correo=" + Correo + ", Foto=" + Foto + '}';
    }
    
    
}

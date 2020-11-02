/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;
import java.util.List;

/**
 *
 * @author Alberto343
 */
public class Artista {
   private int ID;
   private String nombre;
   private String nacionalidad;
   private Blob foto;
   private List<Disco> repertorio;

    public Artista(int ID, String nombre, String nacionalidad, Blob foto, List<Disco> repertorio) {
        this.ID = ID;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
        this.repertorio = repertorio;
    }

    public Artista(int ID, String nombre, String nacionalidad, Blob foto) {
        this.ID = ID;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public List<Disco> getRepertorio() {
        return repertorio;
    }

    public void setRepertorio(List<Disco> repertorio) {
        this.repertorio = repertorio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Artista other = (Artista) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Artista{" + "ID=" + ID + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", foto=" + foto + ", repertorio=" + repertorio + '}';
    }

    
   
    
    
    
}

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
   protected int ID;
   protected String nombre;
   protected String nacionalidad;
   protected String foto;
   protected List<Disco> repertorio;

    public Artista(int ID, String nombre, String nacionalidad, String foto, List<Disco> repertorio) {
        this.ID = ID;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
        this.repertorio = repertorio;
    }

    public Artista(int ID, String nombre, String nacionalidad, String foto) {
        this.ID = ID;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
    }

    public Artista() {
        this(-1,"","",null,null);
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
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
         boolean igual=false;
        if(obj!=null){
            if(this==obj){
                igual=true;
            }else{
                if(obj instanceof Artista){
                    Artista n=(Artista) obj;
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
        return "Artista{" + "ID=" + ID + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", foto=" + foto + ", repertorio=" + repertorio + '}';
    }

    
   
    
    
    
}

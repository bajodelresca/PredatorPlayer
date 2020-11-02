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
public class Cancion {
    private int ID;
    private String Nombre;
    private int Duración;
    private Genero Genero;
    private Disco Album;

    public Cancion(int ID, String Nombre, int Duración, Genero Genero, Disco Album) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Duración = Duración;
        this.Genero = Genero;
        this.Album = Album;
    }

    public Cancion(int ID, String Nombre, int Duración) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Duración = Duración;
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

    public int getDuración() {
        return Duración;
    }

    public void setDuración(int Duración) {
        this.Duración = Duración;
    }

    public Genero getGenero() {
        return Genero;
    }

    public void setGenero(Genero Genero) {
        this.Genero = Genero;
    }

    public Disco getAlbum() {
        return Album;
    }

    public void setAlbum(Disco Album) {
        this.Album = Album;
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
        final Cancion other = (Cancion) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cancion{" + "ID=" + ID + ", Nombre=" + Nombre + ", Duraci\u00f3n=" + Duración + ", Genero=" + Genero + ", Album=" + Album + '}';
    }
    
    
}

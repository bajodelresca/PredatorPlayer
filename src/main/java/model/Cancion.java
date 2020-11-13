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

    protected int ID;
    protected String Nombre;
    protected int Duracion;
    protected int Genero;
    protected Disco Album;

    public Cancion(int ID, String Nombre, int Duracion, Disco Album) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Duracion = Duracion;
        this.Genero = 0;
        this.Album = Album;
    }

    public Cancion(String Nombre, int Duracion, Disco Album) {
        this.ID = -1;
        this.Nombre = Nombre;
        this.Duracion = Duracion;
        this.Genero = 0;
        this.Album = Album;
    }

    public Cancion() {
        this(-1, "", -1, null);
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

    public int getDuracion() {
        return Duracion;
    }

    public void setDuracion(int Duracion) {
        this.Duracion = Duracion;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int Genero) {
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
        boolean igual = false;
        if (obj != null) {
            if (this == obj) {
                igual = true;
            } else {
                if (obj instanceof Cancion) {
                    Cancion n = (Cancion) obj;
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
        return "Cancion{" + "ID=" + ID + ", Nombre=" + Nombre + ", Duracion=" + Duracion + ", Genero=" + Genero + ", Album=" + Album + '}';
    }

}

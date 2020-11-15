/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.AppController;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author espin
 */
public class Usuario {

    private static AppController controlador = AppController.getInstance();
    protected int ID;
    protected String Nombre;
    protected String Correo;
    protected String Foto;
    protected List<Lista> listasubscrito;

    public Usuario(int ID, String Nombre, String Correo, String Foto, List<Lista> listasubscrito) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Foto = Foto;
        this.listasubscrito = listasubscrito;
    }

    public Usuario(int ID, String Nombre, String Correo, String Foto) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Foto = Foto;
    }

    public Usuario() {
        this(-1, "", "", null, null);
    }

    public List<Lista> getListasubscrito() {
        List<Lista> listaSub = controlador.getListFromSubscriber(this.ID);
        if (!listaSub.isEmpty()) {
            this.setListasubscrito(listaSub);
        } else {
            listaSub = new ArrayList<>();
            this.setListasubscrito(listaSub);
        }
        return listasubscrito;
    }

    public void setListasubscrito(List<Lista> listasubscrito) {
        this.listasubscrito = listasubscrito;
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

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean igual = false;
        if (obj != null) {
            if (this == obj) {
                igual = true;
            } else {
                if (obj instanceof Usuario) {
                    Usuario n = (Usuario) obj;
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
        return "Usuario{" + "ID=" + ID + ", Nombre=" + Nombre + ", Correo=" + Correo + ", Foto=" + Foto + '}';
    }

}

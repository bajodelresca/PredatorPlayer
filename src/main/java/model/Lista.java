/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.cj.x.protobuf.MysqlxCursor.Fetch;
import controller.AppController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author espin
 */
@Entity
@Table(name="LISTA")
public class Lista implements Serializable {
    
    private static AppController controlador = AppController.getInstance();
    @Id
    @Column(name="ID")
    protected int ID;
    @Column(name="NOMBRE")
    protected String Nombre;
    @Column(name="DESCRIPCION")
    protected String Descripcion;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CREADOR")
    protected Usuario creador;
    @OneToMany(mappedBy = "ID",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    protected List<Usuario> subscriptores;
    @Column(name="LISTAREPRODUCCION")
    @JoinTable(
        name = "listacancion",
        joinColumns = @JoinColumn(name = "FK_LISTA", nullable = false),
        inverseJoinColumns = @JoinColumn(name="FK_CANCION", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    protected List<Cancion> listareproduccion;
    
    
   
    public Lista(int ID, String Nombre, String Descripcion, Usuario creador, List<Usuario> subscriptores, List<Cancion> listareproduccion) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.creador = creador;
        this.subscriptores = subscriptores;
        this.listareproduccion = listareproduccion;
    }

    public Lista(int ID, String Nombre, String Descripcion, Usuario creador) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.creador = creador;
         this.subscriptores = new ArrayList<>();
        this.listareproduccion = new ArrayList<>();
    }
  public Lista(String Nombre, String Descripcion, Usuario creador) {
        this.ID = -1;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.creador = creador;
         this.subscriptores =  new ArrayList<>();
        this.listareproduccion = new ArrayList<>();
    }
    public Lista() {
        this(-1, "", "", null, null, null);
    }

    public List<Usuario> getSubscriptores() {
        List<Usuario> subscriptores = controlador.getSubscriberFromList(this.ID);
         if (!subscriptores.isEmpty()) {
            this.setSubscriptores(subscriptores);
        } else {
            subscriptores = new ArrayList<>();
            this.setSubscriptores(subscriptores);
        }
        return subscriptores;
    }

    public void setSubscriptores(List<Usuario> subscriptores) {
        this.subscriptores = subscriptores;
    }

    public List<Cancion> getListareproduccion() {
        List<Cancion> canciones = controlador.getAllSongsList(this.ID);
        if (!canciones.isEmpty()) {
            this.setListareproduccion(canciones);
        } else {
            canciones = new ArrayList<>();
            this.setListareproduccion(canciones);
        }
        return listareproduccion;
    }

    public void setListareproduccion(List<Cancion> listareproduccion) {
        this.listareproduccion = listareproduccion;
    }

    /**
     * Introduce una cancion al la lista
     *
     * @param c recibe una cancion
     */
    public void setCancionListareproduccion(Cancion c) {
        this.listareproduccion.add(c);
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
        boolean igual = false;
        if (obj != null) {
            if (this == obj) {
                igual = true;
            } else {
                if (obj instanceof Lista) {
                    Lista n = (Lista) obj;
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
        return "Lista{" + "ID=" + ID + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion + ", creador=" + creador + ", subscriptores=" + subscriptores + ", listareproduccion=" + listareproduccion + '}';
    }

}

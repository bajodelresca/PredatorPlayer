package model;

import controller.AppController;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jorge SB
 */
@Entity
@Table(name = "DISCO")
@NamedQueries({
    @NamedQuery(name="DiscoDAO.findAll",
                query="SELECT ID,Nombre,foto,fecha,creador FROM Disco"),
    @NamedQuery(name="DiscoDAO.findByID",
                query="SELECT ID,Nombre,foto,fecha,creador FROM Disco Where ID= :ID")
}) 
public class Disco implements Serializable {

    private static AppController controlador = AppController.getInstance();

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    protected int ID;

    @Column(name = "NOMBRE")
    protected String Nombre;

    @Column(name = "FOTO")
    protected String foto;

    @Column(name = "FECHA")
    protected Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDARTISTA")
    protected Artista creador;

    @OneToMany(mappedBy = "Album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Cancion> canciones;

    public Disco(int ID, String Nombre, String foto, Date fecha, Artista creador, List<Cancion> canciones) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.foto = foto;
        this.fecha = fecha;
        this.creador = creador;
        this.canciones = canciones;
    }

    public Disco(int ID, String Nombre, String foto, Date fecha, Artista creador) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.foto = foto;
        this.fecha = fecha;
        this.creador = creador;
    }

    public Disco(String Nombre, String foto, Date fecha, Artista creador) {
        this.ID = -1;
        this.Nombre = Nombre;
        this.foto = foto;
        this.fecha = fecha;
        this.creador = creador;
    }
    
    public Disco(Disco d) {
        this.ID = d.getID();
        this.Nombre = d.getNombre();
        this.foto = d.getFoto();
        this.fecha = d.getFecha();
        this.creador = d.getCreador();
    }

    public Disco() {
        this(-1, "", null, null, null, null);
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Artista getCreador() {
        return creador;
    }

    public void setCreador(Artista creador) {
        this.creador = creador;
    }

    public List<Cancion> getCanciones() {
        List<Cancion> canciones = controlador.getCanciones(this.ID);
        if (!canciones.isEmpty()) {
            this.setCanciones(canciones);
        } else {
            canciones = new ArrayList<>();
            this.setCanciones(canciones);
        }
        return this.canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
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
                if (obj instanceof Disco) {
                    Disco n = (Disco) obj;
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
        return "Disco{" + "ID=" + ID + ", Nombre=" + Nombre + ", foto=" + foto + ", fecha=" + fecha + ", creador=" + creador + '}';
    }

}

package controller;

import java.util.List;
import model.Artista;
import model.Cancion;
import model.Connection;
import model.Disco;
import model.Lista;
import model.Subscripcion;
import model.Usuario;

/**
 *
 * @author Alberto343
 */
public class AppController {

    public static Connection currentConnection;
    private static AppController instancia = null;
    private ArtistaController artistControl = null;
    private DiscoController diskControl = null;
    private CancionController songControl = null;
    private SubscripcionController subsControl = null;

    public AppController() {
        artistControl = ArtistaController.getInstance();
        diskControl = DiscoController.getInstance();
        songControl = CancionController.getInstance();
        subsControl = SubscripcionController.getInstance();
    }

    public static AppController getInstance() {
        instancia = new AppController();
        return instancia;
    }

    //___________________________________________________________________________Funciones de ARTISTA
    public List<Artista> getAllArtists() {
        return artistControl.getAllArtists();
    }

    public Artista getArtistsById(int id) {
        return artistControl.getArtistsById(id);
    }

    public boolean insertArtists(Artista a) {
        return artistControl.insertArtists(a);
    }

    public boolean editArtists(Artista a) {
        return artistControl.editArtists(a);
    }

    public boolean removeArtists(Artista a) {
        return artistControl.removeArtists(a);
    }

    public List<Disco> getRepertorio(int ID) {
        return artistControl.getRepertorio(ID);
    }

    //___________________________________________________________________________Funciones de DISCO
    public List<Disco> getAllDiscs() {
        return diskControl.getAllDiscs();
    }

    public Disco getDiscsById(int id) {
        return diskControl.getDiscsById(id);
    }

    public boolean insertDiscs(Disco a) {
        return diskControl.insertDiscs(a);
    }

    public boolean editDiscs(Disco a) {
        return diskControl.editDiscs(a);
    }

    public boolean removeDiscs(Disco a) {
        return diskControl.removeDiscs(a);
    }

    public List<Cancion> getCanciones(int ID) {
        return diskControl.getCanciones(ID);
    }

    //___________________________________________________________________________Funciones de CANCION
    public List<Cancion> getAllSongs() {
        return songControl.getAllSongs();
    }

    public Cancion getSongsById(int id) {
        return songControl.getSongsById(id);
    }

    public boolean insertSongs(Cancion a) {
        return songControl.insertSongs(a);
    }

    public boolean editSongs(Cancion a) {
        return songControl.editSongs(a);
    }

    public boolean removeSongs(Cancion a) {
        return songControl.removeSongs(a);
    }

    //___________________________________________________________________________Funciones de SUBSCRIPCION
    public List<Subscripcion> getAllSubs() {
        return subsControl.getAllSubs();
    }

    public boolean insertSongs(Subscripcion a) {
        return subsControl.insertSubs(a);
    }

    public boolean editSongs(Subscripcion a) {
        return subsControl.editSubs(a);
    }

    public boolean removeSongs(Subscripcion a) {
        return subsControl.removeSubs(a);
    }

    public List<Usuario> getSubscriberFromList(int id) {
        return subsControl.getSubscriberFromList(id);
    }

    public List<Lista> getListFromSubscriber(int id) {
        return subsControl.getListFromSubscriber(id);
    }
}

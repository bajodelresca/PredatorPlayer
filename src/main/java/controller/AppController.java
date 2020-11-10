package controller;

import java.util.List;
import model.Cancion;
import model.Connection;
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
    private CancionController songControl = null;
    private SubscripcionController subsControl = null;

    public AppController() {
        songControl = CancionController.getInstance();
        subsControl = SubscripcionController.getInstance();
    }

    public static AppController getInstance() {
        instancia = new AppController();
        return instancia;
    }

    /*Funciones de Cancion*/
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

    /*Funciones de Subscripcion*/
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

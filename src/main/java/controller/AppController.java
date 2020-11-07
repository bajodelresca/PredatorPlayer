/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public void insertSongs(Cancion a) {
        songControl.insertSongs(a);
    }

    public void editSongs(Cancion a) {
        songControl.editSongs(a);
    }

    public void removeSongs(Cancion a) {
        songControl.removeSongs(a);
    }

    /*Funciones de Subscripcion*/
    public List<Subscripcion> getAllSubs() {
        return subsControl.getAllSubs();
    }

    public void insertSongs(Subscripcion a) {
        subsControl.insertSubs(a);
    }

    public void editSongs(Subscripcion a) {
        subsControl.editSubs(a);
    }

    public void removeSongs(Subscripcion a) {
        subsControl.removeSubs(a);
    }

    public List<Usuario> getSubscriberFromList(int id) {
        return subsControl.getSubscriberFromList(id);
    }
    public List<Lista> getListFromSubscriber(int id) {
        return subsControl.getListFromSubscriber(id);
    }
}

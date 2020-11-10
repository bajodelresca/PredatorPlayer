/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ListaDAO;
import java.util.List;
import model.Cancion;
import model.Lista;

/**
 *
 * @author espin
 */
public class ListaController {
    private static ListaController instancia = null;

    public static ListaController getInstance() {
        instancia = new ListaController();
        return instancia;
    }

    public List<Lista> getAllList() {
        ListaDAO lDAO = new ListaDAO();
        return lDAO.getAll();
    }

    public Lista getSongsById(int id) {
        ListaDAO lDAO = new ListaDAO();
        return lDAO.getByID(id);
    }

    public void insertSongs(Lista a) {
        ListaDAO lDAO = new ListaDAO();
        lDAO.insert(a);
    }

    public void editSongs(Lista a) {
        ListaDAO lDAO = new ListaDAO();
        lDAO.edit(a);
    }
    public void removeSongs(Lista a) {
        ListaDAO lDAO = new ListaDAO();
        lDAO.remove(a);
    }
     public List<Cancion> getAllSongsList(int id) {
        ListaDAO lDAO = new ListaDAO();
        return lDAO.getCancionFromList(id);
    }
     public void insertListCanc(Lista a, Cancion c) {
        ListaDAO lDAO = new ListaDAO();
        lDAO.insertListCanc(a,c);
    }
    
}

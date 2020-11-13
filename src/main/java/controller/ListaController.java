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

    public Lista getListById(int id) {
        ListaDAO lDAO = new ListaDAO();
        return lDAO.getByID(id);
    }

    public boolean insertList(Lista a) {
        boolean result = false;
        if (a != null) {
            ListaDAO lDAO = new ListaDAO();
            lDAO.insert(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean editList(Lista a) {
        boolean result = false;
        if (a != null) {
            ListaDAO lDAO = new ListaDAO();
            lDAO.edit(a);
             result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean removeList(Lista a) {
        boolean result = false;
        if (a != null) {
            ListaDAO lDAO = new ListaDAO();
            lDAO.remove(a);
             result = true;
        } else {
            result = false;
        }
        return result;
    }

    public List<Cancion> getAllSongsList(int id) {
        ListaDAO lDAO = new ListaDAO();
        return lDAO.getCancionFromList(id);
    }

    public boolean insertListCanc(int a, int c) {
        ListaDAO lDAO = new ListaDAO();
        return lDAO.insertListCanc(a, c);
    }
    public boolean searchListByID(int id) {
        ListaDAO lDAO = new ListaDAO();
        return lDAO.searchByID(id);
    }
}
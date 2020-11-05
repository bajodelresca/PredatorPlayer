/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CancionDAO;
import java.util.List;
import model.Cancion;

/**
 *
 * @author Alberto343
 */
public class CancionController {

    private static CancionController instancia = null;

    public static CancionController getInstance() {
        instancia = new CancionController();
        return instancia;
    }

    public List<Cancion> getAllSongs() {
        CancionDAO cDAO = new CancionDAO();
        return cDAO.getAll();
    }

    public Cancion getSongsById(int id) {
        CancionDAO cDAO = new CancionDAO();
        return cDAO.getByID(id);
    }

    public void insertSongs(Cancion a) {
        CancionDAO cDAO = new CancionDAO();
        cDAO.insert(a);
    }

    public void editSongs(Cancion a) {
        CancionDAO cDAO = new CancionDAO();
        cDAO.edit(a);
    }
    public void removeSongs(Cancion a) {
        CancionDAO cDAO = new CancionDAO();
        cDAO.remove(a);
    }
}

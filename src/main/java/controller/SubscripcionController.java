/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SubscripcionDAO;
import java.util.List;
import model.Subscripcion;

/**
 *
 * @author Alberto343
 */
public class SubscripcionController {

    private static SubscripcionController instancia = null;

    public static SubscripcionController getInstance() {
        instancia = new SubscripcionController();
        return instancia;
    }

    public List<Subscripcion> getAllSubs() {
        SubscripcionDAO cDAO = new SubscripcionDAO();
        return cDAO.getAll();
    }

    public void insertSubs(Subscripcion a) {
        SubscripcionDAO cDAO = new SubscripcionDAO();
        cDAO.insert(a);
    }

    public void editSubs(Subscripcion a) {
        SubscripcionDAO cDAO = new SubscripcionDAO();
        cDAO.edit(a);
    }

    public void removeSubs(Subscripcion a) {
        SubscripcionDAO cDAO = new SubscripcionDAO();
        cDAO.remove(a);
    }
}

package controller;

import DAO.DiscoDAO;
import java.util.List;
import model.Cancion;
import model.Disco;

/**
 *
 * @author Jorge SB
 */
public class DiscoController {

    private static DiscoController instancia = null;

    public static DiscoController getInstance() {
        instancia = new DiscoController();
        return instancia;
    }

    public List<Disco> getAllDiscs() {
        DiscoDAO dDAO = new DiscoDAO();
        return dDAO.getAll();
    }

    public Disco getDiscsById(int id) {
        DiscoDAO dDAO = new DiscoDAO();
        return dDAO.getByID(id);
    }

    public boolean insertDiscs(Disco a) {
        boolean result = false;
        if (a != null) {
            DiscoDAO dDAO = new DiscoDAO();
            dDAO.insert(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean editDiscs(Disco a) {
        boolean result = false;
        if (a != null) {
            DiscoDAO dDAO = new DiscoDAO();
            dDAO.edit(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean removeDiscs(Disco a) {
        boolean result = false;
        if (a != null) {
            DiscoDAO dDAO = new DiscoDAO();
            dDAO.remove(a);
        } else {
            result = false;
        }
        return result;
    }

    public List<Cancion> getCanciones(int ID) {
        DiscoDAO dDAO = new DiscoDAO();
        return dDAO.getListCanciones(ID);
    }
}

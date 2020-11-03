/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.ConnectionUtils;
import controller.CancionController;
import controller.SubscripcionController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cancion;
import model.Disco;

/**
 *
 * @author Alberto343
 */
public class CancionDAO extends Cancion implements DAO<Cancion> {

    enum queries {
        //INSERT("INSERT INTO subscripcion (IDLista,IDUsuario) VALUES (NULL,NULL)"),
       // DELETE("DELETE FROM subscripcion WHERE IDLista=? AND IDUsuario=?"),
        GETALL("SELECT * FROM cancion");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    Connection conn;

    public CancionDAO(int ID, String Nombre, int Duracion, Disco Album) {
        super(ID, Nombre, Duracion, Album);
        try {
            conn = ConnectionUtils.connect(CancionController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CancionDAO() {
        super();
          try {
            conn = ConnectionUtils.connect(CancionController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public CancionDAO(Cancion c) {
        super(c.getID(),c.getNombre(),c.getDuracion(),c.getAlbum());
        try {
            conn = ConnectionUtils.connect(SubscripcionController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void insert(Cancion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Cancion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Cancion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cancion> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

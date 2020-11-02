/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.ConnectionUtils;
import controller.ComentaController;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comenta;

/**
 *
 * @author Alberto343
 */
public class ComentaDAO extends Comenta implements DAO<Comenta> {

    enum queries {
        INSERT("INSERT INTO comenta (ID,IDUsuario,IDLista,Mensaje,Instante) VALUES (NULL,?,?,?,?)"),
        GETALL("SELECT * FROM comenta");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    Connection conn;

    public ComentaDAO(int ID, model.Usuario Usuario, model.Lista Lista, String Mensaje, Timestamp Instante) {
        super(ID, Usuario, Lista, Mensaje, Instante);
        try {
            conn = ConnectionUtils.connect(ComentaController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ComentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ComentaDAO() {
        super();
        try {
            conn = ConnectionUtils.connect(ComentaController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ComentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ComentaDAO(Comenta c) {
        super(c.getID(),c.getUsuario(),c.getLista(),c.getMensaje(),c.getInstante());
        try {
            conn = ConnectionUtils.connect(ComentaController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ComentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

    @Override
    public void insert(Comenta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Comenta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Comenta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comenta> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

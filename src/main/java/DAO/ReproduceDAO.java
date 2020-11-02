package DAO;

import Utils.ConnectionUtils;
import controller.ReproduceController;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Reproduce;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alberto343
 */
public class ReproduceDAO extends Reproduce implements DAO<Reproduce> {

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

    public ReproduceDAO(int ID, model.Usuario Usuario, model.Cancion Cancion, Timestamp Instante) {
        super(ID, Usuario, Cancion, Instante);
        try {
            conn = ConnectionUtils.connect(ReproduceController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReproduceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReproduceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ReproduceDAO() {
         super();
        try {
            conn = ConnectionUtils.connect(ReproduceController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReproduceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReproduceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ReproduceDAO(Reproduce r) {
         super(r.getID(),r.getUsuario(),r.getCancion(),r.getInstante());
        try {
            conn = ConnectionUtils.connect(ReproduceController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReproduceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReproduceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Reproduce a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Reproduce a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Reproduce a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reproduce> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

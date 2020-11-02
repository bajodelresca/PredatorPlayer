/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.ConnectionUtils;
import controller.SubscripcionController;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Subscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Lista;
import model.Usuario;

/**
 *
 * @author Alberto343
 */
public class SubscripcionDAO extends Subscripcion implements DAO<Subscripcion> {

    enum queries {
        INSERT("INSERT INTO subscripcion (IDLista,IDUsuario) VALUES (NULL,NULL)"),
        GETALL("SELECT * FROM subscripcion");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    Connection conn;

    public SubscripcionDAO(model.Lista Lista, model.Usuario Usuario) {
        super(Lista, Usuario);
        try {
            conn = ConnectionUtils.connect(SubscripcionController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SubscripcionDAO() {
        super();
          try {
            conn = ConnectionUtils.connect(SubscripcionController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SubscripcionDAO(Subscripcion sb) {
        super(sb.getLista(), sb.getUsuario());
        try {
            conn = ConnectionUtils.connect(SubscripcionController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Subscripcion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Subscripcion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Subscripcion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Metodo que convierte un ResultSet en Subscripcion
     * @param rs Recibe un ResultSet
     * @return Devuelve una Subscripcion
     * @throws SQLException lanza una SQLException
     */
    private Subscripcion convert(ResultSet rs) throws SQLException {
        ListaDAO lDAO = new ListaDAO();
        UsuarioDAO uDAO = new UsuarioDAO();
        int IDLista = rs.getInt("IDLista");
        int IDUsuario = rs.getInt("IDUsuario");
        Lista l = lDAO.getByID(IDLista);
        Usuario u = uDAO.getByID(IDUsuario);
        Subscripcion sb = new Subscripcion(l, u);
        return sb;
    }

    @Override
    public List<Subscripcion> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Subscripcion> listS = new ArrayList<>();
        try {
            stat = conn.prepareStatement(queries.GETALL.getQ());
            rs = stat.executeQuery();
            while (rs.next()) {
                listS.add(convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listS;
    }

}

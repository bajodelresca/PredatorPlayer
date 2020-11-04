/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.ConnectionUtils;
import controller.AppController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        INSERT("INSERT INTO cancion (ID, Nombre, Duracion, IDGenero, IDDisco) VALUES (?,?,?,NULL,?)"),
        UPDATE("UPDATE cancion SET Nombre=?,Duracion=?,IDGenero=?,IDDisco=? WHERE ID=?"),
        // DELETE("DELETE FROM subscripcion WHERE IDLista=? AND IDUsuario=?"),
        GETBYID("SELECT ID,Nombre,Duracion,IDDisco FROM cancion Where ID=?"),
        GETALL("SELECT  ID,Nombre,Duracion,IDDisco FROM cancion");
        
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
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CancionDAO() {
        super();
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CancionDAO(Cancion c) {
        super(c.getID(), c.getNombre(), c.getDuracion(), c.getAlbum());
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Cancion a) {
        PreparedStatement stat=null;
        try{
          //  if(this.getID()>0)
        stat=conn.prepareStatement(queries.GETALL.getQ());
        stat.setInt(1, a.getID());
        stat.setString(2,a.getNombre());
        stat.setInt(3, a.getDuracion());
        stat.setInt(5, a.getAlbum().getID());
        }catch (SQLException ex) {
            Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    

    @Override
    public void edit(Cancion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Cancion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que convierte un ResultSet en Cancion
     *
     * @param rs Recibe un ResultSet
     * @return Devuelve una Subscripcion
     * @throws SQLException lanza una SQLException
     */
    private Cancion convert(ResultSet rs) throws SQLException {
        DiscoDAO dDAO=new DiscoDAO();
        int id=rs.getInt("ID");
        String nombre=rs.getString("Nombre");
        int duracion=rs.getInt("Duracion");
      //  int idGenero=rs.getInt("IDGenero");
        int idDisco=rs.getInt("IDDisco");
        Disco album=dDAO.getByID(idDisco);
        Cancion c=new Cancion(id, nombre, duracion,album);
        return c;
    }

    @Override
    public List<Cancion> getAll() {
       PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cancion> listS = new ArrayList<>();
        try {
            stat = conn.prepareStatement(queries.GETALL.getQ());
            rs = stat.executeQuery();
            while (rs.next()) {
                listS.add(convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listS;
    }
     /**
     * Metodo que devuelve una Cancion por id pasado
     * @param id identificador de cada Cancion
     * @return Devuelve una Cancion
     */
    public Cancion getByID(int id) {
         PreparedStatement stat = null;
        ResultSet rs = null;
        Cancion c = new Cancion();
        try {
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                c=convert(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return c;
    }
}

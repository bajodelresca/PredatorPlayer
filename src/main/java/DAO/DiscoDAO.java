package DAO;

import Utils.ConnectionUtils;
import controller.AppController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Disco;

/**
 *
 * @author Jorge SB
 */
public class DiscoDAO extends Disco implements DAO<Disco> {
    
    enum queries {
        INSERT("INSERT INTO disco (ID, Nombre, Nacionalidad, Foto, Fecha, IDArtista) VALUES (?,?,?,?)"),
        UPDATE("UPDATE disco SET Nombre=?,Nacionalidad=?,Foto=?,Fecha=?,IDArtista=? WHERE ID=?"),
        DELETE("DELETE FROM disco WHERE ID=?"),
        GETBYID("SELECT * FROM Disco WHERE ID=?"),
        GETALL("SELECT * FROM Disco");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    Connection conn;
    
    
    
    
    
    
    

//______________________________________________________________________________CRUD
    
    @Override
    public void insert(Disco a) {
        
    }

    @Override
    public void edit(Disco a) {
        
    }

    @Override
    public void remove(Disco a) {
        
    }
    
    @Override
    public List<Disco> getAll() {
        List<Disco> listD=new ArrayList<>();
        return listD;
    }
    
    /**
     * Metodo que devuelve un Disco por id pasado
     * @param id identificador de cada Disco
     * @return Devuelve un Disco
     */
    public Disco getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

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
import model.Artista;
import model.Disco;

/**
 *
 * @author Jorge SB
 */
public class ArtistaDAO extends Artista implements DAO<Artista> {

    enum queries {
        INSERT("INSERT INTO artista (ID, Nombre, Nacionalidad, Foto) VALUES (NULL,?,?,?)"),
        UPDATE("UPDATE artista SET Nombre=?,Nacionalidad=?,Foto=? WHERE ID=?"),
        DELETE("DELETE FROM artista WHERE ID=?"),
        GETBYID("SELECT ID,Nombre,Nacionalidad,Foto FROM artista Where ID=?"),
        GETALL("SELECT ID,Nombre,Nacionalidad,Foto FROM artista"),
        GETDISCOLISTBYID("SELECT d.ID, d.Nombre, d.Foto, d.fechap, d.IDArtista FROM disco as d INNER JOIN artista as art on art.ID=d.IDArtista WHERE art.ID=?");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    Connection conn;

    public ArtistaDAO(int ID, String Nombre, String Nacionalidad, String Foto) {
        super(ID, Nombre, Nacionalidad, Foto);
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArtistaDAO() {
        super();
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArtistaDAO(Artista a) {
        super(a.getID(), a.getNombre(), a.getNacionalidad(), a.getFoto());
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//______________________________________________________________________________CRUD
    @Override
    public void insert(Artista a) {
        int result = -1;
        try {
            conn = ConnectionUtils.getConnection();
            if (this.ID > 0) {
                edit(a);
            } else {
                PreparedStatement stat = conn.prepareStatement(queries.INSERT.getQ(), Statement.RETURN_GENERATED_KEYS);
                stat.setString(1, a.getNombre());
                stat.setString(2, a.getNacionalidad());
                stat.setString(3, a.getFoto());

                stat.executeUpdate();
                try ( ResultSet generatedKeys = stat.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);
                    }
                }
                this.ID = result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(Artista a) {
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement stat = conn.prepareStatement(queries.UPDATE.getQ());
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getNacionalidad());
            stat.setString(3, a.getFoto());
            stat.setInt(4, a.getID());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Artista a) {
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(queries.DELETE.getQ());
            ps.setInt(1, a.getID());

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se ha insertado correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Artista convert(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        String Nombre = rs.getString("Nombre");
        String Nacionalidad = rs.getString("Nacionalidad");
        String Foto = rs.getString("Foto");
        Artista a = new Artista(id, Nombre, Nacionalidad, Foto);
        return a;
    }

    @Override
    public List<Artista> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Artista> listA = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETALL.getQ());
            rs = stat.executeQuery();
            while (rs.next()) {
                listA.add(convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listA;
    }

    public Artista getByID(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Artista a = new Artista();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convert(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return a;
    }

    /**
     * Recibe el id de un disco y devuelve todas sus canciones
     *
     * @param id
     * @return canciones
     */
    public List<Disco> getListRepertorio(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        DiscoDAO DiDAO = new DiscoDAO();
        List<Disco> repertorio = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETDISCOLISTBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                repertorio.add(DiDAO.convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return repertorio;
    }
}

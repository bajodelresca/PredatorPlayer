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
import javax.persistence.EntityManager;
import model.Cancion;
import model.Lista;
import model.Usuario;

/**
 *
 * @author Alberto343
 */
public class ListaDAO extends Lista implements DAO<Lista> {

    enum queries {
        INSERT("INSERT INTO Lista (ID,Nombre,Descripcion,IDUsuario) VALUES(NULL,?,?,?)"),
        INSERTLISTACANCION("INSERT INTO listacancion (IDLista,IDCancion) VALUES(?,?)"),
        UPDATE("UPDATE Lista SET Nombre = ?, Descripcion = ?, IDUsuario = ? WHERE ID = ?"),
        DELETE("DELETE FROM Lista WHERE ID=?"),
        DELETESONGLIST("DELETE FROM Listacancion WHERE IDCancion=?"),
        GETBYID("SELECT * FROM Lista WHERE ID=?"),
        GETLCBYID("SELECT * FROM ListaCancion WHERE IDLista=? && IDCancion=?"),
        GETALL("SELECT * FROM Lista"),
        GETCANCLISTBYID("SELECT ID, Nombre, Duracion, IDGenero, IDDisco FROM cancion as c INNER JOIN listacancion as list on list.IDCancion=c.ID WHERE list.IDLista=?"),
        GETLISTFROMUSER("SELECT * FROM Lista WHERE IDUsuario = ?");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    Connection conn;

    public ListaDAO(int ID, String Nombre, String Descripcion, Usuario creador) {
        super(ID, Nombre, Descripcion, creador);
    }

    public ListaDAO() {
        super();
    }

    public ListaDAO(Lista c) {
        super(c.getID(), c.getNombre(), c.getDescripcion(), c.getCreador());
    }

    @Override
    public void insert(Lista a) {
        EntityManager manager = ConnectionUtils.getManager();
        manager.getTransaction().begin();
        manager.persist(a);
        manager.getTransaction().commit();
        ConnectionUtils.closeManager(manager);

    }

    @Override
    public void edit(Lista a) {
        EntityManager manager = ConnectionUtils.getManager();
        manager.getTransaction().begin();
        manager.merge(a);
        manager.getTransaction().commit();
        ConnectionUtils.closeManager(manager);
    }

    @Override
    public void remove(Lista a) {
        EntityManager manager = ConnectionUtils.getManager();
        manager.getTransaction().begin();
        manager.remove(a);
        manager.getTransaction().commit();
        ConnectionUtils.closeManager(manager);
    }

    @Override
    public List<Lista> getAll() {
        EntityManager manager = ConnectionUtils.getManager();
        manager.getTransaction().begin();
        List<Lista> listas = manager.createQuery("FROM LISTA").getResultList();
        manager.getTransaction().commit();
        ConnectionUtils.closeManager(manager);
        return listas;
    }

    /**
     * Metodo que devuelve una lista por id pasado
     *
     * @param id identificador de cada Lista
     * @return Devuelve una Lista
     */
    public Lista getByID(int id) {
        EntityManager manager = ConnectionUtils.getManager();
        manager.getTransaction().begin();

        Lista l = manager.find(Lista.class, id);

        manager.getTransaction().commit();
        ConnectionUtils.closeManager(manager);
        return l;
    }

    public List<Cancion> getCancionFromList(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        CancionDAO lDAO = new CancionDAO();
        List<Cancion> listS = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETCANCLISTBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                listS.add(lDAO.convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return listS;
    }

    public boolean insertListCanc(int a, int c) {
        boolean result = false;
        CancionDAO cDAO = new CancionDAO();
        try {
            PreparedStatement stat = null;
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.INSERTLISTACANCION.getQ());
            if (this.getByID(a) != null && cDAO.getByID(c) != null) {
                stat.setInt(1, a);

                stat.setInt(2, c);

                stat.executeUpdate();
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Metodo que comprueba si existe el ID en la tabla
     *
     * @param id recibe un entero
     * @return devuelve un boolean, si existe devuelve true y false si no
     */
    public boolean searchByID(int id) {
        boolean result = false;
        EntityManager manager = ConnectionUtils.getManager();
        manager.getTransaction().begin();

        Lista l = manager.find(Lista.class, id);
        if (l != null) {
            result = true;
        } else {
            result = false;
        }
        manager.getTransaction().commit();
        ConnectionUtils.closeManager(manager);
        return result;

    }

    public boolean searchListaCanByID(int a, int c) {
        boolean result = false;
        EntityManager manager = ConnectionUtils.getManager();
        manager.getTransaction().begin();
        
        Cancion ca = manager.find(Cancion.class, a);
        Lista li = manager.find(Lista.class, c);
        if (ca != null && li != null) {
            result = true;
        } else {
            result = false;
        }
        manager.getTransaction().commit();
        ConnectionUtils.closeManager(manager);
        return result;

    }

    public void removeSongList(Cancion a) {
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(queries.DELETESONGLIST.getQ());
            ps.setInt(1, a.getID());

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se ha borrado correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Recibe el ID del usuario y devuelve Las listas que ha creado
     *
     * @param id
     * @return listUser
     */
    public List<Lista> getListFromUser(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Lista> listUser = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETLISTFROMUSER.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                listUser.add(convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return listUser;
    }
}

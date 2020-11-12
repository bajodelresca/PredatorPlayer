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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        GETBYID("SELECT * FROM Lista WHERE ID=?"),
        GETALL("SELECT * FROM Lista"),
        GETCANCLISTBYID("SELECT ID, Nombre, Duracion, IDGenero, IDDisco FROM cancion as c INNER JOIN listacancion as list on list.IDCancion=c.ID WHERE list.IDLista=?");

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
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ListaDAO() {
        super();

        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ListaDAO(Lista c) {
        super(c.getID(), c.getNombre(), c.getDescripcion(), c.getCreador());
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Lista a) {
        int result = -1;
        try {
            conn = ConnectionUtils.getConnection();
            if (this.ID > 0) {
                edit(a);
            } else {
                PreparedStatement stat = conn.prepareStatement(queries.INSERT.getQ(), Statement.RETURN_GENERATED_KEYS);
                stat.setString(1, a.getNombre());
                stat.setString(2, a.getDescripcion());
                stat.setInt(3, a.getCreador().getID());

                stat.executeUpdate();
                try ( ResultSet generatedKeys = stat.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);
                    }
                }
                this.ID = result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void edit(Lista a) {
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement stat = conn.prepareStatement(queries.UPDATE.getQ());
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getDescripcion());
            stat.setInt(3, a.getCreador().getID());
            stat.setInt(4, a.getID());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Lista a) {
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(queries.DELETE.getQ());
            ps.setInt(1, a.getID());

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se Ha insertado correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    protected Lista convert(ResultSet rs) throws SQLException {
        UsuarioDAO UDAO = new UsuarioDAO();
        int id = rs.getInt("ID");
        String nombre = rs.getString("Nombre");
        String descripcion = rs.getString("Descripcion");
        int idUsuario = rs.getInt("IDUsuario");
        Usuario crea = UDAO.getByID(idUsuario);
        Lista c = new Lista(id, nombre, descripcion, crea);
        return c;
    }

    @Override
    public List<Lista> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Lista> listS = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETALL.getQ());
            rs = stat.executeQuery();
            while (rs.next()) {
                listS.add(convert(rs));
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

    /**
     * Metodo que devuelve una lista por id pasado
     *
     * @param id identificador de cada Lista
     * @return Devuelve una Lista
     */
    public Lista getByID(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Lista c = new Lista();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                c = convert(rs);
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
        return c;
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
                result=true;
            } else {
                result=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}

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
import model.Usuario;

/**
 *
 * @author Alberto343
 */
public class UsuarioDAO extends Usuario implements DAO<Usuario> {

    enum queries {
        INSERT("INSERT INTO Usuario (ID, Correo, Nombre, Foto) VALUES (NULL,?,?,?)"),
        UPDATE("UPDATE Usuario SET Correo=?,Nombre=?,Foto=? WHERE ID=?"),
        DELETE("DELETE FROM Usuario WHERE ID=?"),
        GETBYID("SELECT * FROM Usuario Where ID=?"),
        GETALL("SELECT * FROM Lista");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }

    public UsuarioDAO(int ID, String Nombre, String Correo, String Foto) {
        super(ID, Nombre, Correo, Foto);

        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public UsuarioDAO() {
        super();
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UsuarioDAO(Usuario c) {
        super(c.getID(), c.getNombre(), c.getCorreo(), c.getFoto());
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Connection conn;

    @Override
    public void insert(Usuario a) {
        int result = -1;
        try {
            conn = ConnectionUtils.getConnection();
            
            if (this.ID > 0) {
                edit(a);
            } else {
                PreparedStatement stat = conn.prepareStatement(queries.INSERT.getQ(), Statement.RETURN_GENERATED_KEYS);
                stat.setString(1, a.getCorreo());
                stat.setString(2, a.getNombre());
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
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(Usuario a) {
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement stat = conn.prepareStatement(queries.UPDATE.getQ());
            stat.setString(1, a.getCorreo());
            stat.setString(2, a.getNombre());
            stat.setString(3, a.getFoto());
            stat.setInt(5, a.getID());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Usuario a) {
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(queries.DELETE.getQ());
            ps.setInt(1, a.getID());

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se ha borrado correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    protected Usuario convert(ResultSet rs) throws SQLException {
      //  DiscoDAO dDAO = new DiscoDAO();
        int id = rs.getInt("ID");
        String nombre = rs.getString("Nombre");
        String correo = rs.getString("Correo");
        String foto = rs.getString("Foto");
        Usuario c = new Usuario(id, nombre, correo, foto);
        return c;
    }

    @Override
    public List<Usuario> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Usuario> listS = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETALL.getQ());
            rs = stat.executeQuery();
            while (rs.next()) {
                listS.add(convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listS;
    }

    /**
     * Metodo que devuelve un usuario por id pasado
     *
     * @param id identificador de cada usuario
     * @return Devuelve un usuario
     */
    public Usuario getByID(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Usuario c = new Usuario();
        try {
             conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                c = convert(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return c;
    }
    /**
     * Metodo que comprueba si existe el ID en la tabla
     * @param id recibe un entero
     * @return devuelve un boolean, si existe devuelve true y false si no
     */
    public boolean searchByID(int id) {
        boolean result = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                Usuario c = convert(rs);
                if (c.getID() != -1) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
}

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
		DELETE("DELETE FROM Lista WHERE ID=?"), DELETESONGLIST("DELETE FROM Listacancion WHERE IDCancion=?"),
		GETBYID("SELECT * FROM Lista WHERE ID=?"),
		GETLCBYID("SELECT * FROM ListaCancion WHERE IDLista=? && IDCancion=?"), GETALL("SELECT * FROM Lista"),
		GETCANCLISTBYID(
				"SELECT ID, Nombre, Duracion, IDGenero, IDDisco FROM cancion as c INNER JOIN listacancion as list on list.IDCancion=c.ID WHERE list.IDLista=?"),
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
		

	}

	@Override
	public void edit(Lista a) {
		
	}

	@Override
	public void remove(Lista a) {
		
	}


	@Override
	public List<Lista> getAll() {
	
		return null;
	}

	/**
	 * Metodo que devuelve una lista por id pasado
	 *
	 * @param id identificador de cada Lista
	 * @return Devuelve una Lista
	 */
	public Lista getByID(int id) {
		
		return null;
	}

	public List<Cancion> getCancionFromList(int id) {
		
		return null;
	}

	public boolean insertListCanc(int a, int c) {
		
		return false;
	}

	/**
	 * Metodo que comprueba si existe el ID en la tabla
	 * 
	 * @param id recibe un entero
	 * @return devuelve un boolean, si existe devuelve true y false si no
	 */
	public boolean searchByID(int id) {
		boolean result = false;
		
		return result;
	}

	public boolean searchListaCanByID(int a, int c) {
		boolean result = false;
		
		return result;
	}

	public void removeSongList(Cancion a) {
		
	}

	/**
	 * Recibe el ID del usuario y devuelve Las listas que ha creado
	 * 
	 * @param id
	 * @return listUser
	 */
	public List<Lista> getListFromUser(int id) {
		PreparedStatement stat = null;
		

		return null;
	}
	/*
    enum queries {
        INSERT("INSERT INTO subscripcion (IDLista,IDUsuario) VALUES (?,?)"),
        DELETE("DELETE FROM subscripcion WHERE IDLista=? AND IDUsuario=?"),
        UPDATE("UPDATE subscripcion SET IDLista=?,IDUsuario=?"),
        GETALL("SELECT * FROM subscripcion"),
        GETUSERSUBSBYID("SELECT ID, Correo, Nombre, Foto FROM usuario as u INNER JOIN subscripcion as sub on sub.IDUsuario=u.ID WHERE sub.IDLista=?"),
        GETLISTSUBSBYID("SELECT l.ID, l.Nombre, l.Descripcion, l.IDUsuario FROM lista as l INNER JOIN subscripcion as sub on sub.IDLista=l.ID WHERE sub.IDUsuario=?")
        ;

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
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SubscripcionDAO() {
        super();
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SubscripcionDAO(Subscripcion sb) {
        super(sb.getLista(), sb.getUsuario());
        try {
            conn = ConnectionUtils.connect(AppController.currentConnection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Subscripcion a) {
        try {
            conn = ConnectionUtils.getConnection();
         
                PreparedStatement stat = conn.prepareStatement(queries.INSERT.getQ());
                stat.setInt(1, a.getLista().getID());
                stat.setInt(2, a.getUsuario().getID());
                stat.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(Subscripcion a) {
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement stat = conn.prepareStatement(queries.UPDATE.getQ());
            stat.setInt(1, a.getLista().getID());
            stat.setInt(2, a.getUsuario().getID());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Subscripcion a) {
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(queries.DELETE.getQ());
            ps.setInt(1, a.getLista().getID());
            ps.setInt(2, a.getUsuario().getID());

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se ha borrado correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Metodo que convierte un ResultSet en Subscripcion
     *
     * @param rs Recibe un ResultSet
     * @return Devuelve una Subscripcion
     * @throws SQLException lanza una SQLException
     
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
            conn = ConnectionUtils.getConnection();
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
     
    public  List<Usuario> getSubscriberFromList(int id) {
         PreparedStatement stat = null;
        ResultSet rs = null;
        UsuarioDAO uDAO=new UsuarioDAO();
        List<Usuario> listS = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETUSERSUBSBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                listS.add(uDAO.convert(rs)) ;
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
   
    public  List<Lista> getListFromSubscriber(int id) {
         PreparedStatement stat = null;
        ResultSet rs = null;
        ListaDAO lDAO=new ListaDAO();
        List<Lista> listS = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETLISTSUBSBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                listS.add(lDAO.convert(rs)) ;
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
     
	  */
}

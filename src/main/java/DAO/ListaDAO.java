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
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Artista;
import model.Cancion;
import model.Disco;
import model.Lista;
import model.Usuario;

/**
 *
 * @author Alberto343
 */
//@MappedSuperclass
public class ListaDAO extends Lista implements DAO<Lista> {

	private final static String findAll = "Lista.findAll";
	private final static String findByID = "Lista.findByID";
	private final static String findCancByIDList = "SELECT c.* FROM Cancion as c INNER JOIN listacancion as l on FK_CANCION=c.ID WHERE FK_LISTA= ?";
	private final static String getListFromUser = "Lista.getListFromUser";
	private final static String insertCanInList = "INSERT INTO listacancion (IDLista,IDCancion) VALUES(?,?)";
	private final static String removeSongfromList = "DELETE FROM listacancion WHERE FK_CANCION=?";

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

	public ListaDAO(int id) {
		super(getByID(id));
	}

	public ListaDAO() {
		super();
	}

	public ListaDAO(Lista c) {
		super(c.getID(), c.getNombre(), c.getDescripcion(), c.getCreador());
	}

	public void setSubscriptores(List<Usuario> subscriptores) {
		this.subscriptores = subscriptores;
		for (Usuario subscrito : subscriptores) {
			subscrito.getListasubscrito().add((Lista) this);
		}
	}

	public void setListareproduccion(List<Cancion> listareproduccion) {
		this.listareproduccion = listareproduccion;

	}

	/**
	 * Introduce una cancion a la lista
	 *
	 * @param c recibe una cancion
	 */
	public void setCancionListareproduccion(Cancion c) {
		if (listareproduccion == null) {
			listareproduccion = new ArrayList<Cancion>();
		}
		if (!listareproduccion.contains(c)) {
			listareproduccion.add(c);
		}
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
		manager.remove(manager.contains(a) ? a : manager.merge(a));
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public List<Lista> getAll() {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		TypedQuery q = manager.createNamedQuery(findAll, Lista.class);
		List<Lista> listas = q.getResultList();
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
	private static Lista getByID(int id) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		TypedQuery q = manager.createNamedQuery(findByID, Lista.class);
		q.setParameter("ID", id);

		Lista l = (Lista) q.getSingleResult();

		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return l;
	}

	public List<Cancion> getCancionFromList(int id) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();

		Query q = manager.createNativeQuery(findCancByIDList, Cancion.class);
		q.setParameter(1, id);
		List<Cancion> canciones = q.getResultList();
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return canciones;

	}

	public boolean insertListCanc(int a, int c) {

		boolean result = false;
		CancionDAO cDAO = new CancionDAO(c);

		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		Cancion ca = new Cancion(cDAO);

		if (this.getByID(a) != null && ca != null) {
			Query q = manager.createNativeQuery(insertCanInList, Cancion.class);
			q.setParameter(1, a);
			q.setParameter(2, c);
			manager.getTransaction().commit();
			ConnectionUtils.closeManager(manager);
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	/*
	 * Metodo que comprueba si existe el ID en la tabla
	 *
	 * @param id recibe un entero
	 * 
	 * @return devuelve un boolean, si existe devuelve true y false si no
	 */
	public boolean searchByID(int id) {
		boolean result = false;
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		Lista c = getByID(id);
		if (c != null) {
			result = true;
		} else {
			result = false;
		}
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return result;
	}
	
	/*
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
    */
	
	/**
	 * Recibe una canción y la borra de una lista de reproducción
	 * @param Canción a
	 */
    public void removeSongList(Cancion a) {
    	EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		Query q = manager.createNativeQuery(removeSongfromList, Cancion.class);
		q.setParameter(1, a.getID());
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
    }

    /**
     * Recibe el ID del usuario y devuelve Las listas que ha creado
     *
     * @param id
     * @return listUser
     */
    public List<Lista> getListFromUser(int id) {
    	EntityManager manager = ConnectionUtils.getManager();
        manager.getTransaction().begin();
        TypedQuery q = manager.createNamedQuery(getListFromUser, Lista.class);
        q.setParameter("ID", id);

        List<Lista> listas = q.getResultList();
        manager.getTransaction().commit();
        ConnectionUtils.closeManager(manager);
        return listas;
    }

}

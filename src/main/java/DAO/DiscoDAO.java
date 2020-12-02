package DAO;

import Utils.ConnectionUtils;
import controller.AppController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import model.Artista;
import model.Cancion;
import model.Disco;

/**
 *
 * @author Jorge SB
 */

@NamedQueries({
    @NamedQuery(name="DiscoDAO.findAll",
                query="SELECT * FROM Disco"),
    @NamedQuery(name="DiscoDAO.findByID",
                query="SELECT * FROM Disco Where ID= :ID")
}) 
public class DiscoDAO extends Disco implements DAO<Disco> {
	
	private final static String findAll = "DiscoDAO.findAll";
	private final static String findByID = "DiscoDAO.findByID";

	public DiscoDAO(int ID, String Nombre, String foto, Date fecha, Artista creador) {
		super(ID, Nombre, foto, fecha, creador);
	}

	public DiscoDAO() {
		super();
	}

	public DiscoDAO(Disco d) {
		super(d.getID(), d.getNombre(), d.getFoto(), d.getFecha(), d.getCreador());
	}
	
	public DiscoDAO(int id) {
		super(getByID(id));
	}

//______________________________________________________________________________CRUD
	@Override
	public void insert(Disco a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.persist(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public void edit(Disco a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.merge(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public void remove(Disco a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.remove(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public List<Disco> getAll() {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(findAll);
		List<Disco> discos =  q.getResultList();
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return discos;
	}

	public Disco getByID(int id) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();

		Query q = manager.createNamedQuery(findByID);
		q.setParameter(1, id);
		Disco d = (Disco) q.getSingleResult();
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return d;
	}

	/**
	 * Recibe el id de un disco y devuelve todas sus canciones
	 *
	 * @param id
	 * @return canciones
	 */
	public List<Cancion> getListCanciones(int id) {
		// PREGUNTAR
		return canciones;
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

		Disco d = manager.find(Disco.class, id);
		if (d != null) {
			result = true;
		} else {
			result = false;
		}
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return result;
	}
}

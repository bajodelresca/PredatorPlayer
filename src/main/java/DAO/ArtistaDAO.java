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
    @NamedQuery(name="ArtistaDAO.findAll",
                query="SELECT ID,Nombre,Nacionalidad,Foto FROM artista"),
    @NamedQuery(name="ArtistaDAO.findByID",
                query="SELECT ID,Nombre,Nacionalidad,Foto FROM artista Where ID= :ID")
}) 
public class ArtistaDAO extends Artista implements DAO<Artista> {

	private final static String findAll = "ArtistaDAO.findAll";
	private final static String findByID = "ArtistaDAO.findByID";

	public ArtistaDAO(int ID, String Nombre, String Nacionalidad, String Foto) {
		super(ID, Nombre, Nacionalidad, Foto);
	}

	public ArtistaDAO() {
		super();
	}

	public ArtistaDAO(Artista a) {
		super(a.getID(), a.getNombre(), a.getNacionalidad(), a.getFoto());
	}
	
	public ArtistaDAO(int id) {
		super(getByID(id));
	}

//______________________________________________________________________________CRUD
	@Override
	public void insert(Artista a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.persist(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public void edit(Artista a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.merge(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public void remove(Artista a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.remove(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public List<Artista> getAll() {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(findAll);
		List<Artista> artistas =  q.getResultList();
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return artistas;
	}

	public Artista getByID(int id) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();

		Query q = manager.createNamedQuery(findByID);
		q.setParameter(1, id);
		Artista a = (Artista) q.getSingleResult();
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return a;
	}

	/**
	 * Recibe el id de un artista y devuelve todos sus discos
	 *
	 * @param id
	 * @return canciones
	 */

	/*
	 * public List<Disco> getListRepertorio(int id) { EntityManager manager =
	 * ConnectionUtils.getManager(); manager.getTransaction().begin();
	 * 
	 * // PREGUNTAR List<Disco> repertorio = manager
	 * .createQuery("FROM disco as d INNER JOIN artista as art on art.ID=d.IDArtista WHERE art.ID=?"
	 * ) .getResultList(); if (d != null) { result = true; } else { result = false;
	 * }
	 * 
	 * manager.getTransaction().commit(); ConnectionUtils.closeManager(manager);
	 * 
	 * return repertorio; }
	 */

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

		Artista a = manager.find(Artista.class, id);
		if (a != null) {
			result = true;
		} else {
			result = false;
		}
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return result;
	}
}

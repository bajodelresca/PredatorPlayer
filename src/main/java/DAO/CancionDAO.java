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

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Cancion;
import model.Disco;

@NamedQueries({
    @NamedQuery(name="CancionDAO.findAll",
                query="SELECT  ID,Nombre,Duracion,IDDisco FROM cancion"),
    @NamedQuery(name="CancionDAO.findByID",
                query="SELECT ID,Nombre,Duracion,IDDisco FROM cancion Where ID= :ID")
}) 
public class CancionDAO extends Cancion implements DAO<Cancion> {
	
	private final static String findAll = "CancionDAO.findAll";
	private final static String findByID = "CancionDAO.findByID";
	public CancionDAO(int ID, String Nombre, int Duracion, Disco Album) {
		super(ID, Nombre, Duracion, Album);

	}

	public CancionDAO() {
		super();

	}

	public CancionDAO(Cancion c) {
		super(c.getID(), c.getNombre(), c.getDuracion(), c.getAlbum());

	}

	public CancionDAO(int id) {
		super(getByID(id));
	}
	@Override
	public void insert(Cancion a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.persist(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public void edit(Cancion a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.merge(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	@Override
	public void remove(Cancion a) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		manager.remove(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
	}

	

	@Override
	public List<Cancion> getAll() {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(findAll);
		List<Cancion> canciones =  q.getResultList();
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return canciones;
	}

	/**
	 * Metodo que devuelve una Cancion por id pasado
	 *
	 * @param id identificador de cada Cancion
	 * @return Devuelve una Cancion
	 */
	private static Cancion getByID(int id) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();

		Query q = manager.createNamedQuery(findByID);
		q.setParameter(1, id);
		Cancion c = (Cancion) q.getSingleResult();
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return c;

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
		Cancion c = getByID(id);
		if (c != null) {
			result = true;
		} else {
			result = false;
		}
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
		return result;
	}
}

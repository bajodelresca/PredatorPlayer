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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Cancion;
import model.Disco;

/**
 *
 * @author Alberto343
 */
public class CancionDAO extends Cancion implements DAO<Cancion> {

	enum queries {
		INSERT("INSERT INTO cancion (ID, Nombre, Duracion, IDDisco) VALUES (NULL,?,?,?)"),
		UPDATE("UPDATE cancion SET Nombre=?,Duracion=?,IDDisco=? WHERE ID=?"), DELETE("DELETE FROM cancion WHERE ID=?"),
		DELETEALL("DELETE FROM cancion INNER JOIN WHERE ID=?"),
		GETBYID("SELECT ID,Nombre,Duracion,IDDisco FROM cancion Where ID=?"),
		GETALL("SELECT  ID,Nombre,Duracion,IDDisco FROM cancion");

		private String q;

		queries(String q) {
			this.q = q;
		}

		public String getQ() {
			return this.q;
		}
	}

	public CancionDAO(int ID, String Nombre, int Duracion, Disco Album) {
		super(ID, Nombre, Duracion, Album);

	}

	public CancionDAO() {
		super();

	}

	public CancionDAO(Cancion c) {
		super(c.getID(), c.getNombre(), c.getDuracion(), c.getAlbum());

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

	/**
	 * Metodo que convierte un ResultSet en Cancion
	 *
	 * @param rs Recibe un ResultSet
	 * @return Devuelve una Subscripcion
	 * @throws SQLException lanza una SQLException
	 */
	protected Cancion convert(ResultSet rs) throws SQLException {
		DiscoDAO dDAO = new DiscoDAO();
		int id = rs.getInt("ID");
		String nombre = rs.getString("Nombre");
		int duracion = rs.getInt("Duracion");
		// int idGenero=rs.getInt("IDGenero");
		int idDisco = rs.getInt("IDDisco");
		Disco album = dDAO.getByID(idDisco);
		Cancion c = new Cancion(id, nombre, duracion, album);
		return c;
	}

	@Override
	public List<Cancion> getAll() {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();
		List<Cancion> canciones = manager.createQuery("FROM CANCION").getResultList();
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
	public Cancion getByID(int id) {
		EntityManager manager = ConnectionUtils.getManager();
		manager.getTransaction().begin();

		Cancion c = manager.find(Cancion.class, id);

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

		Cancion c = manager.find(Cancion.class, id);
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

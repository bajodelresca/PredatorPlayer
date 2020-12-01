/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import model.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alberto343
 */
public class ConnectionUtils {
	private static java.sql.Connection _conn = null;
	private final static String APLICATIONH2 = "aplicacionH2";
	private final static String APLICATIONSQL = "aplicacionSQL";

	private static EntityManagerFactory emf;

	public static java.sql.Connection connect(Connection c) throws ClassNotFoundException, SQLException {
		java.sql.Connection conn = null;

		if (c == null) {
			return null;
		}

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://" + c.getHost() + "/" + c.getDb() + "?useLegacyDatetimeCode=false&serverTimezone=UTC",
				c.getUser(), c.getPassword());

		return conn;
	}

	public static java.sql.Connection getConnection() {
		if (_conn == null) {
			Connection c = new Connection();
			c.loadDataXML();
			try {
				_conn = connect(c);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return _conn;
	}

	private static EntityManagerFactory APLICATIONH2() {
		return Persistence.createEntityManagerFactory(APLICATIONH2);
	}

	private static EntityManagerFactory APLICATIONSQL() {
		return Persistence.createEntityManagerFactory(APLICATIONSQL);
	}

	public static boolean conexion(int op) {
		boolean result = false;
		switch (op) {
		case 1:
			emf = ConnectionUtils.APLICATIONH2();
			result = true;
			break;
		case 2:
			emf = ConnectionUtils.APLICATIONSQL();
			result = true;
			break;
		case 3:
			Utilities.p("Saliendo...");
			result = false;
			break;
		default:
			Utilities.p("Opción no valida");
		}

		return result;
	}

	public static EntityManager getManager() {
		return emf.createEntityManager();
	}

	public static void closeManager(EntityManager m) {
		m.close();
	}

}
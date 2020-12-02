package grupo1.proyectoacdat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DAO.ArtistaDAO;
import Utils.ConnectionUtils;
import Utils.Utilities;
import View.GUI;
import model.Artista;

public class test {
	public static void main(String[] args) {
		Artista art = new Artista(1, "ArtistaPrueba", "Española", "foto1");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacionH2");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(art);

		Artista a = manager.find(Artista.class, 1);
		System.out.println(a);
		manager.getTransaction().commit();
		ConnectionUtils.closeManager(manager);
/*
		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("aplicacionH2");
		EntityManager manager2 = emf2.createEntityManager();
		manager2.getTransaction().begin();

		manager2.getTransaction().commit();
		ConnectionUtils.closeManager(manager2);*/
	}
}

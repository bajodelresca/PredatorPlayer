/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo1.proyectoacdat;

import DAO.ArtistaDAO;
import DAO.CancionDAO;
import DAO.DiscoDAO;
import DAO.ListaDAO;
import View.GUI;
import java.util.List;
import model.Artista;
import model.Cancion;
import model.Disco;

/**
 *
 * @author Alberto343
 */
public class execute {

    public static void main(String[] args) {
        CancionDAO cDAO=new CancionDAO();
        DiscoDAO a=new DiscoDAO();
        Cancion c1=cDAO.getByID(5);
        Cancion c2=cDAO.getByID(3);
        cDAO.remove(c1);
        cDAO.remove(c2);
      Disco d=a.getByID(2);
        a.remove(d);
        for (Disco subscripcion : a.getAll()) {
            System.out.println(subscripcion);
        }
         
       /* CancionDAO cDAO = new CancionDAO();
        Cancion c = new Cancion();
        c.setNombre("CANCIONPRUEBA");
        c.setDuracion(300);
        c.setGenero(0);
        cDAO.insert(c);
        for (Cancion subscripcion : cDAO.getAll()) {
            System.out.println(subscripcion);
        }*/
        //   GUI.principal();
    }
}

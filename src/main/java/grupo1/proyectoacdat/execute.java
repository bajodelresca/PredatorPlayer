/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo1.proyectoacdat;

import DAO.ArtistaDAO;
import DAO.CancionDAO;
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
        
        ArtistaDAO a=new ArtistaDAO();
        Artista art=new Artista();
         
           art.setNombre("Juan");
           art.setNacionalidad("Argentino");
           art.setFoto("FOTO3");
             a.insert(art);
        for (Artista subscripcion : a.getAll()) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo1.proyectoacdat;

import DAO.CancionDAO;
import View.GUI;
import controller.AppController;
import java.util.List;
import model.Cancion;
import model.Subscripcion;

/**
 *
 * @author Alberto343
 */
public class execute {
    public static void main(String[] args) {
      /*  CancionDAO cdao=new CancionDAO();
        List<Cancion> lista=cdao.getAll();
        for (Cancion cancion : lista) {
            System.out.println(cancion);
        }*/
     AppController a=AppController.getInstance();
     List<Subscripcion>list=  a.getAllSubs();
        for (Subscripcion subscripcion : list) {
            System.out.println(subscripcion);
        }
    // GUI.principal();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UsuarioDAO;
import java.util.List;
import model.Usuario;

/**
 *
 * @author espin
 */
public class UsuarioController {
     private static UsuarioController instancia = null;

    public static UsuarioController getInstance() {
        instancia = new UsuarioController();
        return instancia;
    }

    public List<Usuario> getAllList() {
        UsuarioDAO UDAO = new UsuarioDAO();
        return UDAO.getAll();
    }

    public Usuario getUserById(int id) {
        UsuarioDAO UDAO = new UsuarioDAO();
        return UDAO.getByID(id);
    }

    public void insertUser(Usuario a) {
        UsuarioDAO UDAO = new UsuarioDAO();
        UDAO.insert(a);
    }

    public void editUser(Usuario a) {
        UsuarioDAO UDAO = new UsuarioDAO();
        UDAO.edit(a);
    }
    public void removeUser(Usuario a) {
        UsuarioDAO UDAO = new UsuarioDAO();
        UDAO.remove(a);
    }
     
}

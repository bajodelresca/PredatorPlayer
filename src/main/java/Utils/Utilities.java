package Utils;

import java.util.Scanner;

public class Utilities {

    private static Scanner keyboard = new Scanner(System.in);

    /**
     * Escribe un texto en consola sin retorno de carro
     *
     * @param f texto a imprimir
     */
    public static void p(String f) {
        System.out.print(f);
    }

    /**
     * Escribe un texto en consola con retorno de carro
     *
     * @param f texto a imprimir
     */
    public static void P(String f) {
        Utilities.p(f + "\n");
    }

    public static int Menu() {
        int opcion = 0;
        P("-----Bienvenido a Espotifi-----");
        P("1) Listar");
        P("2) Insertar");
        P("3) Editar");
        P("4) Eliminar");
        P("5) Salir");
        P("-------------------------------------");
        p("> ");

        try {
            opcion = keyboard.nextInt();
        } catch (Exception e) {
            System.out.println("Introduzca un número entero");
            keyboard = new Scanner(System.in);
        }

        return opcion;
    }

    //____________________________________________________________________________MenuListar
    public static int MenuListar() {
        int opcion = 0;
        P("-----Menú de Información-----");
        P("1) Listar Cancion");
        P("2) Listar Disco");
        P("3) Listar Artista");
        P("4) Listar lista de Reproduccion");
        P("5) Listar subscriptores por Id de la lista");
        P("6) Listar listas a las que esta subscrito el usuario por Id");
        P("7) Volver al menú anterior");
        P("-------------------------------------");
        p("> ");

        try {
            opcion = keyboard.nextInt();
        } catch (Exception e) {
            System.out.println("Introduzca un número entero");
            keyboard = new Scanner(System.in);
        }

        return opcion;
    }
    

    public static int subMenuListar() {
        int opcion = 0;
        P("1) Listar todos");
        P("2) Listar por ID");
        P("3) Volver al menú anterior");
        p("> ");

        try {
            opcion = keyboard.nextInt();
        } catch (Exception e) {
            System.out.println("Introduzca un número entero");
            keyboard = new Scanner(System.in);
        }

        return opcion;
    }
    
    //____________________________________________________________________________MenuInsertar
    
    public static int MenuInsertar() {
        int opcion = 0;
        P("-----Menú de Creación-----");
        P("1) Insertar Artista");
        P("2) Insertar Disco");
        P("3) Insertar Canción");
        P("4) Crear una lista de reproducción");
        P("5) Insertar una canción a una lista");
        P("6) Subscribirte una lista de reproducción");        
        P("7) Salir");
        P("-------------------------------------");
        p("> ");

        try {
            opcion = keyboard.nextInt();
        } catch (Exception e) {
            System.out.println("Introduzca un número entero");
            keyboard = new Scanner(System.in);
        }

        return opcion;
    }
    
    //____________________________________________________________________________MenuEditar
    
    public static int MenuEditar() {
        int opcion = 0;
        P("-----Menú de Edición-----");
        P("1) Editar un Artista");
        P("2) Editar un Disco");
        P("3) Editar un Canción");
        P("4) Editar una lista de reproducción");       
        P("5) Salir");
        P("-------------------------------------");
        p("> ");

        try {
            opcion = keyboard.nextInt();
        } catch (Exception e) {
            System.out.println("Introduzca un número entero");
            keyboard = new Scanner(System.in);
        }

        return opcion;
    }
    
    //____________________________________________________________________________MenuEliminar
    
    public static int MenuEliminar() {
        int opcion = 0;
        P("-----Menú de Eliminación-----");
        P("1) Eliminar Artista");
        P("2) Eliminar Disco");
        P("3) Eliminar Canción");
        P("4) Eliminar una lista de reproducción");
        P("5) Eliminar una canción de una lista");
        P("6) Eliminar una suscripción a una lista");        
        P("7) Eliminar un Usuario"); 
        P("8) Salir");
        P("-------------------------------------");
        p("> ");

        try {
            opcion = keyboard.nextInt();
        } catch (Exception e) {
            System.out.println("Introduzca un número entero");
            keyboard = new Scanner(System.in);
        }

        return opcion;
    }
    
    
    
}

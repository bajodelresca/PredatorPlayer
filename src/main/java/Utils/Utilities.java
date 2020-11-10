package Utils;

import java.util.Scanner;

/**
 *
 * @author Alberto343
 */
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
        P("1)Listar");
        P("2)Insertar");
        P("3)Editar");
        P("4)Eliminar");
        P("5)Salir");
        P("-------------------------------------");
        p("> ");
        
        try{
            opcion = keyboard.nextInt();
        }catch(Exception e){
            System.out.println("Introduzca un número entero");
            keyboard=new Scanner(System.in);
        }

        return opcion;
    }
     public static int MenuListar() {
        int opcion = 0;
        P("-----Bienvenido a Espotifi-----");
        P("1)Listar Cancion");
        P("2)Listar Disco");
        P("3)Listar Artista");
        P("4)Listar lista de Reproduccion");
        P("5)Listar subscriptores por Id de la lista");
        P("6)Listar listas a las que esta subscrito el usuario por Id");
        P("7)Volver al menú anterior");
        P("-------------------------------------");
        p("> ");

        try{
            opcion = keyboard.nextInt();
        }catch(Exception e){
            System.out.println("Introduzca un número entero");
            keyboard=new Scanner(System.in);
        }

        return opcion;
    }
      public static int MenuListarCancion() {
        int opcion = 0;
        P("1)Listar todos");
        P("2)Listar por ID");
        P("3)Listar por Nombre");
        P("4)Volver al menú anterior");
        p("> ");
        
        try{
            opcion = keyboard.nextInt();
        }catch(Exception e){
            System.out.println("Introduzca un número entero");
            keyboard=new Scanner(System.in);
        }

        return opcion;
    }
}

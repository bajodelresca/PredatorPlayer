package View;

import Utils.Utilities;
import controller.AppController;
import java.util.List;
import java.util.Scanner;
import model.Cancion;

public class GUI {

    private static AppController controlador = AppController.getInstance();
    private static Scanner keyboard = new Scanner(System.in);

    public static void principal() {
        int opcion = 0;
        do {
            opcion = Utilities.Menu();
            ControladorPrimerMenu(opcion);
        } while (opcion != 5);

    }

    private static void ControladorPrimerMenu(int op1) {
        int op2 = 0;

        switch (op1) {
            case 1:
                do {
                    op2 = Utilities.MenuListar();
                    ControladorMenuListar(op2);
                } while (op2 != 7);
                break;

            case 2:
                do {
                    op2 = Utilities.MenuInsertar();
                    ControladorMenuInsertar(op2);
                } while (op2 != 7);
                break;

            case 3:
                do {
                    op2 = Utilities.MenuEditar();
                    ControladorMenuEditar(op2);
                } while (op2 != 5);
                break;

            case 4:
                do {
                    op2 = Utilities.MenuEliminar();
                    ControladorMenuEliminar(op2);
                } while (op2 != 7);
                break;

            case 5:
                Utilities.P("Saliendo de la aplicación.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    //___________________________________________________________________________MenuListar
    private static void ControladorMenuListar(int op2) {
        int op3 = 0;
        switch (op2) {
            case 1:
                do {
                    op3 = Utilities.subMenuListar();
                    ControladorSubMenuListar(op3);
                } while (op3 != 3);
                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

            case 5:

                break;

            case 6:

                break;

            case 7:
                Utilities.P("Saliendo del Menú de Información.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorSubMenuListar(int op2) {
        switch (op2) {
            case 1:
                List<Cancion> listCancio = controlador.getAllSongs();
                for (Cancion cancion : listCancio) {
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + cancion.getID());
                    System.out.println("Nombre: " + cancion.getNombre());
                    System.out.println("Duracion: " + cancion.getDuracion());
                    System.out.println("Disco: " + cancion.getAlbum());
                    System.out.println("----------------------------------");
                }
                break;

            case 2:
                Utilities.P("Introduce el id de la cancion: ");
                int id = keyboard.nextInt();
                if (controlador.searchSongByID(id)) {
                    Cancion c = controlador.getSongsById(id);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + c.getID());
                    System.out.println("Nombre: " + c.getNombre());
                    System.out.println("Duracion: " + c.getDuracion());
                    System.out.println("Disco: " + c.getAlbum());
                    System.out.println("----------------------------------");
                } else {
                    System.out.println("EL ID DE LA CANCION NO EXISTE");
                }
                break;
            case 3:

                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    //___________________________________________________________________________MenuInsertar
    private static void ControladorMenuInsertar(int op2) {

        switch (op2) {
            case 1:

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

            case 5:

                break;

            case 6:

                break;

            case 7:
                Utilities.P("Saliendo del Menú de Creación.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    //___________________________________________________________________________MenuEditar
    private static void ControladorMenuEditar(int op2) {

        switch (op2) {
            case 1:

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

            case 5:
                Utilities.P("Saliendo del Menú de Edición.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    //___________________________________________________________________________MenuEliminar
    private static void ControladorMenuEliminar(int op2) {

        switch (op2) {
            case 1:

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

            case 5:

                break;

            case 6:

                break;

            case 7:
                Utilities.P("Saliendo del Menú de Eliminación.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

}

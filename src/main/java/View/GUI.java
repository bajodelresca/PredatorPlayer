package View;

import Utils.Utilities;
import controller.AppController;

public class GUI {

    private static AppController controlador = AppController.getInstance();

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
                Utilities.P("Saliendo del Menú de Información.");
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

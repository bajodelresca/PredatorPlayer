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

}

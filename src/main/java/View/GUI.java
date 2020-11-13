package View;

import Utils.Utilities;
import controller.AppController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Artista;
import model.Cancion;
import model.Disco;

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
                    ControladorSubMenuListarCancion(op3);
                } while (op3 != 3);
                break;

            case 2:
                do {
                    op3 = Utilities.subMenuListar();
                    ControladorSubMenuListarDisco(op3);
                } while (op3 != 3);
                break;

            case 3:
                do {
                    op3 = Utilities.subMenuListar();
                    ControladorSubMenuListarArtista(op3);
                } while (op3 != 3);
                break;

            case 4:
                do {
                    op3 = Utilities.subMenuListar();
                    ControladorSubMenuListarListaDR(op3);
                } while (op3 != 3);
                break;

            case 5:
                do {
                    Utilities.P("Introduzca el ID de la Lista de Reproducción");
                    /* 
                    ControladorSubMenuListarSUB(id); */
                } while (op3 != 3);
                break;

            case 6:
                do {
                    Utilities.P("Introduzca el ID del Usuario");
                    /* 
                    ControladorSubMenuListarListasDU(op3); */
                } while (op3 != 3);
                break;

            case 7:
                Utilities.P("Saliendo del Menú de Información.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorSubMenuListarCancion(int op2) {
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
                Utilities.P("Saliendo del Menú listar Canción ");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }
    
    private static void ControladorSubMenuListarDisco(int op2) {
        switch (op2) {
            case 1:
                List<Disco> listDisco = controlador.getAllDiscs();
                for (Disco disco : listDisco) {
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + disco.getID());
                    System.out.println("Nombre: " + disco.getNombre());
                    System.out.println("Foto: " + disco.getFoto());
                    System.out.println("Fecha: " + disco.getFecha());
                    System.out.println("Creador: " + disco.getCreador());
                    System.out.println("----------------------------------");
                }
                break;
            case 2:
                Utilities.P("Introduce el id del Disco: ");
                int id = keyboard.nextInt();
                if (controlador.searchSongByID(id)) {
                    Disco d = controlador.getDiscsById(id);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + d.getID());
                    System.out.println("Nombre: " + d.getNombre());
                    System.out.println("Foto: " + d.getFoto());
                    System.out.println("Fecha: " + d.getFecha());
                    System.out.println("Creador: " + d.getCreador());
                    System.out.println("----------------------------------");
                } else {
                    System.out.println("EL ID DEL DISCO NO EXISTE");
                }
                break;
            case 3:
                Utilities.P("Saliendo del Menú listar Disco ");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    //___________________________________________________________________________MenuInsertar
    private static void ControladorMenuInsertar(int op2) {
        String nombre = "";
        String foto = "";
        switch (op2) {
            case 1:
                Utilities.P("Introduzca el nombre del artista: ");
                nombre = keyboard.next();
                Utilities.P("Introduzca la nacionalidad: ");
                String nacionalidad = keyboard.next();
                Utilities.P("Introduzca la foto de perfil: ");
                foto = keyboard.next();
                Artista a = new Artista(nombre, nacionalidad, foto);
                if (controlador.insertArtists(a)) {
                    Utilities.P("EL ARTISTA HA SIDO CREADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA CREACION DEL ARTISTA");
                }
                break;

            case 2:
                //ARREGLAR
                Utilities.P("Introduzca el nombre del disco: ");
                nombre = keyboard.next();
                Utilities.P("Introduzca una foto: ");
                foto = keyboard.next();
                Utilities.P("Introduzca la fecha de salida (yyyy/MM/dd): ");
                String fecha = keyboard.next();
                DateFormat fechaFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                try {
                    date = fechaFormat.parse(fecha);
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
                Utilities.P("Introduzca el ID del Artista: ");
                int idArtista = keyboard.nextInt();
                if (controlador.searchArtistaByID(idArtista)) {
                    Artista art = controlador.getArtistsById(idArtista);
                    Disco d = new Disco(nombre, foto, date, art);
                    if (controlador.insertDiscs(d)) {
                        Utilities.P("EL DISCO HA SIDO CREADO CON EXITO");
                    } else {
                        Utilities.P("HA OCURRIDO UN PROBLEMA EN LA CREACION DEL DISCO");
                    }
                } else {
                    System.out.println("EL ID DEL ARTISTA NO EXISTE");
                }

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

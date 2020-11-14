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
import model.Lista;
import model.Subscripcion;
import model.Usuario;

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
                } while (op2 != 8);
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
                } while (op2 != 6);
                break;

            case 4:
                do {
                    op2 = Utilities.MenuEliminar();
                    ControladorMenuEliminar(op2);
                } while (op2 != 8);
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
                    Utilities.P("1) Listar por ID: ");
                    Utilities.P("2) Volver al menú anterior");
                    Utilities.p("> ");
                    op3 = keyboard.nextInt();
                    ControladorSubMenuListarSUB(op3);
                } while (op3 != 2);
                break;

            case 6:
                do {
                    Utilities.P("1) Listar por ID: ");
                    Utilities.P("2) Volver al menú anterior");
                    Utilities.p("> ");
                    op3 = keyboard.nextInt();
                    ControladorSubMenuListarListasDU(op3);
                } while (op3 != 2);
                break;
            
            case 7:
                do {
                    Utilities.P("1) Listar Canciones de la Lista de Reproducción");
                    Utilities.P("2) Volver al menú anterior");
                    Utilities.p("> ");
                    op3 = keyboard.nextInt();
                    ControladorSubMenuListarListaDeCAN(op3);
                } while (op3 != 3);
                break;

            case 8:
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
                int id = 0;
                boolean valid = false;
                do {
                    try {
                        id = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
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
                int id = 0;
                boolean valid = false;
                do {
                    try {
                        id = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
                if (controlador.searchDiscByID(id)) {
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

    private static void ControladorSubMenuListarArtista(int op2) {
        switch (op2) {
            case 1:
                List<Artista> listArtist = controlador.getAllArtists();
                for (Artista artista : listArtist) {
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + artista.getID());
                    System.out.println("Nombre: " + artista.getNombre());
                    System.out.println("Nacionalidad: " + artista.getNacionalidad());
                    System.out.println("Foto: " + artista.getFoto());
                    System.out.println("----------------------------------");
                }
                break;
            case 2:
                Utilities.P("Introduce el id del Artista: ");
                int id = 0;
                boolean valid = false;
                do {
                    try {
                        id = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
                if (controlador.searchArtistaByID(id)) {
                    Artista a = controlador.getArtistsById(id);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + a.getID());
                    System.out.println("Nombre: " + a.getNombre());
                    System.out.println("Nacionalidad: " + a.getNacionalidad());
                    System.out.println("Foto: " + a.getFoto());
                    System.out.println("----------------------------------");
                } else {
                    System.out.println("EL ID DEL ARTISTA NO EXISTE");
                }
                break;
            case 3:
                Utilities.P("Saliendo del Menú listar Artista ");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorSubMenuListarListaDR(int op2) {
        switch (op2) {
            case 1:
                List<Lista> listLista = controlador.getAllList();
                for (Lista lista : listLista) {
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + lista.getID());
                    System.out.println("Nombre: " + lista.getNombre());
                    System.out.println("Descripción: " + lista.getDescripcion());
                    System.out.println("Creador: " + lista.getCreador());
                    System.out.println("----------------------------------");
                }
                break;
            case 2:
                Utilities.P("Introduce el id de la Lista de Reproducción: ");
                int id = 0;
                boolean valid = false;
                do {
                    try {
                        id = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
                if (controlador.searchListByID(id)) {
                    Lista l = controlador.getListById(id);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + l.getID());
                    System.out.println("Nombre: " + l.getNombre());
                    System.out.println("Descripción: " + l.getDescripcion());
                    System.out.println("Creador: " + l.getCreador());
                    System.out.println("----------------------------------");
                } else {
                    System.out.println("EL ID DE LA LISTA NO EXISTE");
                }
                break;
            case 3:
                Utilities.P("Saliendo del Menú listar Lista de Reproducción ");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorSubMenuListarSUB(int op2) {
        switch (op2) {
            case 1:
                Utilities.P("Introduce el id de la Lista de Reproducción: ");
                int id = 0;
                boolean valid = false;
                do {
                    try {
                        id = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
                if (controlador.searchListByID(id)) {
                    List<Usuario> listUsuario = controlador.getSubscriberFromList(id);
                    for (Usuario u : listUsuario) {
                        System.out.println("----------------------------------");
                        System.out.println("ID: " + u.getID());
                        System.out.println("Nombre: " + u.getNombre());
                        System.out.println("Correo: " + u.getCorreo());
                        System.out.println("Foto: " + u.getFoto());
                        System.out.println("----------------------------------");
                    }
                } else {
                    System.out.println("EL ID DE LA LISTA NO EXISTE");
                }
                break;
            case 2:
                Utilities.P("Saliendo del Menú listar Suscriptores ");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorSubMenuListarListasDU(int op2) {
        switch (op2) {
            case 1:
                Utilities.P("Introduce el id del Usuario: ");
                int id = 0;
                boolean valid = false;
                do {
                    try {
                        id = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
                if (controlador.searchUserByID(id)) {
                    List<Lista> listLista = controlador.getListFromSubscriber(id);
                    for (Lista li : listLista) {
                        System.out.println("----------------------------------");
                        System.out.println("ID: " + li.getID());
                        System.out.println("Nombre: " + li.getNombre());
                        System.out.println("----------------------------------");
                    }
                } else {
                    System.out.println("EL ID DEL USUARIO NO EXISTE");
                }
                break;
            case 2:
                Utilities.P("Saliendo del Menú listar Listas Subscritas ");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }
    
    private static void ControladorSubMenuListarListaDeCAN (int op2){
        switch (op2) {
            case 1:
                Utilities.P("Introduce el id de la Lista de Reproducción: ");
                int id = 0;
                boolean valid = false;
                do {
                    try {
                        id = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
                if (controlador.searchListByID(id)) {
                    List<Cancion> listCanc = controlador.getAllSongsList(id);
                    for (Cancion c : listCanc) {
                        System.out.println("----------------------------------");
                        System.out.println("ID: " + c.getID());
                        System.out.println("Nombre: " + c.getNombre());
                        System.out.println("----------------------------------");
                    }
                } else {
                    System.out.println("EL ID DE LA LISTA NO EXISTE");
                }
                break;
            case 2:
                Utilities.P("Saliendo del Menú listar canciones de la Lista de Reproducción ");
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

                Utilities.P("Introduzca el nombre del disco: ");
                nombre = keyboard.next();
                Utilities.P("Introduzca una foto: ");
                foto = keyboard.next();
                Utilities.P("Introduzca el año de salida (yyyy): ");
                String year = keyboard.next();
                Utilities.P("Introduzca el mes de salida (MM): ");
                String month = keyboard.next();
                Utilities.P("Introduzca el dia de salida (dd): ");
                String day = keyboard.next();
                String fecha = year + "-" + month + "-" + day;
                DateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                java.sql.Date sql = null;
                try {
                    date = fechaFormat.parse(fecha);
                    sql = new java.sql.Date(date.getTime());
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
                Utilities.P("Introduzca el ID del Artista: ");

                int idArtista = 0;
                boolean valid = false;
                do {
                    try {
                        idArtista = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
                if (controlador.searchArtistaByID(idArtista)) {
                    Artista art = controlador.getArtistsById(idArtista);
                    Disco d = new Disco(nombre, foto, sql, art);
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
                Utilities.P("Introduzca el nombre de la Cancion: ");
                nombre = keyboard.next();
                Utilities.P("Introduzca su Duracion (Segundos): ");

                int duracion = 0;
                boolean valid2 = false;
                do {
                    try {
                        duracion = Integer.parseInt(keyboard.nextLine());
                        valid2 = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid2);
                Utilities.P("Introduzca el ID del Disco: ");
                int idDisco = keyboard.nextInt();
                if (controlador.searchDiscByID(idDisco)) {
                    Disco d = controlador.getDiscsById(idDisco);
                    Cancion c = new Cancion(nombre, duracion, d);
                    if (controlador.insertSongs(c)) {
                        Utilities.P("LA CANCION HA SIDO CREADA CON EXITO");
                    } else {
                        Utilities.P("HA OCURRIDO UN PROBLEMA EN LA CREACION DE LA CANCION");
                    }
                } else {
                    System.out.println("EL ID DEL DISCO NO EXISTE");
                }

                break;

            case 4:
                Utilities.P("Introduzca el nombre de la Lista: ");
                nombre = keyboard.nextLine();
                Utilities.P("Introduzca la descripcion de la Lista: ");
                String descripcion = keyboard.nextLine();
                Utilities.P("Introduzca el ID del Creador: ");
                int idCreador = 0;
                boolean valid3 = false;
                do {
                    try {
                        idCreador = Integer.parseInt(keyboard.nextLine());
                        valid3 = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid3);
                if (controlador.searchUserByID(idCreador)) {
                    Usuario u = controlador.getUserById(idCreador);
                    Lista l = new Lista(nombre, descripcion, u);
                    if (controlador.insertList(l)) {
                        Utilities.P("LA LISTA HA SIDO CREADA CON EXITO");
                    } else {
                        Utilities.P("HA OCURRIDO UN PROBLEMA EN LA CREACION DE LA LISTA");
                    }
                } else {
                    System.out.println("EL ID DEL USUARIO NO EXISTE");
                }
                break;

            case 5:
                Utilities.P("Introduzca el ID de la Lista: ");
                int idLista = 0;
                boolean valid4 = false;
                do {
                    try {
                        idLista = Integer.parseInt(keyboard.nextLine());
                        valid4 = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid4);
                if (controlador.searchListByID(idLista)) {
                    Lista listR = controlador.getListById(idLista);
                    Utilities.P("Introduzca el ID de la Cancion: ");
                    int idCancion = 0;
                    boolean valid5 = false;
                    do {
                        try {
                            idCancion = Integer.parseInt(keyboard.nextLine());
                            valid5 = true;

                        } catch (IllegalStateException ex) {
                            keyboard = new Scanner(System.in);
                            Utilities.P("Error in keyboard. Please, try it again: ");
                        } catch (NumberFormatException ex) {
                            Utilities.P("Error reading integer type. Please, try it again: ");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            Utilities.P("Error unknown. Please, try it again: ");
                        }
                    } while (!valid5);
                    if (controlador.searchSongByID(idCancion)) {
                        Cancion song = controlador.getSongsById(idCancion);
                        if (controlador.insertListCanc(listR.getID(), song.getID())) {
                            listR.setCancionListareproduccion(song);
                            Utilities.P("SE HA INSERTADO LA CANCION CON EXITO");
                        } else {
                            Utilities.P("HA OCURRIDO UN PROBLEMA INSERCION DE LA CANCION");
                        }
                    } else {
                        Utilities.P("EL ID DE LA CANCION NO EXISTE ");
                    }
                } else {
                    Utilities.P("EL ID DE LA LISTA NO EXISTE ");
                }
                break;

            case 6:
                Utilities.P("Introduzca el ID de la Lista: ");
                int idList = 0;
                boolean valid6 = false;
                do {
                    try {
                        idList = Integer.parseInt(keyboard.nextLine());
                        valid6 = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid6);
                if (controlador.searchListByID(idList)) {
                    Lista listR = controlador.getListById(idList);
                    Utilities.P("Introduzca el ID del usuario: ");
                    int idUser = keyboard.nextInt();
                    if (controlador.searchUserByID(idUser)) {
                        Usuario u = controlador.getUserById(idUser);
                        Subscripcion sub = new Subscripcion(listR, u);
                        if (controlador.insertSubs(sub)) {
                            Utilities.P("SE HA CREADO LA SUBSCRIPCION CON EXITO");
                        } else {
                            Utilities.P("HA OCURRIDO UN PROBLEMA AL CREAR LA SUBSCRIPCION");
                        }
                    } else {
                        Utilities.P("EL ID DEl USUARIO NO EXISTE ");
                    }
                } else {
                    Utilities.P("EL ID DE LA LISTA NO EXISTE ");
                }
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
                Utilities.P("Introduzca el ID del Artista a editar: ");
                int idArtEdit = keyboard.nextInt();
                if (controlador.searchArtistaByID(idArtEdit)) {
                    Artista a = controlador.getArtistsById(idArtEdit);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + a.getID());
                    System.out.println("Nombre: " + a.getNombre());
                    System.out.println("Nacionalidad: " + a.getNacionalidad());
                    System.out.println("Foto: " + a.getFoto());
                    System.out.println("----------------------------------");
                    int op = 0;
                    do {
                        op = Utilities.MenuEditarArtista();
                        ControladorMenuEditarArtista(op, a);
                    } while (op != 4);

                } else {
                    Utilities.P("El ID DEL ARTISTA NO EXISTE");
                }
                break;

            case 2:
                Utilities.P("Introduzca el ID del Disco a editar: ");
                int idDiscEdit = keyboard.nextInt();
                if (controlador.searchDiscByID(idDiscEdit)) {
                    Disco d = controlador.getDiscsById(idDiscEdit);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + d.getID());
                    System.out.println("Nombre: " + d.getNombre());
                    System.out.println("Foto: " + d.getFoto());
                    System.out.println("Fecha: " + d.getFecha());
                    System.out.println("Creador: " + d.getCreador());
                    System.out.println("----------------------------------");
                    int op = 0;
                    do {
                        op = Utilities.MenuEditarArtista();
                        ControladorMenuEditarDisco(op, d);
                    } while (op != 5);

                } else {
                    Utilities.P("El ID DEL DISCO NO EXISTE");
                }
                break;

            case 3:
                Utilities.P("Introduzca el ID de la Cancion a editar: ");
                int idSongEdit = keyboard.nextInt();
                if (controlador.searchSongByID(idSongEdit)) {
                    Cancion c = controlador.getSongsById(idSongEdit);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + c.getID());
                    System.out.println("Nombre: " + c.getNombre());
                    System.out.println("Duracion: " + c.getDuracion());
                    System.out.println("Disco: " + c.getAlbum());
                    System.out.println("----------------------------------");
                    int op = 0;
                    do {
                        op = Utilities.MenuEditarCancion();
                        ControladorMenuEditarCancion(op, c);
                    } while (op != 4);

                } else {
                    Utilities.P("El ID DE LA CANCION NO EXISTE");
                }
                break;

            case 4:
                Utilities.P("Introduzca el ID de la Lista a editar: ");
                int idListEdit = keyboard.nextInt();
                if (controlador.searchListByID(idListEdit)) {
                    Lista l = controlador.getListById(idListEdit);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + l.getID());
                    System.out.println("Nombre: " + l.getNombre());
                    System.out.println("Descripción: " + l.getDescripcion());
                    System.out.println("Creador: " + l.getCreador());
                    System.out.println("----------------------------------");
                    int op = 0;
                    do {
                        op = Utilities.MenuEditarLsita();
                        ControladorMenuEditarLista(op, l);
                    } while (op != 3);

                } else {
                    Utilities.P("El ID DE LA LISTA NO EXISTE");
                }
                break;
            case 5:
                Utilities.P("Introduzca el ID del Usuario a editar: ");
                int idUserEdit = keyboard.nextInt();
                if (controlador.searchUserByID(idUserEdit)) {
                    Usuario u = controlador.getUserById(idUserEdit);
                    System.out.println("----------------------------------");
                    System.out.println("ID: " + u.getID());
                    System.out.println("Nombre: " + u.getNombre());
                    System.out.println("Correo: " + u.getCorreo());
                    System.out.println("Foto: " + u.getFoto());
                    System.out.println("----------------------------------");
                    int op = 0;
                    do {
                        op = Utilities.MenuEditarUsuario();
                        ControladorMenuEditarUsuario(op, u);
                    } while (op != 4);

                } else {
                    Utilities.P("El ID DEL USUARIO NO EXISTE");
                }
                break;
            case 6:
                Utilities.P("Saliendo del Menú de Edición.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorMenuEditarArtista(int op2, Artista a) {
        switch (op2) {
            case 1:
                Utilities.P("Introduzca el nuevo nombre: ");
                String newNombre = keyboard.next();
                a.setNombre(newNombre);
                if (controlador.editArtists(a)) {
                    Utilities.P("EL ARTISTA HA SIDO EDITADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL ARTISTA");
                }

                break;

            case 2:
                Utilities.P("Introduzca la nueva nacinalidad: ");
                String newnacinalidad = keyboard.next();
                a.setNacionalidad(newnacinalidad);
                if (controlador.editArtists(a)) {
                    Utilities.P("EL ARTISTA HA SIDO EDITADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL ARTISTA");
                }
                break;

            case 3:
                Utilities.P("Introduzca la nueva foto: ");
                String newfoto = keyboard.next();
                a.setNacionalidad(newfoto);
                if (controlador.editArtists(a)) {
                    Utilities.P("EL ARTISTA HA SIDO EDITADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL ARTISTA");
                }
                break;
            case 4:
                Utilities.P("Saliendo del Menú de Edición Artista.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorMenuEditarDisco(int op2, Disco d) {
        switch (op2) {
            case 1:
                Utilities.P("Introduzca el nuevo nombre: ");
                String newNombre = keyboard.next();
                d.setNombre(newNombre);
                if (controlador.editDiscs(d)) {
                    Utilities.P("EL DISCO HA SIDO EDITADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL DISCO");
                }
                break;

            case 2:
                Utilities.P("Introduzca la nueva foto: ");
                String newFoto = keyboard.next();
                d.setFoto(newFoto);
                if (controlador.editDiscs(d)) {
                    Utilities.P("EL DISCO HA SIDO EDITADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL DISCO");
                }
                break;

            case 3:
                Utilities.P("Introduzca la nueva fecha: ");
                Utilities.P("Introduzca el año de salida (yyyy): ");
                String year = keyboard.next();
                Utilities.P("Introduzca el mes de salida (MM): ");
                String month = keyboard.next();
                Utilities.P("Introduzca el dia de salida (dd): ");
                String day = keyboard.next();
                String fecha = year + "-" + month + "-" + day;
                DateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                java.sql.Date sql = null;
                try {
                    date = fechaFormat.parse(fecha);
                    sql = new java.sql.Date(date.getTime());
                } catch (ParseException ex) {
                    System.out.println(ex);
                }

                d.setFecha(sql);
                if (controlador.editDiscs(d)) {
                    Utilities.P("EL DISCO HA SIDO EDITADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL DISCO");
                }
                break;
            case 4:
                Utilities.P("Introduzca el ID del Artista: ");
                int idArt = keyboard.nextInt();
                if (controlador.searchArtistaByID(idArt)) {
                    Artista newArt = controlador.getArtistsById(idArt);
                    d.setCreador(newArt);
                    if (controlador.editDiscs(d)) {
                        Utilities.P("EL DISCO HA SIDO EDITADO CON EXITO");
                    } else {
                        Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL DISCO");
                    }
                } else {
                    System.out.println("EL ID DEL DISCO NO EXISTE");
                }
                break;
            case 5:
                Utilities.P("Saliendo del Menú de Edición Disco.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorMenuEditarCancion(int op2, Cancion a) {
        switch (op2) {
            case 1:
                Utilities.P("Introduzca el nuevo nombre: ");
                String newNombre = keyboard.next();
                a.setNombre(newNombre);
                if (controlador.editSongs(a)) {
                    Utilities.P("LA CANCION HA SIDO EDITADA CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DE LA CANCION");
                }
                break;

            case 2:
                Utilities.P("Introduzca la nueva Duracion: ");
                int newDuracion = 0;
                boolean valid = false;
                do {
                    try {
                        newDuracion = Integer.parseInt(keyboard.nextLine());
                        valid = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid);
                a.setDuracion(newDuracion);
                if (controlador.editSongs(a)) {
                    Utilities.P("LA CANCION HA SIDO EDITADA CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DE LA CANCION");
                }
                break;

            case 3:
                Utilities.P("Introduzca el ID del Album: ");
                int idAlbum = 0;
                boolean valid2 = false;
                do {
                    try {
                        idAlbum = Integer.parseInt(keyboard.nextLine());
                        valid2 = true;

                    } catch (IllegalStateException ex) {
                        keyboard = new Scanner(System.in);
                        Utilities.P("Error in keyboard. Please, try it again: ");
                    } catch (NumberFormatException ex) {
                        Utilities.P("Error reading integer type. Please, try it again: ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Utilities.P("Error unknown. Please, try it again: ");
                    }
                } while (!valid2);
                if (controlador.searchDiscByID(idAlbum)) {
                    Disco newDisc = controlador.getDiscsById(idAlbum);
                    a.setAlbum(newDisc);
                    if (controlador.editSongs(a)) {
                        Utilities.P("LA CANCION HA SIDO EDITADA CON EXITO");
                    } else {
                        Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DE LA CANCION");
                    }
                } else {
                    System.out.println("EL ID DEL DISCO NO EXISTE");
                }
                break;
            case 4:
                Utilities.P("Saliendo del Menú de Edición Cancion.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorMenuEditarLista(int op2, Lista a) {
        switch (op2) {
            case 1:
                Utilities.P("Introduzca el nuevo nombre: ");
                String newNombre = keyboard.next();
                a.setNombre(newNombre);
                if (controlador.editList(a)) {
                    Utilities.P("LA LISTA HA SIDO EDITADA CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DE LA LISTA");
                }
                break;

            case 2:
                Utilities.P("Introduzca la descripcion: ");
                String newdescrip = keyboard.next();
                a.setDescripcion(newdescrip);
                if (controlador.editList(a)) {
                    Utilities.P("LA LISTA HA SIDO EDITADA CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DE LA LISTA");
                }
                break;

            case 3:
                Utilities.P("Saliendo del Menú de Edición Lista.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorMenuEditarUsuario(int op2, Usuario a) {
        switch (op2) {
            case 1:
                Utilities.P("Introduzca el nuevo nombre: ");
                String newNombre = keyboard.next();
                a.setNombre(newNombre);
                if (controlador.editUser(a)) {
                    Utilities.P("EL USUARIO HA SIDO EDITADA CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL USUARIO");
                }
                break;

            case 2:
                Utilities.P("Introduzca el nuevo correo: ");
                String newEmail = keyboard.next();
                a.setCorreo(newEmail);
                if (controlador.editUser(a)) {
                    Utilities.P("EL USUARIO HA SIDO EDITADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL USUARIO");
                }
                break;
            case 3:
                Utilities.P("Introduzca la nueva foto: ");
                String newfoto = keyboard.next();
                a.setFoto(newfoto);
                if (controlador.editUser(a)) {
                    Utilities.P("EL USUARIO HA SIDO EDITADO CON EXITO");
                } else {
                    Utilities.P("HA OCURRIDO UN PROBLEMA EN LA EDICION DEL USUARIO");
                }
                break;

            case 4:
                Utilities.P("Saliendo del Menú de Edición Usuario.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    //___________________________________________________________________________MenuEliminar
    private static void ControladorMenuEliminar(int op2) {

        switch (op2) {
            case 1:
                Utilities.P("Introduzca el ID del artista: ");
                int idArtistab = keyboard.nextInt();
                if (controlador.searchArtistaByID(idArtistab)) {
                    int opcion = 0;
                    Utilities.P("¿Estas seguro de que quieres eliminar? ");
                    Utilities.P("1- Si eliminar ");
                    Utilities.P("2- No eliminar ");

                    opcion = keyboard.nextInt();
                    if (opcion == 1) {

                        Artista a = controlador.getArtistsById(idArtistab);
                        List<Disco> disclist = a.getRepertorio();
                        for (Disco disco : disclist) {
                            List<Cancion> canclist = disco.getCanciones();
                            for (Cancion cancion : canclist) {
                                controlador.removeSongs(cancion);
                            }
                            controlador.removeDiscs(disco);
                        }
                        controlador.removeArtists(a);
                        Utilities.P("EL ARTISTA HA SIDO BORRADA CON EXITO");

                    } else if (opcion == 2) {
                        System.out.println("SALIENDO");
                    } else {
                        System.out.println("Introduzca una opcion valida");
                    }
                } else {
                    System.out.println("EL ID DEL ARTISTA NO EXISTE");
                }

                break;

            case 2:
                Utilities.P("Introduzca el ID del disco: ");
                int idiscb = keyboard.nextInt();
                if (controlador.searchDiscByID(idiscb)) {
                    int opcion = 0;
                    Utilities.P("¿Estas seguro de que quieres eliminar? ");
                    Utilities.P("1- Si eliminar ");
                    Utilities.P("2- No eliminar ");

                    opcion = keyboard.nextInt();
                    if (opcion == 1) {

                        Disco a = controlador.getDiscsById(idiscb);
                        List<Cancion> Cancionlist = a.getCanciones();
                        for (Cancion cancion : Cancionlist) {

                            controlador.removeSongs(cancion);
                        }
                        controlador.removeDiscs(a);
                        Utilities.P("EL DISCO HA SIDO BORRADA CON EXITO");

                    } else if (opcion == 2) {
                        System.out.println("SALIENDO");
                    } else {
                        System.out.println("Introduzca una opcion valida");
                    }
                } else {
                    System.out.println("EL ID DEL DISCO NO EXISTE");
                }

                break;

            case 3:

                Utilities.P("Introduzca el ID de la cancion: ");
                int idCancionb = keyboard.nextInt();
                if (controlador.searchSongByID(idCancionb)) {
                    Cancion c = controlador.getSongsById(idCancionb);
                    controlador.removeSongs(c);
                    Utilities.P("LA CANCION HA SIDO BORRADA CON EXITO");

                } else {
                    System.out.println("EL ID DE LA CANCION NO EXISTE");
                }

                break;

            case 4:
                Utilities.P("Introduzca el ID de la lista: ");
                int idListab = keyboard.nextInt();
                if (controlador.searchListByID(idListab)) {
                    Lista l = controlador.getListById(idListab);
                    controlador.removeList(l);
                    Utilities.P("LA LISTA HA SIDO BORRADA CON EXITO");

                } else {
                    System.out.println("EL ID DE LA LISTA NO EXISTE");
                }

                break;

            case 5:
                Utilities.P("Introduzca el ID de la cancion: ");
                int idcab = keyboard.nextInt();
                if (controlador.searchSongByID(idcab)) {
                    Cancion u = controlador.getSongsById(idcab);
                    controlador.removesongofList(u);
                    Utilities.P("LA CANCION HA SIDO BORRADA CON EXITO");

                } else {
                    System.out.println("EL ID DE LA CANCION NO EXISTE");
                }

                break;
            case 6:
                Utilities.P("Introduzca el ID de la lista: ");
                int idli = keyboard.nextInt();
                if (controlador.searchListByID(idli)) {
                    Utilities.P("Introduzca el ID del subscriptor: ");
                    int idusub = keyboard.nextInt();
                    if (controlador.searchUserByID(idusub)) {
                        Lista l = controlador.getListById(idli);
                        Usuario s = controlador.getUserById(idusub);
                        Subscripcion u = new Subscripcion(l, s);
                        controlador.removeSubs(u);
                        Utilities.P("LA SUBSCRIPCION HA SIDO BORRADA CON EXITO");
                    } else {
                        System.out.println("EL ID DEL USUARIO NO EXISTE");
                    }

                } else {
                    System.out.println("EL ID DE LA LISTA NO EXISTE");
                }
                break;

            case 7:
                Utilities.P("Introduzca el ID del usuario: ");
                int iduserb = keyboard.nextInt();
                if (controlador.searchUserByID(iduserb)) {
                    Usuario u = controlador.getUserById(iduserb);
                    controlador.removeUser(u);
                    Utilities.P("EL USUARIO HA SIDO BORRADA CON EXITO");

                } else {
                    System.out.println("EL ID DEL USUARIO NO EXISTE");
                }

                break;
            case 8:
                Utilities.P("Saliendo del Menú de Eliminación.");
                break;
            default:

                Utilities.P("Opción no válida, vuelve a intentarlo.");
                break;

        }
    }

}

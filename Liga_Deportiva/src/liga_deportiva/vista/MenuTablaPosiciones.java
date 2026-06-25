package liga_deportiva.vista;

import java.util.Scanner;
import java.util.List;
import liga_deportiva.modelo.Torneo;

import liga_deportiva.modelo.TablaPosiciones;
import liga_deportiva.modelo.PosicionEquipo;

import liga_deportiva.controlador.TablaPosicionesControlador;
import liga_deportiva.controlador.TorneoControlador;

public class MenuTablaPosiciones {

    private Scanner scanner;

    private TablaPosicionesControlador tablaPosicionesControlador;
    private TorneoControlador torneoControlador;

    public MenuTablaPosiciones(
            TablaPosicionesControlador tablaPosicionesControlador,
            TorneoControlador torneoControlador) {

        this.scanner = new Scanner(System.in);

        this.tablaPosicionesControlador =
                tablaPosicionesControlador;

        this.torneoControlador =
                torneoControlador;
    }

    public void mostrarMenuTablaPosiciones() {

        int opcion;

        do {

            mostrarEncabezado(
                    "TABLA DE POSICIONES");

            System.out.println(
                    "1. Buscar tabla por torneo");

            System.out.println(
                    "2. Recalcular tabla");

            System.out.println(
                    "3. Listar tablas");

            System.out.println();

            System.out.println(
                    "--------------------------------------------------");

            System.out.println(
                    "0. Volver al menú principal");

            System.out.println(
                    "==================================================");

            opcion =
                    leerEntero(
                            "Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    buscarTabla();
                    break;

                case 2:
                    recalcularTabla();
                    break;

                case 3:
                    listarTablas();
                    break;

                case 0:
                    break;

                default:

                    mostrarError(
                            "Opción no válida.");

                    pausarPantalla();
            }

        } while (opcion != 0);
    }
    
    // ==================================================
    // MÉTODOS AUXILIARES Y VALIDACIONES
    // ==================================================

    private void mostrarEncabezado(String titulo) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println(titulo);
        System.out.println("==================================================");
        System.out.println();
    }

    private String leerTextoObligatorio(String mensaje) {

        String texto;

        do {

            System.out.print(mensaje);
            texto = scanner.nextLine().trim();

            if (texto.isEmpty()) {

                mostrarError(
                        "Este campo es obligatorio.");
            }

        } while (texto.isEmpty());

        return texto;
    }
    
    private String leerCodigo(String mensaje) {

        String codigo;

        do {

            System.out.print(mensaje);

        codigo = scanner.nextLine().trim();

        if (codigo.isEmpty()) {

            mostrarError(
                    "El código es obligatorio."
            );

            continue;
        }

        if (!codigo.matches("[A-Za-z0-9]+")) {

            mostrarError(
                    "El código solo puede contener letras y números."
            );

            codigo = "";
        }

        } while (codigo.isEmpty());

        return codigo;
    }

    private int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Integer.parseInt(
                        scanner.nextLine());

            } catch (NumberFormatException e) {

                mostrarError(
                        "Debe ingresar un número entero válido.");
            }
        }
    }

    private void mostrarError(String mensaje) {

        System.out.println(
                "[ERROR] " + mensaje);
    }

    private void mostrarExito() {

        System.out.println(
                "[SUCCESS] Operación realizada correctamente.");
    }

    private void pausarPantalla() {

        System.out.println();

        System.out.print(
                "Presione ENTER para continuar...");

        scanner.nextLine();
    }
    
    // ==================================================
    // CAPTURA DE DATOS
    // ==================================================

    private String solicitarCodigoTorneo() {

        mostrarEncabezado(
                "BÚSQUEDA DE TABLA");

        return leerCodigo(
                "Código del torneo: ");
    }

    // ==================================================
    // VISUALIZACIÓN
    // ==================================================

    private void mostrarTabla(
            TablaPosiciones tabla) {

        if (tabla == null) {

            mostrarError(
                    "No se encontró la tabla.");

            return;
        }

        mostrarEncabezado(
                "TABLA DE POSICIONES");
        
        if (tabla.getTorneo() == null) {

        mostrarError(
            "La tabla no tiene torneo asociado.");

        return;
        }

        System.out.println(
                "Torneo: "
                + tabla.getTorneo().getNombre());

        System.out.println();

        List<PosicionEquipo> posiciones =
                tabla.obtenerPosiciones();

        if (posiciones.isEmpty()) {

            mostrarError(
                    "No existen posiciones registradas.");

            return;
        }

        System.out.printf(
                "%-4s %-25s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-5s%n",
                "POS",
                "EQUIPO",
                "PJ",
                "PG",
                "PE",
                "PP",
                "GF",
                "GC",
                "DG",
                "PTS"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        int posicion = 1;

        for (PosicionEquipo equipo
                : posiciones) {
            
            if (equipo == null
            || equipo.getEquipo() == null) {
                continue;
            }

            System.out.printf(
                    "%-4d %-25s %-4d %-4d %-4d %-4d %-4d %-4d %-4d %-5d%n",
                    posicion++,
                    equipo.getEquipo().getNombre(),
                    equipo.getPartidosJugados(),
                    equipo.getPartidosGanados(),
                    equipo.getPartidosEmpatados(),
                    equipo.getPartidosPerdidos(),
                    equipo.getGolesFavor(),
                    equipo.getGolesContra(),
                    equipo.getDiferenciaGoles(),
                    equipo.getPuntos()
            );
        }
    }

    private void mostrarListaTablas(
            List<TablaPosiciones> tablas) {

        if (tablas.isEmpty()) {

            mostrarError(
                    "No existen tablas registradas.");

            return;
        }

        mostrarEncabezado(
                "LISTADO DE TABLAS");

        System.out.printf(
                "%-10s %-35s%n",
                "CÓDIGO",
                "TORNEO"
        );

        System.out.println(
                "------------------------------------------------"
        );

        for (TablaPosiciones tabla
                : tablas) {
            
            if (tabla == null
            || tabla.getTorneo() == null) {
            continue;
            }

            System.out.printf(
                    "%-10s %-35s%n",
                    tabla.getTorneo().getCodigo(),
                    tabla.getTorneo().getNombre()
            );
        }
    }

    // ==================================================
    // LISTADOS
    // ==================================================

    private void listarTablas() {

        mostrarListaTablas(
                tablaPosicionesControlador
                        .listarTablas());

        pausarPantalla();
    }
    
    // ==================================================
    // LÓGICA DE OPCIONES
    // ==================================================

    private void buscarTabla() {

        String codigoTorneo =
                solicitarCodigoTorneo();

        Torneo torneo =
                torneoControlador
                        .buscarTorneoPorCodigo(
                                codigoTorneo);

        if (torneo == null) {

            mostrarError(
                    "No existe el torneo.");

            pausarPantalla();
            return;
        }

        TablaPosiciones tabla =
                tablaPosicionesControlador
                        .buscarTablaPorTorneo(
                                torneo);

        mostrarTabla(tabla);

        pausarPantalla();
    }

    private void recalcularTabla() {

        mostrarEncabezado(
                "RECALCULAR TABLA");

        String codigoTorneo =
                leerTextoObligatorio(
                        "Código del torneo: ");

        Torneo torneo =
                torneoControlador
                        .buscarTorneoPorCodigo(
                                codigoTorneo);

        if (torneo == null) {

            mostrarError(
                    "No existe el torneo.");

            pausarPantalla();
            return;
        }

        TablaPosiciones tabla =
                tablaPosicionesControlador
                        .buscarTablaPorTorneo(
                                torneo);

        if (tabla == null) {

            mostrarError(
                    "No existe la tabla.");

            pausarPantalla();
            return;
        }

        if (tablaPosicionesControlador
                .recalcularTabla(tabla)) {

            mostrarExito();

            System.out.println();
            mostrarTabla(tabla);

        } else {

            mostrarError(
                    "No fue posible recalcular la tabla.");
        }

        pausarPantalla();
    }

}


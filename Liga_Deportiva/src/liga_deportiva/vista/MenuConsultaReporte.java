package liga_deportiva.vista;

import java.util.Scanner;
import java.util.List;

import liga_deportiva.modelo.Jugador;
import liga_deportiva.modelo.Partido;
import liga_deportiva.modelo.TablaPosiciones;
import liga_deportiva.modelo.PosicionEquipo;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.controlador.ConsultaReporteControlador;

public class MenuConsultaReporte {

    private Scanner scanner;
    private ConsultaReporteControlador consultaReporteControlador;

    public MenuConsultaReporte(
            ConsultaReporteControlador consultaReporteControlador) {

        this.scanner = new Scanner(System.in);
        this.consultaReporteControlador =
                consultaReporteControlador;
    }

    public void mostrarMenuConsultaReporte() {

        int opcion;

        do {

            mostrarEncabezado(
                    "CONSULTAS Y REPORTES");

            System.out.println(
                    "1. Consultar jugadores por equipo");

            System.out.println(
                    "2. Consultar historial de partidos de un equipo");

            System.out.println(
                    "3. Consultar tabla de posiciones");

            System.out.println(
                    "4. Consultar estadísticas de jugador");

            System.out.println(
                    "5. Consultar equipo con más victorias");

            System.out.println(
                    "6. Consultar equipo con más goles");

            System.out.println(
                    "7. Consultar máximo goleador");

            System.out.println(
                    "8. Consultar total de goles por torneo");

            System.out.println(
                    "9. Reporte equipo con más victorias");

            System.out.println(
                    "10. Reporte tabla de posiciones");

            System.out.println(
                    "11. Reporte estadísticas de equipos");

            System.out.println(
                    "12. Reporte estadísticas de jugadores");

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
                    consultarJugadoresPorEquipo();
                    break;

                case 2:
                    consultarHistorialEquipo();
                    break;

                case 3:
                    consultarTablaPosiciones();
                    break;

                case 4:
                    consultarEstadisticasJugador();
                    break;

                case 5:
                    consultarEquipoMasVictorias();
                    break;

                case 6:
                    consultarEquipoMasGoles();
                    break;

                case 7:
                    consultarMaximoGoleador();
                    break;

                case 8:
                    consultarTotalGolesTorneo();
                    break;

                case 9:
                    reporteEquipoMasVictorias();
                    break;

                case 10:
                    reporteTablaPosiciones();
                    break;

                case 11:
                    reporteEstadisticasEquipos();
                    break;

                case 12:
                    reporteEstadisticasJugadores();
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

    private String solicitarCodigoEquipo() {

        return leerTextoObligatorio(
                "Código del equipo: ");
    }

    private String solicitarCodigoJugador() {

        return leerTextoObligatorio(
                "Código del jugador: ");
    }

    private String solicitarCodigoTorneo() {

        return leerTextoObligatorio(
                "Código del torneo: ");
    }

    // ==================================================
    // VISUALIZACIÓN
    // ==================================================

    private void mostrarJugadores(
            List<Jugador> jugadores) {

        if (jugadores == null
        || jugadores.isEmpty()) {

            mostrarError(
                    "No existen jugadores registrados.");

            return;
        }

        System.out.printf(
                "%-10s %-25s %-8s%n",
                "CÓDIGO",
                "NOMBRE",
                "EDAD"
        );

        System.out.println(
                "------------------------------------------------"
        );

        for (Jugador jugador : jugadores) {

            System.out.printf(
                    "%-10s %-25s %-8d%n",
                    jugador.getCodigo(),
                    jugador.getNombre(),
                    jugador.getEdad()
            );
        }
    }

    private void mostrarPartidos(
            List<Partido> partidos) {

        if (partidos == null
        || partidos.isEmpty())  {

            mostrarError(
                    "No existen partidos registrados.");

            return;
        }

        System.out.printf(
                "%-10s %-15s %-20s %-20s%n",
                "CÓDIGO",
                "FECHA",
                "LOCAL",
                "VISITANTE"
        );

        System.out.println(
                "------------------------------------------------------------------"
        );

        for (Partido partido : partidos) {

            System.out.printf(
                    "%-10s %-15s %-20s %-20s%n",
                    partido.getCodigo(),
                    partido.getFecha(),
                    partido.getEquipoLocal()
                            .getNombre(),
                    partido.getEquipoVisitante()
                            .getNombre()
            );
        }
    }

    private void mostrarTablaPosiciones(
            TablaPosiciones tabla) {

        if (tabla == null) {

            mostrarError(
                    "No existe la tabla de posiciones.");

            return;
        }

        System.out.println(
                "Torneo: "
                        + tabla.getTorneo()
                                .getNombre());

        System.out.println();

        System.out.printf(
                "%-4s %-25s %-5s%n",
                "POS",
                "EQUIPO",
                "PTS"
        );

        System.out.println(
                "----------------------------------------------"
        );

        int posicion = 1;
        
        if (tabla.obtenerPosiciones() == null
        || tabla.obtenerPosiciones().isEmpty()) {

        mostrarError(
            "No existen posiciones registradas.");

        return;
        }

        for (PosicionEquipo equipo
                : tabla.obtenerPosiciones()) {

            System.out.printf(
                    "%-4d %-25s %-5d%n",
                    posicion++,
                    equipo.getEquipo()
                            .getNombre(),
                    equipo.getPuntos()
            );
        }
    }

    private void mostrarReporte(
            String reporte) {

        if (reporte == null
                || reporte.trim().isEmpty()) {

            mostrarError(
                    "No existen datos para mostrar.");

            return;
        }

        System.out.println();
        System.out.println(reporte);
    }

    // ==================================================
    // LÓGICA DE OPCIONES
    // ==================================================

    private void consultarJugadoresPorEquipo() {

        mostrarEncabezado(
                "JUGADORES POR EQUIPO");

        String codigoEquipo =
                solicitarCodigoEquipo();

        List<Jugador> jugadores =
                consultaReporteControlador
                        .listarJugadoresPorEquipo(
                                codigoEquipo);

        mostrarJugadores(jugadores);

        pausarPantalla();
    }

    private void consultarHistorialEquipo() {

        mostrarEncabezado(
                "HISTORIAL DE PARTIDOS");

        String codigoEquipo =
                solicitarCodigoEquipo();

        List<Partido> partidos =
                consultaReporteControlador
                        .obtenerHistorialPartidosEquipo(
                                codigoEquipo);

        mostrarPartidos(partidos);

        pausarPantalla();
    }

    private void consultarTablaPosiciones() {

        mostrarEncabezado(
                "TABLA DE POSICIONES");

        String codigoTorneo =
                solicitarCodigoTorneo();

        TablaPosiciones tabla =
                consultaReporteControlador
                        .obtenerTablaPosiciones(
                                codigoTorneo);

        mostrarTablaPosiciones(tabla);

        pausarPantalla();
    }

    private void consultarEstadisticasJugador() {

        mostrarEncabezado(
                "ESTADÍSTICAS DE JUGADOR");

        String codigoJugador =
                solicitarCodigoJugador();

        String estadisticas =
                consultaReporteControlador
                        .obtenerEstadisticasJugador(
                                codigoJugador);

        mostrarReporte(estadisticas);

        pausarPantalla();
    }

    private void consultarEquipoMasVictorias() {

        mostrarEncabezado(
                "EQUIPO CON MÁS VICTORIAS");

        Equipo equipo =
                consultaReporteControlador
                        .obtenerEquipoMasVictorias();

        if (equipo == null) {

            mostrarError(
                    "No existen datos registrados.");

        } else {

            System.out.println(
                    "Equipo: "
                            + equipo.getNombre());
        }

        pausarPantalla();
    }

    private void consultarEquipoMasGoles() {

        mostrarEncabezado(
                "EQUIPO CON MÁS GOLES");

        Equipo equipo =
                consultaReporteControlador
                        .obtenerEquipoMasGoles();

        if (equipo == null) {

            mostrarError(
                    "No existen datos registrados.");

        } else {

            System.out.println(
                    "Equipo: "
                            + equipo.getNombre());
        }

        pausarPantalla();
    }

    private void consultarMaximoGoleador() {

        mostrarEncabezado(
                "MÁXIMO GOLEADOR");

        Jugador jugador =
                consultaReporteControlador
                        .obtenerMaximoGoleador();

        if (jugador == null) {

            mostrarError(
                    "No existen datos registrados.");

        } else {

            System.out.println(
                    "Jugador: "
                            + jugador.getNombre());
        }

        pausarPantalla();
    }

    private void consultarTotalGolesTorneo() {

        mostrarEncabezado(
                "TOTAL DE GOLES POR TORNEO");

        String codigoTorneo =
                solicitarCodigoTorneo();

        int totalGoles =
                consultaReporteControlador
                        .obtenerTotalGolesTorneo(
                                codigoTorneo);

        System.out.println(
                "Total de goles: "
                        + totalGoles);

        pausarPantalla();
    }

    private void reporteEquipoMasVictorias() {

        mostrarEncabezado(
                "REPORTE EQUIPO CON MÁS VICTORIAS");

        mostrarReporte(
                consultaReporteControlador
                        .generarReporteEquipoMasVictorias());

        pausarPantalla();
    }

    private void reporteTablaPosiciones() {

        mostrarEncabezado(
                "REPORTE TABLA DE POSICIONES");

        String codigoTorneo =
                solicitarCodigoTorneo();

        mostrarReporte(
                consultaReporteControlador
                        .generarReporteTablaPosiciones(
                                codigoTorneo));

        pausarPantalla();
    }

    private void reporteEstadisticasEquipos() {

        mostrarEncabezado(
                "REPORTE ESTADÍSTICAS DE EQUIPOS");

        mostrarReporte(
                consultaReporteControlador
                        .generarReporteEstadisticasEquipos());

        pausarPantalla();
    }

    private void reporteEstadisticasJugadores() {

        mostrarEncabezado(
                "REPORTE ESTADÍSTICAS DE JUGADORES");

        mostrarReporte(
                consultaReporteControlador
                        .generarReporteEstadisticasJugadores());

        pausarPantalla();
    }

}



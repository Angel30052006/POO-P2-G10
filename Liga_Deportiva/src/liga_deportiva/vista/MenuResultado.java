package liga_deportiva.vista;

import java.util.Scanner;

import java.util.List;

import liga_deportiva.modelo.Partido;
import liga_deportiva.modelo.Jugador;
import liga_deportiva.modelo.EventoJugador;
import liga_deportiva.modelo.ResultadoPartido;

import liga_deportiva.controlador.ResultadoControlador;
import liga_deportiva.controlador.PartidoControlador;
import liga_deportiva.controlador.JugadorControlador;

public class MenuResultado {

    private Scanner scanner;

    private ResultadoControlador resultadoControlador;
    private PartidoControlador partidoControlador;
    private JugadorControlador jugadorControlador;

    public MenuResultado(
            ResultadoControlador resultadoControlador,
            PartidoControlador partidoControlador,
            JugadorControlador jugadorControlador) {

        this.scanner = new Scanner(System.in);

        this.resultadoControlador = resultadoControlador;
        this.partidoControlador = partidoControlador;
        this.jugadorControlador = jugadorControlador;
    }

    public void mostrarMenuResultados() {

        int opcion;

        do {

            mostrarEncabezado(
                    "GESTIÓN DE RESULTADOS");

            System.out.println(
                    "1. Registrar resultado");

            System.out.println(
                    "2. Corregir resultado");

            System.out.println(
                    "3. Registrar evento de jugador");

            System.out.println(
                    "4. Buscar resultado por partido");

            System.out.println(
                    "5. Listar resultados");

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
                    registrarResultado();
                    break;

                case 2:
                    corregirResultado();
                    break;

                case 3:
                    registrarEventoJugador();
                    break;

                case 4:
                    buscarResultado();
                    break;

                case 5:
                    listarResultados();
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
    
    private int leerEnteroNoNegativo(
        String mensaje) {

        int numero;

        do {

        numero = leerEntero(mensaje);

        if (numero < 0) {

            mostrarError(
                    "No se permiten valores negativos.");
        }

        } while (numero < 0);

        return numero;
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

    private String solicitarCodigoPartido() {

        mostrarEncabezado(
                "BÚSQUEDA DE RESULTADO");

        return leerCodigo(
                "Código del partido: ");
    }

    // ==================================================
    // VISUALIZACIÓN
    // ==================================================

    private void mostrarResultado(
            ResultadoPartido resultado) {

        if (resultado == null) {

            mostrarError(
                    "No se encontró el resultado.");

            return;
        }

        mostrarEncabezado(
                "DATOS DEL RESULTADO");

        System.out.println(
                "Partido           : "
                        + resultado.getPartido()
                                .getCodigo());

        System.out.println(
                "Equipo Local      : "
                        + resultado.getPartido()
                                .getEquipoLocal()
                                .getNombre());

        System.out.println(
                "Equipo Visitante  : "
                        + resultado.getPartido()
                                .getEquipoVisitante()
                                .getNombre());

        System.out.println(
                "Goles Local       : "
                        + resultado
                                .obtenerGolesEquipoLocal());

        System.out.println(
                "Goles Visitante   : "
                        + resultado
                                .obtenerGolesEquipoVisitante());

        System.out.println(
                "Ganador           : "
                        + resultado
                                .determinarGanador());
    }

    private void mostrarListaResultados(
            List<ResultadoPartido> resultados) {

        if (resultados.isEmpty()) {

            mostrarError(
                    "No existen resultados registrados.");

            return;
        }

        mostrarEncabezado(
                "LISTADO DE RESULTADOS");

        System.out.printf(
                "%-8s %-20s %-20s %-8s %-8s%n",
                "PARTIDO",
                "LOCAL",
                "VISITANTE",
                "GL",
                "GV"
        );

        System.out.println(
                "---------------------------------------------------------------------"
        );

        for (ResultadoPartido resultado
                : resultados) {

            System.out.printf(
                    "%-8s %-20s %-20s %-8d %-8d%n",
                    resultado.getPartido()
                            .getCodigo(),

                    resultado.getPartido()
                            .getEquipoLocal()
                            .getNombre(),

                    resultado.getPartido()
                            .getEquipoVisitante()
                            .getNombre(),

                    resultado
                            .obtenerGolesEquipoLocal(),

                    resultado
                            .obtenerGolesEquipoVisitante()
            );
        }
    }

    // ==================================================
    // LISTADOS
    // ==================================================

    private void listarResultados() {

        mostrarListaResultados(
                resultadoControlador
                        .listarResultados());

        pausarPantalla();
    }

    // ==================================================
    // LÓGICA DE OPCIONES
    // ==================================================

    private void registrarResultado() {

        mostrarEncabezado(
                "REGISTRAR RESULTADO");

        String codigoPartido =
                leerCodigo(
                        "Código del partido: ");

        Partido partido =
                partidoControlador
                        .buscarPartidoPorCodigo(
                                codigoPartido);

        if (partido == null) {

            mostrarError(
                    "No existe el partido.");

            pausarPantalla();
            return;
        }

        int golesLocal =
                leerEnteroNoNegativo(
                        "Goles equipo local: ");

        int golesVisitante =
                leerEnteroNoNegativo(
                        "Goles equipo visitante: ");

        ResultadoPartido resultado =
                new ResultadoPartido(
                        partido,
                        golesLocal,
                        golesVisitante
                );

        if (resultadoControlador
                .registrarResultado(resultado)) {

            mostrarExito();

        } else {

            mostrarError(
                    "No fue posible registrar el resultado.");
        }

        pausarPantalla();
    }

    private void corregirResultado() {

        mostrarEncabezado(
                "CORREGIR RESULTADO");

        String codigoPartido =
                leerCodigo(
                        "Código del partido: ");

        Partido partido =
                partidoControlador
                        .buscarPartidoPorCodigo(
                                codigoPartido);

        if (partido == null) {

            mostrarError(
                    "No existe el partido.");

            pausarPantalla();
            return;
        }

        int golesLocal =
                leerEnteroNoNegativo(
                        "Nuevo marcador local: ");

        int golesVisitante =
                leerEnteroNoNegativo(
                        "Nuevo marcador visitante: ");

        ResultadoPartido resultadoCorregido =
                new ResultadoPartido(
                        partido,
                        golesLocal,
                        golesVisitante
                );

        if (resultadoControlador
                .corregirResultado(
                        resultadoCorregido)) {

            mostrarExito();

        } else {

            mostrarError(
                    "No fue posible corregir el resultado.");
        }

        pausarPantalla();
    }

    private void registrarEventoJugador() {

        mostrarEncabezado(
                "REGISTRAR EVENTO DE JUGADOR");

        String codigoPartido =
                leerCodigo(
                        "Código del partido: ");

        Partido partido =
                partidoControlador
                        .buscarPartidoPorCodigo(
                                codigoPartido);

        if (partido == null) {

            mostrarError(
                    "No existe el partido.");

            pausarPantalla();
            return;
        }

        String codigoJugador =
                leerCodigo(
                        "Código del jugador: ");

        Jugador jugador =
                jugadorControlador
                        .buscarJugadorPorCodigo(
                                codigoJugador);

        if (jugador == null) {

            mostrarError(
                    "No existe el jugador.");

            pausarPantalla();
            return;
        }

        EventoJugador evento =
                new EventoJugador(jugador);

        int goles =
                leerEnteroNoNegativo(
                        "Cantidad de goles: ");

        int amarillas =
                leerEnteroNoNegativo(
                        "Tarjetas amarillas: ");

        int rojas =
                leerEnteroNoNegativo(
                        "Tarjetas rojas: ");

        int faltas =
                leerEnteroNoNegativo(
                        "Cantidad de faltas: ");

        for (int i = 0; i < goles; i++) {
            evento.registrarGol();
        }

        for (int i = 0; i < amarillas; i++) {
            evento.registrarAmarilla();
        }

        for (int i = 0; i < rojas; i++) {
            evento.registrarRoja();
        }

        for (int i = 0; i < faltas; i++) {
            evento.registrarFalta();
        }

        if (resultadoControlador
                .agregarEventoJugador(
                        partido,
                        evento)) {

            mostrarExito();

        } else {

            mostrarError(
                    "No fue posible registrar el evento.");
        }

        pausarPantalla();
    }

    private void buscarResultado() {

        String codigoPartido =
                solicitarCodigoPartido();

        Partido partido =
                partidoControlador
                        .buscarPartidoPorCodigo(
                                codigoPartido);

        if (partido == null) {

            mostrarError(
                    "No existe el partido.");

            pausarPantalla();
            return;
        }

        ResultadoPartido resultado =
                resultadoControlador
                        .buscarResultadoPorPartido(
                                partido);

        mostrarResultado(resultado);

        pausarPantalla();
    }

}


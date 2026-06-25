package liga_deportiva.vista;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import liga_deportiva.controlador.PartidoControlador;
import liga_deportiva.controlador.ArbitroControlador;
import liga_deportiva.controlador.TorneoControlador;
import liga_deportiva.controlador.EquipoControlador;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.modelo.Arbitro;
import liga_deportiva.modelo.Torneo;
import liga_deportiva.modelo.Estadio;
import liga_deportiva.modelo.EstadoPartido;

import liga_deportiva.modelo.Partido;

public class MenuPartido {

   
    private Scanner scanner;
    private PartidoControlador partidoControlador;
    private ArbitroControlador arbitroControlador;
    private TorneoControlador torneoControlador;
    private EquipoControlador equipoControlador;

    public MenuPartido(
        PartidoControlador partidoControlador,
        EquipoControlador equipoControlador,
        ArbitroControlador arbitroControlador,
        TorneoControlador torneoControlador) {

        this.scanner = new Scanner(System.in);
        this.partidoControlador = partidoControlador;
        this.equipoControlador = equipoControlador;
        this.arbitroControlador = arbitroControlador;
        this.torneoControlador = torneoControlador;
    }
    public void mostrarMenuPartidos() {

        int opcion;

        do {

            mostrarEncabezado("GESTIÓN DE PARTIDOS");

            System.out.println("1. Registrar partido");
            System.out.println("2. Buscar partido por código");
            System.out.println("3. Modificar partido");
            System.out.println("4. Cambiar estado de partido");
            System.out.println("5. Listar partidos");
            System.out.println("6. Listar partidos programados");
            System.out.println("7. Listar partidos finalizados");

            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println("0. Volver al menú principal");
            System.out.println("==================================================");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    registrarPartido();
                    break;

                case 2:
                    buscarPartido();
                    break;

                case 3:
                    modificarPartido();
                    break;

                case 4:
                    cambiarEstadoPartido();
                    break;

                case 5:
                    listarPartidos();
                    break;

                case 6:
                    listarPartidosProgramados();
                    break;

                case 7:
                    listarPartidosFinalizados();
                    break;

                case 0:
                    break;

                default:
                    mostrarError("Opción no válida.");
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
                mostrarError("Este campo es obligatorio.");
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
    
    
    private LocalDate leerFecha(String mensaje) {

    while (true) {

        try {

            return LocalDate.parse(
                    leerTextoObligatorio(mensaje));

        } catch (DateTimeParseException e) {

            mostrarError(
                    "Formato inválido. Use AAAA-MM-DD."
            );
        }
    }
}
    private LocalTime leerHora(String mensaje) {

    while (true) {

        try {

            return LocalTime.parse(
                    leerTextoObligatorio(mensaje));

        } catch (DateTimeParseException e) {

            mostrarError(
                    "Formato inválido. Use HH:MM."
            );
        }
    }
}

    private int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                mostrarError("Debe ingresar un número entero válido.");
            }
        }
    }

    private void mostrarError(String mensaje) {
        System.out.println("[ERROR] " + mensaje);
    }

    private void mostrarExito() {
        System.out.println("[SUCCESS] Operación realizada correctamente.");
    }

    private void pausarPantalla() {

        System.out.println();
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }
    
    // ==================================================
    // CAPTURA DE DATOS
    // ==================================================

    private String solicitarCodigoPartido() {

        mostrarEncabezado("BÚSQUEDA DE PARTIDO");

        return leerCodigo(
                "Código del partido: ");
    }

    // ==================================================
    // VISUALIZACIÓN
    // ==================================================

    private void mostrarPartido(Partido partido) {

        if (partido == null) {

            mostrarError("No se encontró el partido.");
            return;
        }
        
        if (partido.getTorneo() == null
        || partido.getEstadio() == null
        || partido.getEquipoLocal() == null
        || partido.getEquipoVisitante() == null
        || partido.getArbitro() == null) {

        mostrarError(
            "El partido contiene datos incompletos."
            );

            return;
        }

        mostrarEncabezado("DATOS DEL PARTIDO");

        System.out.println("Código           : "
                + partido.getCodigo());

        System.out.println("Fecha            : "
                + partido.getFecha());

        System.out.println("Hora             : "
                + partido.getHora());

        System.out.println("Estado           : "
                + partido.getEstado());

        System.out.println("Torneo           : "
                + partido.getTorneo().getNombre());

        System.out.println("Estadio          : "
                + partido.getEstadio().getNombre());

        System.out.println("Equipo Local     : "
                + partido.getEquipoLocal().getNombre());

        System.out.println("Equipo Visitante : "
                + partido.getEquipoVisitante().getNombre());

        System.out.println("Árbitro          : "
                + partido.getArbitro().getNombre());
    }

    private void mostrarListaPartidos(
            List<Partido> partidos) {

        if (partidos == null
        || partidos.isEmpty())  {

            mostrarError(
                    "No existen partidos registrados.");

            return;
        }

        mostrarEncabezado("LISTADO DE PARTIDOS");

        System.out.printf(
                "%-8s %-12s %-8s %-15s %-20s %-20s%n",
                "CÓDIGO",
                "FECHA",
                "HORA",
                "ESTADO",
                "LOCAL",
                "VISITANTE"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Partido partido : partidos) {

            System.out.printf(
                    "%-8s %-12s %-8s %-15s %-20s %-20s%n",
                    partido.getCodigo(),
                    partido.getFecha(),
                    partido.getHora(),
                    partido.getEstado(),
                    partido.getEquipoLocal().getNombre(),
                    partido.getEquipoVisitante().getNombre()
            );
        }
    }

    // ==================================================
    // LISTADOS
    // ==================================================

    private void listarPartidos() {

        mostrarListaPartidos(
                partidoControlador.listarPartidos());

        pausarPantalla();
    }

    private void listarPartidosProgramados() {

        mostrarEncabezado(
                "PARTIDOS PROGRAMADOS");

        mostrarListaPartidos(
                partidoControlador
                        .listarPartidosProgramados());

        pausarPantalla();
    }

    private void listarPartidosFinalizados() {

        mostrarEncabezado(
                "PARTIDOS FINALIZADOS");

        mostrarListaPartidos(
                partidoControlador
                        .listarPartidosFinalizados());

        pausarPantalla();
    }
    

    // ==================================================
    // LÓGICA DE OPCIONES
    // ==================================================

    private void registrarPartido() {

        mostrarEncabezado("REGISTRAR PARTIDO");

        try {

            String codigo =
                    leerCodigo(
                            "Código del partido: ");

            LocalDate fecha =
                    leerFecha(
                         "Fecha (AAAA-MM-DD): ");

            LocalTime hora =
                    leerHora(
                         "Hora (HH:MM): ");

            String codigoTorneo =
                    leerCodigo(
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
            
            
            if (fecha.isBefore(
                torneo.getFechaInicio())
                || fecha.isAfter(
                    torneo.getFechaFin())) {

                mostrarError(
                        "La fecha del partido debe estar dentro del rango del torneo."
                );

            pausarPantalla();
                return;
            }

            String codigoEstadio =
                    leerCodigo(
                            "Código del estadio: ");

            Estadio estadio =
                    partidoControlador
                            .buscarEstadioPorCodigo(
                                    codigoEstadio);

            if (estadio == null) {

                mostrarError(
                        "No existe el estadio.");

                pausarPantalla();
                return;
            }

            String codigoLocal =
                    leerCodigo(
                            "Código equipo local: ");

            Equipo equipoLocal =
                    equipoControlador
                            .buscarEquipoPorCodigo(
                                    codigoLocal);

            if (equipoLocal == null) {

                mostrarError(
                        "No existe el equipo local.");

                pausarPantalla();
                return;
            }

            String codigoVisitante =
                    leerCodigo(
                            "Código equipo visitante: ");

            Equipo equipoVisitante =
                    equipoControlador
                            .buscarEquipoPorCodigo(
                                    codigoVisitante);

            if (equipoVisitante == null) {

                mostrarError(
                        "No existe el equipo visitante.");

                pausarPantalla();
                return;
            }

            if (!partidoControlador
                    .validarEquiposDistintos(
                            equipoLocal,
                            equipoVisitante)) {

                mostrarError(
                        "Los equipos deben ser distintos.");

                pausarPantalla();
                return;
            }

            String codigoArbitro =
                    leerCodigo(
                            "Código del árbitro: ");

            Arbitro arbitro =
                    arbitroControlador
                            .buscarArbitroPorCodigo(
                                    codigoArbitro);

            if (arbitro == null) {

                mostrarError(
                        "No existe el árbitro.");

                pausarPantalla();
                return;
            }

            Partido partido =
                    new Partido(
                            codigo,
                            fecha,
                            hora,
                            EstadoPartido.PROGRAMADO,
                            torneo,
                            estadio,
                            equipoLocal,
                            equipoVisitante,
                            arbitro
                    );

            if (partidoControlador
                    .registrarPartido(partido)) {

                mostrarExito();

            } else {

                mostrarError(
                        "No fue posible registrar el partido.");
            }

        } catch (DateTimeParseException e) {

            mostrarError(
                    "Fecha u hora con formato inválido.");
        }

        pausarPantalla();
    }

    private void modificarPartido() {

        mostrarEncabezado(
                "MODIFICAR PARTIDO");

        try {

            String codigo =
                    leerCodigo(
                            "Código del partido: ");

            Partido existente =
                    partidoControlador
                            .buscarPartidoPorCodigo(
                                    codigo);

            if (existente == null) {

                mostrarError(
                        "No existe el partido.");

                pausarPantalla();
                return;
            }

            if (existente.estaFinalizado()) {

                mostrarError(
                        "No se puede modificar un partido finalizado.");

                pausarPantalla();
                return;
            }

            LocalDate fecha =
                    leerFecha(
                         "Fecha (AAAA-MM-DD): ");

           
 
            if (fecha.isBefore(
                    existente.getTorneo()
                .getFechaInicio())
                    || fecha.isAfter(
                    existente.getTorneo()
                        .getFechaFin())) {

                mostrarError(
                        "La fecha debe estar dentro del rango del torneo."
            );

                pausarPantalla();
                return;
            }
            
             LocalTime hora =
                    leerHora(
                         "Hora (HH:MM): ");
             
            Partido partidoModificado =
                    new Partido(
                            existente.getCodigo(),
                            fecha,
                            hora,
                            existente.getEstado(),
                            existente.getTorneo(),
                            existente.getEstadio(),
                            existente.getEquipoLocal(),
                            existente.getEquipoVisitante(),
                            existente.getArbitro()
                    );

            if (partidoControlador
                    .modificarPartido(
                            partidoModificado)) {

                mostrarExito();

            } else {

                mostrarError(
                        "No fue posible modificar el partido.");
            }

        } catch (DateTimeParseException e) {

            mostrarError(
                    "Fecha u hora con formato inválido.");
        }

        pausarPantalla();
    }

    private void buscarPartido() {

        String codigo =
                solicitarCodigoPartido();

        Partido partido =
                partidoControlador
                        .buscarPartidoPorCodigo(
                                codigo);

        mostrarPartido(partido);

        pausarPantalla();
    }

    private void cambiarEstadoPartido() {

        mostrarEncabezado(
                "CAMBIAR ESTADO DE PARTIDO");

        String codigo =
                leerCodigo(
                        "Código del partido: ");

        System.out.println();
        System.out.println("1. PROGRAMADO");
        System.out.println("2. RETRASADO");
        System.out.println("3. REPROGRAMADO");
        System.out.println("4. SUSPENDIDO");
        System.out.println("5. CANCELADO");
        System.out.println("6. EN_JUEGO");
        System.out.println("7. FINALIZADO");

        int opcion =
                leerEntero(
                        "Seleccione una opción: ");
        
        
        if (opcion < 1 || opcion > 7) {

        mostrarError(
            "Debe seleccionar una opción entre 1 y 7."
        );

        pausarPantalla();
        return;
        }

        EstadoPartido nuevoEstado = null;

        switch (opcion) {

            case 1:
                nuevoEstado =
                        EstadoPartido.PROGRAMADO;
                break;

            case 2:
                nuevoEstado =
                        EstadoPartido.RETRASADO;
                break;

            case 3:
                nuevoEstado =
                        EstadoPartido.REPROGRAMADO;
                break;

            case 4:
                nuevoEstado =
                        EstadoPartido.SUSPENDIDO;
                break;

            case 5:
                nuevoEstado =
                        EstadoPartido.CANCELADO;
                break;

            case 6:
                nuevoEstado =
                        EstadoPartido.EN_JUEGO;
                break;

            case 7:
                nuevoEstado =
                        EstadoPartido.FINALIZADO;
                break;

            default:
                mostrarError(
                        "Opción inválida.");
        }

        if (nuevoEstado != null) {

            if (partidoControlador
                    .cambiarEstado(
                            codigo,
                            nuevoEstado)) {

                mostrarExito();

            } else {

                mostrarError(
                        "No fue posible actualizar el estado.");
            }
        }

        pausarPantalla();
    }

}

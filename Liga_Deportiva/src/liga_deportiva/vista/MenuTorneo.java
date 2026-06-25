package liga_deportiva.vista;


import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

import liga_deportiva.controlador.TorneoControlador;
import liga_deportiva.controlador.EquipoControlador;
import liga_deportiva.controlador.PartidoControlador;

import liga_deportiva.modelo.Torneo;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.modelo.Partido;
import liga_deportiva.modelo.EstadoTorneo;


public class MenuTorneo {

    private Scanner scanner;

    private TorneoControlador torneoControlador;
    private EquipoControlador equipoControlador;
    private PartidoControlador partidoControlador;

    public MenuTorneo(
            TorneoControlador torneoControlador,
            EquipoControlador equipoControlador,
            PartidoControlador partidoControlador) {

        this.scanner = new Scanner(System.in);

        this.torneoControlador = torneoControlador;
        this.equipoControlador = equipoControlador;
        this.partidoControlador = partidoControlador;
    }

    public void mostrarMenuTorneos() {

        boolean continuar = true;

        while (continuar) {

            mostrarEncabezado(
                    "GESTIÓN DE TORNEOS"
            );

            System.out.println(
                    "1. Registrar torneo"
            );

            System.out.println(
                    "2. Buscar torneo por código"
            );

            System.out.println(
                    "3. Buscar torneo por nombre"
            );

            System.out.println(
                    "4. Modificar torneo"
            );

            System.out.println(
                    "5. Listar torneos"
            );


            System.out.println(
                    "6. Agregar equipo al torneo"
            );

            System.out.println(
                    "7. Quitar equipo del torneo"
            );


            System.out.println(
                    "8. Agregar partido al torneo"
            );


            System.out.println(
                    "9. Finalizar torneo"
            );

            System.out.println(
                    "\n--------------------------------------------------"
            );

            System.out.println(
                    "0. Volver al menú principal"
            );

            System.out.println(
                    "=================================================="
            );

            int opcion =
                    leerEntero(
                            "Seleccione una opción: "
                    );

            switch (opcion) {

                case 1:
                    registrarTorneo();
                    break;

                case 2:
                    buscarTorneoPorCodigo();
                    break;

                case 3:
                    buscarTorneoPorNombre();
                    break;

                case 4:
                    modificarTorneo();
                    break;

                case 5:
                    listarTorneos();
                    break;

                case 6:
                    agregarEquipoATorneo();
                    break;

                case 7:
                    quitarEquipoDeTorneo();
                    break;

                case 8:
                    agregarPartidoATorneo();
                    break;

                case 9:
                    finalizarTorneo();
                    break;

                case 0:

                    volverMenuPrincipal();
                    continuar = false;
                    break;

                default:

                    mostrarError(
                            "Opción inválida."
                    );

                    pausarPantalla();
            }
        }
    }
    
    private void mostrarEncabezado(String titulo) {

        System.out.println(
                "\n=================================================="
        );

        System.out.printf(
                "%30s%n",
                titulo
        );

        System.out.println(
                "=================================================="
        );
    }

    private void mostrarExito(String mensaje) {

        System.out.println(
                "\n[SUCCESS] " + mensaje
        );
    }

    public void mostrarError(String mensaje) {

        System.out.println(
                "\n[ERROR] " + mensaje
        );
    }

    private void pausarPantalla() {

        System.out.println(
                "\nPresione ENTER para continuar..."
        );

        scanner.nextLine();
    }

    private String leerTextoObligatorio(
            String mensaje) {

        String texto;

        do {

            System.out.print(mensaje);

            texto = scanner.nextLine().trim();

            if (texto.isEmpty()) {

                mostrarError(
                        "Este campo es obligatorio."
                );
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
    
    
    
    private String leerNombre(
            String mensaje) {

        String nombre;

        do {

        System.out.print(mensaje);

        nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {

            mostrarError(
                    "El nombre es obligatorio."
            );

            continue;
        }

        if (!nombre.matches(
                "[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {

            mostrarError(
                    "El nombre solo puede contener letras."
            );

            nombre = "";
        }

        } while (nombre.isEmpty());

        return nombre;
    }
    
    private LocalDate leerFecha(String mensaje) {

        while (true) {

        try {

            return LocalDate.parse(
                    leerTextoObligatorio(
                            mensaje
                    )
            );

        } catch (Exception e) {

            mostrarError(
                    "Formato de fecha inválido. Use AAAA-MM-DD."
                );
            }
        }
    }
    

    private int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                mostrarError(
                        "Debe ingresar un número entero válido."
                );
            }
        }
    }
    
    public Torneo solicitarDatosTorneo() {

        mostrarEncabezado(
                "REGISTRAR TORNEO"
        );

        String codigo =
                leerCodigo(
                        "Código: "
                );

        String nombre =
                leerNombre(
                        "Nombre: "
                );
        
        LocalDate fechaInicio =
                leerFecha(
                           "Nueva fecha inicio (AAAA-MM-DD): "
                );

        LocalDate fechaFin =
                leerFecha(
                            "Nueva fecha fin (AAAA-MM-DD): "
                        );

        return new Torneo(
                codigo,
                nombre,
                fechaInicio,
                fechaFin,
                EstadoTorneo.ACTIVO
        );
    }

    public String solicitarCodigoTorneo() {

        return leerCodigo(
                "Ingrese código del torneo: "
        );
    }

    public void mostrarTorneo(
            Torneo torneo) {

        mostrarEncabezado(
                "DATOS DEL TORNEO"
        );

        if (torneo == null) {

            mostrarError(
                    "No se encontró el torneo."
            );

            pausarPantalla();
            return;
        }

        System.out.println(
                "Código            : "
                        + torneo.getCodigo()
        );

        System.out.println(
                "Nombre            : "
                        + torneo.getNombre()
        );

        System.out.println(
                "Fecha inicio      : "
                        + torneo.getFechaInicio()
        );

        System.out.println(
                "Fecha fin         : "
                        + torneo.getFechaFin()
        );

        System.out.println(
                "Estado            : "
                        + torneo.getEstado()
        );

        System.out.println(
                "Equipos           : "
                        + torneo.obtenerCantidadEquipos()
        );

        System.out.println(
                "Partidos          : "
                        + torneo.getPartidos().size()
        );

        pausarPantalla();
    }

    public void mostrarListaTorneos(
            List<Torneo> torneos) {

        mostrarEncabezado(
                "LISTADO DE TORNEOS"
        );

        if (torneos.isEmpty()) {

            mostrarError(
                    "No existen torneos registrados."
            );

            pausarPantalla();
            return;
        }

        System.out.printf(
                "%-10s %-20s %-12s %-12s %-12s %-10s%n",
                "CÓDIGO",
                "NOMBRE",
                "INICIO",
                "FIN",
                "ESTADO",
                "EQUIPOS"
        );

        System.out.println(
                "-------------------------------------------------------------------------------------"
        );

        for (Torneo torneo : torneos) {

            System.out.printf(
                    "%-10s %-20s %-12s %-12s %-12s %-10d%n",
                    torneo.getCodigo(),
                    torneo.getNombre(),
                    torneo.getFechaInicio(),
                    torneo.getFechaFin(),
                    torneo.getEstado(),
                    torneo.obtenerCantidadEquipos()
            );
        }

        System.out.println(
                "-------------------------------------------------------------------------------------"
        );

        System.out.println(
                "\nTotal de torneos registrados: "
                        + torneos.size()
        );

        pausarPantalla();
    }
    
    private void registrarTorneo() {


        Torneo torneo =
                solicitarDatosTorneo();

        if (torneoControlador
                .registrarTorneo(torneo)) {

            mostrarExito(
                    "Torneo registrado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible registrar el torneo."
            );
        }

        pausarPantalla();
    }

    private void buscarTorneoPorCodigo() {

        mostrarEncabezado(
                "BUSCAR TORNEO POR CÓDIGO"
        );

        String codigo =
                solicitarCodigoTorneo();

        Torneo torneo =
                torneoControlador
                        .buscarTorneoPorCodigo(codigo);

        mostrarTorneo(torneo);
    }

    private void buscarTorneoPorNombre() {

        mostrarEncabezado(
                "BUSCAR TORNEO POR NOMBRE"
        );

        String nombre =
                leerNombre(
                        "Ingrese nombre del torneo: "
                );

        Torneo torneo =
                torneoControlador
                        .buscarTorneoPorNombre(nombre);

        mostrarTorneo(torneo);
    }

    private void modificarTorneo() {

        mostrarEncabezado(
                "MODIFICAR TORNEO"
        );
        
        try {
        String codigo =
                solicitarCodigoTorneo();

        Torneo torneoExistente =
                torneoControlador
                        .buscarTorneoPorCodigo(codigo);

        if (torneoExistente == null) {

            mostrarError(
                    "No existe un torneo con ese código."
            );

            pausarPantalla();
            return;
        }

        String nombre =
                leerNombre(
                        "Nuevo nombre: "
                );

        LocalDate fechaInicio =
                LocalDate.parse(
                        leerTextoObligatorio(
                                "Nueva fecha inicio (AAAA-MM-DD): "
                        )
                );

        LocalDate fechaFin =
                LocalDate.parse(
                        leerTextoObligatorio(
                                "Nueva fecha fin (AAAA-MM-DD): "
                        )
                );

        Torneo torneoModificado =
                new Torneo(
                        codigo,
                        nombre,
                        fechaInicio,
                        fechaFin,
                        torneoExistente.getEstado()
                );

        if (torneoControlador
                .modificarTorneo(
                        torneoModificado)) {

            mostrarExito(
                    "Torneo modificado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible modificar el torneo."
            );
        }
        } catch (Exception e) {

                mostrarError(
                "Formato de fecha inválido. Use AAAA-MM-DD."
                );
        }

        pausarPantalla();
    }

    private void listarTorneos() {

        List<Torneo> torneos =
                torneoControlador
                        .listarTorneos();

        mostrarListaTorneos(torneos);
    }

    private void agregarEquipoATorneo() {

        mostrarEncabezado(
                "AGREGAR EQUIPO AL TORNEO"
        );

        String codigoTorneo =
                solicitarCodigoTorneo();

        String codigoEquipo =
                leerCodigo(
                        "Código del equipo: "
                );

        Equipo equipo =
                equipoControlador
                        .buscarEquipoPorCodigo(
                                codigoEquipo
                        );

        if (torneoControlador
                .agregarEquipoATorneo(
                        codigoTorneo,
                        equipo
                )) {

            mostrarExito(
                    "Equipo agregado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible agregar el equipo."
            );
        }

        pausarPantalla();
    }

    private void quitarEquipoDeTorneo() {

        mostrarEncabezado(
                "QUITAR EQUIPO DEL TORNEO"
        );

        String codigoTorneo =
                solicitarCodigoTorneo();

        String codigoEquipo =
                leerCodigo(
                        "Código del equipo: "
                );

        Equipo equipo =
                equipoControlador
                        .buscarEquipoPorCodigo(
                                codigoEquipo
                        );

        if (torneoControlador
                .quitarEquipoDeTorneo(
                        codigoTorneo,
                        equipo
                )) {

            mostrarExito(
                    "Equipo removido correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible remover el equipo."
            );
        }

        pausarPantalla();
    }

    private void agregarPartidoATorneo() {

        mostrarEncabezado(
                "AGREGAR PARTIDO AL TORNEO"
        );

        String codigoTorneo =
                solicitarCodigoTorneo();

        String codigoPartido =
                leerCodigo(
                        "Código del partido: "
                );

        Partido partido =
                partidoControlador
                        .buscarPartidoPorCodigo(
                                codigoPartido
                        );

        if (torneoControlador
                .agregarPartidoATorneo(
                        codigoTorneo,
                        partido
                )) {

            mostrarExito(
                    "Partido agregado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible agregar el partido."
            );
        }

        pausarPantalla();
    }

    private void finalizarTorneo() {

        mostrarEncabezado(
                "FINALIZAR TORNEO"
        );

        String codigo =
                solicitarCodigoTorneo();

        if (torneoControlador
                .finalizarTorneo(codigo)) {

            mostrarExito(
                    "Torneo finalizado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible finalizar el torneo."
            );
        }

        pausarPantalla();
    }

    private void volverMenuPrincipal() {

        mostrarExito(
                "Regresando al menú principal..."
        );
    }
}
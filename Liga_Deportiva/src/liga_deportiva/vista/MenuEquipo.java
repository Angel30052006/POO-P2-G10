package liga_deportiva.vista;


import java.util.List;
import java.util.Scanner;

import liga_deportiva.controlador.EquipoControlador;
import liga_deportiva.controlador.EntrenadorControlador;
import liga_deportiva.controlador.JugadorControlador;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.modelo.Jugador;
import liga_deportiva.modelo.Entrenador;

public class MenuEquipo {

    private Scanner scanner;
    private EquipoControlador equipoControlador;
    private EntrenadorControlador entrenadorControlador;
    private JugadorControlador jugadorControlador;


    public MenuEquipo(
        EquipoControlador equipoControlador,
        EntrenadorControlador entrenadorControlador,
        JugadorControlador jugadorControlador) {

    this.scanner = new Scanner(System.in);
    this.equipoControlador = equipoControlador;
    this.entrenadorControlador = entrenadorControlador;
    this.jugadorControlador = jugadorControlador;
}

    public void mostrarMenuEquipos() {

        boolean continuar = true;

        while (continuar) {

            mostrarEncabezado(
                    "GESTIÓN DE EQUIPOS"
            );

            System.out.println(
                    "1. Registrar equipo"
            );

            System.out.println(
                    "2. Buscar equipo por código"
            );

            System.out.println(
                    "3. Buscar equipo por nombre"
            );

            System.out.println(
                    "4. Modificar equipo"
            );

            System.out.println(
                    "5. Listar equipos"
            );

       
            System.out.println(
                    "6. Asignar entrenador"
            );

            System.out.println(
                    "7. Remover entrenador"
            );


            System.out.println(
                    "8. Agregar jugador"
            );

            System.out.println(
                    "9. Quitar jugador"
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
                    registrarEquipo();
                    break;

                case 2:
                    buscarEquipoPorCodigo();
                    break;

                case 3:
                    buscarEquipoPorNombre();
                    break;

                case 4:
                    modificarEquipo();
                    break;

                case 5:
                    listarEquipos();
                    break;

                case 6:
                    asignarEntrenador();
                    break;

                case 7:
                    removerEntrenador();
                    break;

                case 8:
                    agregarJugador();
                    break;

                case 9:
                    quitarJugador();
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
    
    private String leerNombreEquipo(
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
    
    
    private String leerCiudad(
        String mensaje) {

        String ciudad;

        do {

        System.out.print(mensaje);

        ciudad = scanner.nextLine().trim();

        if (ciudad.isEmpty()) {

            mostrarError(
                    "La ciudad es obligatoria."
            );

            continue;
        }

        if (!ciudad.matches(
                "[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {

            mostrarError(
                    "La ciudad solo puede contener letras."
            );

            ciudad = "";
        }

        } while (ciudad.isEmpty());

        return ciudad;
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
    
    public Equipo solicitarDatosEquipo() {

        mostrarEncabezado(
                "REGISTRAR EQUIPO"
        );

        String codigo =
                leerTextoObligatorio(
                        "Código: "
                );

        String nombre =
                leerNombreEquipo(
                        "Nombre: "
                );

        String ciudadOrigen =
                leerCiudad(
                        "Ciudad de origen: "
                );

        return new Equipo(
                codigo,
                nombre,
                ciudadOrigen
        );
    }

    public String solicitarCodigoEquipo() {

        return leerTextoObligatorio(
                "Ingrese código del equipo: "
        );
    }

    public void mostrarEquipo(
            Equipo equipo) {

        mostrarEncabezado(
                "DATOS DEL EQUIPO"
        );

        if (equipo == null) {

            mostrarError(
                    "No se encontró el equipo."
            );

            pausarPantalla();
            return;
        }

        System.out.println(
                "Código            : "
                        + equipo.getCodigo()
        );

        System.out.println(
                "Nombre            : "
                        + equipo.getNombre()
        );

        System.out.println(
                "Ciudad origen     : "
                        + equipo.getCiudadOrigen()
        );

        System.out.println(
                "Entrenador        : "
                        + (equipo.getEntrenador() != null
                        ? equipo.getEntrenador().getNombre()
                        : "Sin entrenador")
        );

        System.out.println(
                "Cantidad jugadores: "
                        + equipo.obtenerCantidadJugadores()
        );

        pausarPantalla();
    }

    public void mostrarListaEquipos(
            List<Equipo> equipos) {

        mostrarEncabezado(
                "LISTADO DE EQUIPOS"
        );

        if (equipos == null
        || equipos.isEmpty()) {

            mostrarError(
                    "No existen equipos registrados."
            );

            pausarPantalla();
            return;
        }

        System.out.printf(
                "%-12s %-20s %-18s %-20s %-10s%n",
                "CÓDIGO",
                "NOMBRE",
                "CIUDAD",
                "ENTRENADOR",
                "JUGADORES"
        );

        System.out.println(
                "--------------------------------------------------------------------------------------------"
        );

        for (Equipo equipo : equipos) {

            System.out.printf(
                    "%-12s %-20s %-18s %-20s %-10d%n",
                    equipo.getCodigo(),
                    equipo.getNombre(),
                    equipo.getCiudadOrigen(),
                    equipo.getEntrenador() != null
                            ? equipo.getEntrenador().getNombre()
                            : "Sin entrenador",
                    equipo.obtenerCantidadJugadores()
            );
        }

        System.out.println(
                "--------------------------------------------------------------------------------------------"
        );

        System.out.println(
                "\nTotal de equipos registrados: "
                        + equipos.size()
        );

        pausarPantalla();
    }
    
    private void registrarEquipo() {

        Equipo equipo =
                solicitarDatosEquipo();

        if (equipoControlador
                .registrarEquipo(equipo)) {

            mostrarExito(
                    "Equipo registrado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible registrar el equipo."
            );
        }

        pausarPantalla();
    }

    private void buscarEquipoPorCodigo() {

        mostrarEncabezado(
                "BUSCAR EQUIPO POR CÓDIGO"
        );

        String codigo =
                solicitarCodigoEquipo();

        Equipo equipo =
                equipoControlador
                        .buscarEquipoPorCodigo(codigo);

        mostrarEquipo(equipo);
    }

    private void buscarEquipoPorNombre() {

        mostrarEncabezado(
                "BUSCAR EQUIPO POR NOMBRE"
        );

        String nombre =
                leerNombreEquipo(
                        "Ingrese nombre del equipo: "
                );

        Equipo equipo =
                equipoControlador
                        .buscarEquipoPorNombre(nombre);

        mostrarEquipo(equipo);
    }

    private void modificarEquipo() {

        mostrarEncabezado(
                "MODIFICAR EQUIPO"
        );

        String codigo =
                solicitarCodigoEquipo();

        Equipo equipoExistente =
                equipoControlador
                        .buscarEquipoPorCodigo(codigo);

        if (equipoExistente == null) {

            mostrarError(
                    "No existe un equipo con ese código."
            );

            pausarPantalla();
            return;
        }

        String nombre =
                leerNombreEquipo(
                        "Nuevo nombre: "
                );

        String ciudad =
                leerCiudad(
                        "Nueva ciudad: "
                );

        Equipo equipoModificado =
                new Equipo(
                        codigo,
                        nombre,
                        ciudad
                );

        if (equipoControlador
                .modificarEquipo(
                        equipoModificado)) {

            mostrarExito(
                    "Equipo modificado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible modificar el equipo."
            );
        }

        pausarPantalla();
    }

    private void listarEquipos() {

        List<Equipo> equipos =
                equipoControlador
                        .listarEquipos();

        mostrarListaEquipos(equipos);
    }

    private void asignarEntrenador() {

        mostrarEncabezado(
                "ASIGNAR ENTRENADOR"
        );

        String codigoEquipo =
                solicitarCodigoEquipo();

        String codigoEntrenador =
                leerTextoObligatorio(
                        "Código del entrenador: "
                );

        Entrenador entrenador =
                entrenadorControlador
                        .buscarEntrenadorPorCodigo(
                                codigoEntrenador
                        );
        
        
        if (entrenador == null) {

        mostrarError(
            "No existe un entrenador con ese código."
        );

        pausarPantalla();
        return;
        }

        if (equipoControlador
                .asignarEntrenador(
                        codigoEquipo,
                        entrenador)) {

            mostrarExito(
                    "Entrenador asignado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible asignar el entrenador."
            );
        }

        pausarPantalla();
    }

    private void removerEntrenador() {

        mostrarEncabezado(
                "REMOVER ENTRENADOR"
        );

        String codigoEquipo =
                solicitarCodigoEquipo();

        if (equipoControlador
                .removerEntrenador(
                        codigoEquipo)) {

            mostrarExito(
                    "Entrenador removido correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible remover el entrenador."
            );
        }

        pausarPantalla();
    }

    private void agregarJugador() {

        mostrarEncabezado(
                "AGREGAR JUGADOR"
        );

        String codigoEquipo =
                solicitarCodigoEquipo();

        String codigoJugador =
                leerTextoObligatorio(
                        "Código del jugador: "
                );

        Jugador jugador =
                jugadorControlador
                        .buscarJugadorPorCodigo(
                                codigoJugador
                        );
        
        if (jugador == null) {

        mostrarError(
            "No existe un jugador con ese código."
        );

         pausarPantalla();
        return;
        }

        if (equipoControlador
                .agregarJugador(
                        codigoEquipo,
                        jugador)) {

            mostrarExito(
                    "Jugador agregado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible agregar el jugador."
            );
        }

        pausarPantalla();
    }

    private void quitarJugador() {

        mostrarEncabezado(
                "QUITAR JUGADOR"
        );

        String codigoEquipo =
                solicitarCodigoEquipo();

        String codigoJugador =
                leerTextoObligatorio(
                        "Código del jugador: "
                );

        Jugador jugador =
                jugadorControlador
                        .buscarJugadorPorCodigo(
                                codigoJugador
                        );
        
        if (jugador == null) {

        mostrarError(
            "No existe un jugador con ese código."
        );

            pausarPantalla();
            return;
        }   

        if (equipoControlador
                .quitarJugador(
                        codigoEquipo,
                        jugador)) {

            mostrarExito(
                    "Jugador removido correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible remover el jugador."
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
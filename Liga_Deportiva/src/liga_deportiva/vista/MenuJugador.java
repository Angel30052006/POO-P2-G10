package liga_deportiva.vista;

import java.util.List;
import java.util.Scanner;

import liga_deportiva.controlador.JugadorControlador;
import liga_deportiva.modelo.Jugador;

public class MenuJugador {

    private Scanner scanner;
    private JugadorControlador jugadorControlador;

    public MenuJugador(
            JugadorControlador jugadorControlador) {

        this.scanner = new Scanner(System.in);
        this.jugadorControlador = jugadorControlador;
    }

    public void mostrarMenuJugadores() {

        boolean continuar = true;

        while (continuar) {

            mostrarEncabezado(
                    "GESTIÓN DE JUGADORES"
            );

            System.out.println(
                    "1. Registrar jugador"
            );

            System.out.println(
                    "2. Buscar jugador por código"
            );

            System.out.println(
                    "3. Buscar jugador por nombre"
            );

            System.out.println(
                    "4. Modificar jugador"
            );

            System.out.println(
                    "5. Listar jugadores"
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
                    registrarJugador();
                    break;

                case 2:
                    buscarJugadorPorCodigo();
                    break;

                case 3:
                    buscarJugadorPorNombre();
                    break;

                case 4:
                    modificarJugador();
                    break;

                case 5:
                    listarJugadores();
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
    
    
    private String leerPosicion(
        String mensaje) {

        String posicion;

        do {

        System.out.print(mensaje);

        posicion = scanner.nextLine().trim();

        if (posicion.isEmpty()) {

            mostrarError(
                    "La posición es obligatoria."
            );

            continue;
        }

        if (!posicion.matches(
                "[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {

            mostrarError(
                    "La posición solo puede contener letras."
            );

            posicion = "";
        }

        } while (posicion.isEmpty());

        return posicion;
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

    private int leerEdad() {

        int edad;

        do {

            edad = leerEntero(
                    "Edad (15-70): "
            );

            if (edad < 15|| edad > 70) {

                mostrarError(
                        "La edad debe estar entre 15 y 70 años."
                );
            }

        } while (edad < 15 || edad > 70);

        return edad;
    }

    private int leerNumeroCamiseta() {

        int numero;

        do {

            numero =
                    leerEntero(
                            "Número de camiseta: "
                    );

            if (numero <= 0) {

                mostrarError(
                        "El número debe ser mayor que cero."
                );
            }

        } while (numero <= 0);

        return numero;
    }

    private boolean leerAutorizacionEspecial() {

        while (true) {

            System.out.print(
                    "¿Posee autorización especial? (S/N): "
            );

            String respuesta =
                    scanner.nextLine().trim();

            if (respuesta.equalsIgnoreCase("S")) {
                return true;
            }

            if (respuesta.equalsIgnoreCase("N")) {
                return false;
            }

            mostrarError(
                    "Ingrese únicamente S o N."
            );
        }
    }
    
    public Jugador solicitarDatosJugador() {

        mostrarEncabezado(
                "REGISTRAR JUGADOR"
        );

        String codigo =
                leerTextoObligatorio(
                        "Código: "
                );

        String nombre =
                leerNombre(
                        "Nombre: "
                );

        int edad =
                leerEdad();

        int numeroCamiseta =
                leerNumeroCamiseta();

        String posicion =
                leerPosicion(
                        "Posición: "
                );

        boolean autorizacionEspecial =
                leerAutorizacionEspecial();

        return new Jugador(
                codigo,
                nombre,
                edad,
                numeroCamiseta,
                posicion,
                autorizacionEspecial
        );
    }

    public String solicitarCodigoJugador() {

        return leerTextoObligatorio(
                "Ingrese código del jugador: "
        );
    }

    public void mostrarJugador(
            Jugador jugador) {

        mostrarEncabezado(
                "DATOS DEL JUGADOR"
        );

        if (jugador == null) {

            mostrarError(
                    "No se encontró el jugador."
            );

            pausarPantalla();
            return;
        }

        System.out.println(
                "Código                 : "
                        + jugador.getCodigo()
        );

        System.out.println(
                "Nombre                 : "
                        + jugador.getNombre()
        );

        System.out.println(
                "Edad                   : "
                        + jugador.getEdad()
                        + " años"
        );

        System.out.println(
                "Número de camiseta     : "
                        + jugador.getNumeroCamiseta()
        );

        System.out.println(
                "Posición               : "
                        + jugador.getPosicion()
        );

        System.out.println(
                "Autorización especial  : "
                        + (jugador.isAutorizacionEspecial()
                        ? "Sí"
                        : "No")
        );

        pausarPantalla();
    }

    public void mostrarListaJugadores(
            List<Jugador> jugadores) {

        mostrarEncabezado(
                "LISTADO DE JUGADORES"
        );

        if (jugadores == null
        || jugadores.isEmpty()) {

            mostrarError(
                    "No existen jugadores registrados."
            );

            pausarPantalla();
            return;
        }

        System.out.printf(
                "%-12s %-20s %-8s %-10s %-18s %-12s%n",
                "CÓDIGO",
                "NOMBRE",
                "EDAD",
                "CAMISETA",
                "POSICIÓN",
                "AUTORIZACIÓN"
        );

        System.out.println(
                "---------------------------------------------------------------------------------------------"
        );

        for (Jugador jugador : jugadores) {

            System.out.printf(
                    "%-12s %-20s %-8d %-10d %-18s %-12s%n",
                    jugador.getCodigo(),
                    jugador.getNombre(),
                    jugador.getEdad(),
                    jugador.getNumeroCamiseta(),
                    jugador.getPosicion(),
                    jugador.isAutorizacionEspecial()
                            ? "Sí"
                            : "No"
            );
        }

        System.out.println(
                "---------------------------------------------------------------------------------------------"
        );

        System.out.println(
                "\nTotal de jugadores registrados: "
                        + jugadores.size()
        );

        pausarPantalla();
    }
    
    private void registrarJugador() {

        Jugador jugador =
                solicitarDatosJugador();

        if (jugadorControlador
                .registrarJugador(jugador)) {

            mostrarExito(
                    "Jugador registrado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible registrar el jugador."
            );
        }

        pausarPantalla();
    }

    private void buscarJugadorPorCodigo() {

        mostrarEncabezado(
                "BUSCAR JUGADOR POR CÓDIGO"
        );

        String codigo =
                solicitarCodigoJugador();

        Jugador jugador =
                jugadorControlador
                        .buscarJugadorPorCodigo(codigo);

        mostrarJugador(jugador);
    }

    private void buscarJugadorPorNombre() {

        mostrarEncabezado(
                "BUSCAR JUGADOR POR NOMBRE"
        );

        String nombre =
                leerNombre(
                        "Ingrese nombre del jugador: "
                );

        Jugador jugador =
                jugadorControlador
                        .buscarJugadorPorNombre(nombre);

        mostrarJugador(jugador);
    }

    private void modificarJugador() {

        mostrarEncabezado(
                "MODIFICAR JUGADOR"
        );

        String codigo =
                solicitarCodigoJugador();

        Jugador jugadorExistente =
                jugadorControlador
                        .buscarJugadorPorCodigo(codigo);

        if (jugadorExistente == null) {

            mostrarError(
                    "No existe un jugador con ese código."
            );

            pausarPantalla();
            return;
        }

        System.out.println(
                "\nDatos actuales:"
        );

        System.out.println(
                "Nombre                : "
                        + jugadorExistente.getNombre()
        );

        System.out.println(
                "Edad                  : "
                        + jugadorExistente.getEdad()
                        + " años"
        );

        System.out.println(
                "Número de camiseta    : "
                        + jugadorExistente.getNumeroCamiseta()
        );

        System.out.println(
                "Posición              : "
                        + jugadorExistente.getPosicion()
        );

        System.out.println(
                "Autorización especial : "
                        + (jugadorExistente.isAutorizacionEspecial()
                        ? "Sí"
                        : "No")
        );

        System.out.println();

        String nombre =
                leerNombre(
                        "Nuevo nombre: "
                );

        int edad =
                leerEdad();

        int numeroCamiseta =
                leerNumeroCamiseta();

        String posicion =
                leerPosicion(
                        "Nueva posición: "
                );

        boolean autorizacionEspecial =
                leerAutorizacionEspecial();

        Jugador jugadorModificado =
                new Jugador(
                        codigo,
                        nombre,
                        edad,
                        numeroCamiseta,
                        posicion,
                        autorizacionEspecial
                );

        if (jugadorControlador
                .modificarJugador(
                        jugadorModificado)) {

            mostrarExito(
                    "Jugador modificado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible modificar el jugador."
            );
        }

        pausarPantalla();
    }

    private void listarJugadores() {

        List<Jugador> jugadores =
                jugadorControlador
                        .listarJugadores();

        mostrarListaJugadores(jugadores);
    }

    private void volverMenuPrincipal() {

        mostrarExito(
                "Regresando al menú principal..."
        );
    }
}


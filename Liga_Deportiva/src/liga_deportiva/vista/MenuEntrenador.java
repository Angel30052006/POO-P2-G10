package liga_deportiva.vista;
import java.util.List;
import java.util.Scanner;

import liga_deportiva.controlador.EntrenadorControlador;
import liga_deportiva.modelo.Entrenador;

public class MenuEntrenador {

    private Scanner scanner;
    private EntrenadorControlador entrenadorControlador;

    public MenuEntrenador(
            EntrenadorControlador entrenadorControlador) {

        this.scanner = new Scanner(System.in);
        this.entrenadorControlador = entrenadorControlador;
    }

    public void mostrarMenuEntrenadores() {

        boolean continuar = true;

        while (continuar) {

            mostrarEncabezado(
                    "GESTIÓN DE ENTRENADORES"
            );

            System.out.println(
                    "1. Registrar entrenador"
            );

            System.out.println(
                    "2. Buscar entrenador por código"
            );

            System.out.println(
                    "3. Buscar entrenador por nombre"
            );

            System.out.println(
                    "4. Modificar entrenador"
            );

            System.out.println(
                    "5. Listar entrenadores"
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
                    registrarEntrenador();
                    break;

                case 2:
                    buscarEntrenadorPorCodigo();
                    break;

                case 3:
                    buscarEntrenadorPorNombre();
                    break;

                case 4:
                    modificarEntrenador();
                    break;

                case 5:
                    listarEntrenadores();
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
                "%32s%n",
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
    
    private String leerTextoObligatorio( String mensaje) { 
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
    
    private String leerNacionalidad(String mensaje) {

    String nacionalidad;

    do {

        System.out.print(mensaje);

        nacionalidad = scanner.nextLine().trim();

        if (nacionalidad.isEmpty()) {

            mostrarError(
                    "La nacionalidad es obligatoria."
            );

            continue;
        }

        if (!nacionalidad.matches(
                "[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {

            mostrarError(
                    "La nacionalidad solo puede contener letras."
            );

            nacionalidad = "";
        }

        }    while (nacionalidad.isEmpty());

        return nacionalidad;
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
                    "Edad (18-70): "
            );

            if (edad < 18 || edad > 70) {

                mostrarError(
                        "La edad debe estar entre 18 y 70 años."
                );
            }

        } while (edad < 18 || edad > 70);

        return edad;
    }

    private int leerExperiencia(int edad) {

        int experiencia;

        do {

            experiencia =
                    leerEntero(
                            "Años de experiencia: "
                    );

            if (experiencia < 0) {

                mostrarError(
                        "La experiencia no puede ser negativa."
                );
            }

            if (experiencia > edad) {

                mostrarError(
                        "La experiencia no puede superar la edad."
                );
            }

        } while (
                experiencia < 0
                || experiencia > edad
        );

        return experiencia;
    }
    
    public Entrenador solicitarDatosEntrenador() {

        mostrarEncabezado(
                "REGISTRAR ENTRENADOR"
        );

        String codigo =
                leerTextoObligatorio(
                        "Código: "
                );

        String nombre =
                leerNombre(
                        "Nombre: "
                );

        int edad = leerEdad();

        String nacionalidad =
                leerNacionalidad(
                        "Nacionalidad: "
                );

        int experiencia =
                leerExperiencia(edad);

        return new Entrenador(
                codigo,
                nombre,
                edad,
                nacionalidad,
                experiencia
        );
    }

    public String solicitarCodigoEntrenador() {

        return leerTextoObligatorio(
                "Ingrese código del entrenador: "
        );
    }

    public void mostrarEntrenador(
            Entrenador entrenador) {

        mostrarEncabezado(
                "DATOS DEL ENTRENADOR"
        );

        if (entrenador == null) {

            mostrarError(
                    "No se encontró el entrenador."
            );

            pausarPantalla();
            return;
        }

        System.out.println(
                "Código        : "
                        + entrenador.getCodigo()
        );

        System.out.println(
                "Nombre        : "
                        + entrenador.getNombre()
        );

        System.out.println(
                "Edad          : "
                        + entrenador.getEdad()
                        + " años"
        );

        System.out.println(
                "Nacionalidad  : "
                        + entrenador.getNacionalidad()
        );

        System.out.println(
                "Experiencia   : "
                        + entrenador.getAniosExperiencia()
                        + " años"
        );

        pausarPantalla();
    }

    public void mostrarListaEntrenadores(
            List<Entrenador> entrenadores) {

        mostrarEncabezado(
                "LISTADO DE ENTRENADORES"
        );

        if (entrenadores == null
        || entrenadores.isEmpty()) {

            mostrarError(
                    "No existen entrenadores registrados."
            );

            pausarPantalla();
            return;
        }

        System.out.printf(
                "%-12s %-20s %-8s %-18s %-15s%n",
                "CÓDIGO",
                "NOMBRE",
                "EDAD",
                "NACIONALIDAD",
                "EXPERIENCIA"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        for (Entrenador entrenador : entrenadores) {

            System.out.printf(
                    "%-12s %-20s %-8d %-18s %-15s%n",
                    entrenador.getCodigo(),
                    entrenador.getNombre(),
                    entrenador.getEdad(),
                    entrenador.getNacionalidad(),
                    entrenador.getAniosExperiencia()
                            + " años"
            );
        }

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        System.out.println(
                "\nTotal de entrenadores registrados: "
                        + entrenadores.size()
        );

        pausarPantalla();
    }
    
    private void registrarEntrenador() {

        Entrenador entrenador =
                solicitarDatosEntrenador();

        if (entrenadorControlador
                .registrarEntrenador(entrenador)) {

            mostrarExito(
                    "Entrenador registrado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible registrar el entrenador."
            );
        }

        pausarPantalla();
    }

    private void buscarEntrenadorPorCodigo() {

        mostrarEncabezado(
                "BUSCAR ENTRENADOR POR CÓDIGO"
        );

        String codigo =
                solicitarCodigoEntrenador();

        Entrenador entrenador =
                entrenadorControlador
                        .buscarEntrenadorPorCodigo(codigo);

        mostrarEntrenador(entrenador);
    }

    private void buscarEntrenadorPorNombre() {

        mostrarEncabezado(
                "BUSCAR ENTRENADOR POR NOMBRE"
        );

        String nombre =
                leerNombre(
                        "Ingrese nombre del entrenador: "
                );

        Entrenador entrenador =
                entrenadorControlador
                        .buscarEntrenadorPorNombre(nombre);

        mostrarEntrenador(entrenador);
    }

    private void modificarEntrenador() {

        mostrarEncabezado(
                "MODIFICAR ENTRENADOR"
        );

        String codigo =
                solicitarCodigoEntrenador();

        Entrenador entrenadorExistente =
                entrenadorControlador
                        .buscarEntrenadorPorCodigo(codigo);

        if (entrenadorExistente == null) {

            mostrarError(
                    "No existe un entrenador con ese código."
            );

            pausarPantalla();
            return;
        }

        System.out.println(
                "\nDatos actuales:"
        );

        System.out.println(
                "Nombre        : "
                        + entrenadorExistente.getNombre()
        );

        System.out.println(
                "Edad          : "
                        + entrenadorExistente.getEdad()
                        + " años"
        );

        System.out.println(
                "Nacionalidad  : "
                        + entrenadorExistente.getNacionalidad()
        );

        System.out.println(
                "Experiencia   : "
                        + entrenadorExistente.getAniosExperiencia()
                        + " años"
        );

        System.out.println();

        String nombre =
                leerNombre(
                        "Nuevo nombre: "
                );

        int edad =
                leerEdad();

        String nacionalidad =
                leerNacionalidad(
                        "Nueva nacionalidad: "
                );

        int experiencia =
                leerExperiencia(edad);

        Entrenador entrenadorModificado =
                new Entrenador(
                        codigo,
                        nombre,
                        edad,
                        nacionalidad,
                        experiencia
                );

        if (entrenadorControlador
                .modificarEntrenador(
                        entrenadorModificado)) {

            mostrarExito(
                    "Entrenador modificado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible modificar el entrenador."
            );
        }

        pausarPantalla();
    }

    private void listarEntrenadores() {

        List<Entrenador> entrenadores =
                entrenadorControlador
                        .listarEntrenadores();

        mostrarListaEntrenadores(entrenadores);
    }

    private void volverMenuPrincipal() {

        mostrarExito(
                "Regresando al menú principal..."
        );
    }
}



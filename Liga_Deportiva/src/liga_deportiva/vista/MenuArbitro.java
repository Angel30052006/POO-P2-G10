package liga_deportiva.vista;


import java.util.List;
import java.util.Scanner;

import liga_deportiva.controlador.ArbitroControlador;
import liga_deportiva.modelo.Arbitro;

public class MenuArbitro {

    private Scanner scanner;
    private ArbitroControlador arbitroControlador;

    public MenuArbitro(
            ArbitroControlador arbitroControlador) {

        this.scanner = new Scanner(System.in);
        this.arbitroControlador = arbitroControlador;
    }

    public void mostrarMenuArbitros() {

        boolean continuar = true;

        while (continuar) {

            mostrarEncabezado("GESTIÓN DE ÁRBITROS");

            System.out.println("1. Registrar árbitro");
            System.out.println("2. Buscar árbitro por código");
            System.out.println("3. Buscar árbitro por nombre");
            System.out.println("4. Modificar árbitro");
            System.out.println("5. Listar árbitros");

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
                    registrarArbitro();
                    break;

                case 2:
                    buscarArbitroPorCodigo();
                    break;

                case 3:
                    buscarArbitroPorNombre();
                    break;

                case 4:
                    modificarArbitro();
                    break;

                case 5:
                    listarArbitros();
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
    
    private String leerNombre(String mensaje) {

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

    private int leerExperiencia(
            int edad) {

        int experiencia;

        do {

            experiencia = leerEntero(
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
    
    public Arbitro solicitarDatosArbitro() {

        mostrarEncabezado("REGISTRAR ÁRBITRO");

        String codigo =
                leerTextoObligatorio(
                        "Código: "
                );

        String nombre =
            leerNombre(
                "Nombre: "
        );

        int edad = leerEdad();

        int aniosExperiencia =
                leerExperiencia(edad);

        return new Arbitro(
                codigo,
                nombre,
                edad,
                aniosExperiencia
        );
    }

    public String solicitarCodigoArbitro() {

        return leerTextoObligatorio(
                "Ingrese código del árbitro: "
        );
    }

    public void mostrarArbitro(
            Arbitro arbitro) {

        mostrarEncabezado(
                "DATOS DEL ÁRBITRO"
        );

        if (arbitro == null) {

            mostrarError(
                    "No se encontró el árbitro."
            );

            pausarPantalla();
            return;
        }

        System.out.println(
                "Código      : "
                        + arbitro.getCodigo()
        );

        System.out.println(
                "Nombre      : "
                        + arbitro.getNombre()
        );

        System.out.println(
                "Edad        : "
                        + arbitro.getEdad()
                        + " años"
        );

        System.out.println(
                "Experiencia : "
                        + arbitro.getAniosExperiencia()
                        + " años"
        );

        pausarPantalla();
    }

    public void mostrarListaArbitros(
            List<Arbitro> arbitros) {

        mostrarEncabezado(
                "LISTADO DE ÁRBITROS"
        );

        if (arbitros.isEmpty()) {

            mostrarError(
                    "No existen árbitros registrados."
            );

            pausarPantalla();
            return;
        }

        System.out.printf(
                "%-12s %-25s %-10s %-15s%n",
                "CÓDIGO",
                "NOMBRE",
                "EDAD",
                "EXPERIENCIA"
        );

        System.out.println(
                "------------------------------------------------------------------"
        );

        for (Arbitro arbitro : arbitros) {

            System.out.printf(
                    "%-12s %-25s %-10d %-15s%n",
                    arbitro.getCodigo(),
                    arbitro.getNombre(),
                    arbitro.getEdad(),
                    arbitro.getAniosExperiencia()
                            + " años"
            );
        }

        System.out.println(
                "------------------------------------------------------------------"
        );

        System.out.println(
                "\nTotal de árbitros registrados: "
                        + arbitros.size()
        );

        pausarPantalla();
    }
    
    private void registrarArbitro() {

        Arbitro arbitro =
                solicitarDatosArbitro();

        if (arbitroControlador
                .registrarArbitro(arbitro)) {

            mostrarExito(
                    "Árbitro registrado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible registrar el árbitro."
            );
        }

        pausarPantalla();
    }

    private void buscarArbitroPorCodigo() {

        mostrarEncabezado(
                "BUSCAR ÁRBITRO POR CÓDIGO"
        );

        String codigo =
                solicitarCodigoArbitro();

        Arbitro arbitro =
                arbitroControlador
                        .buscarArbitroPorCodigo(codigo);

        mostrarArbitro(arbitro);
    }

    private void buscarArbitroPorNombre() {

        mostrarEncabezado(
                "BUSCAR ÁRBITRO POR NOMBRE"
        );

        String nombre =
                leerNombre(
                        "Ingrese nombre del árbitro: "
                );

        Arbitro arbitro =
                arbitroControlador
                        .buscarArbitroPorNombre(nombre);

        mostrarArbitro(arbitro);
    }

    private void modificarArbitro() {

        mostrarEncabezado(
                "MODIFICAR ÁRBITRO"
        );

        String codigo =
                solicitarCodigoArbitro();

        Arbitro arbitroExistente =
                arbitroControlador
                        .buscarArbitroPorCodigo(codigo);

        if (arbitroExistente == null) {

            mostrarError(
                    "No existe un árbitro con ese código."
            );

            pausarPantalla();
            return;
        }

        System.out.println(
                "\nDatos actuales:"
        );

        System.out.println(
                "Nombre      : "
                        + arbitroExistente.getNombre()
        );

        System.out.println(
                "Edad        : "
                        + arbitroExistente.getEdad()
                        + " años"
        );

        System.out.println(
                "Experiencia : "
                        + arbitroExistente
                        .getAniosExperiencia()
                        + " años"
        );

        System.out.println();

        String nombre =
            leerNombre(
                "Nuevo nombre: "
        );

        int edad =
                leerEdad();

        int experiencia =
                leerExperiencia(edad);

        Arbitro arbitroModificado =
                new Arbitro(
                        codigo,
                        nombre,
                        edad,
                        experiencia
                );

        if (arbitroControlador
                .modificarArbitro(
                        arbitroModificado)) {

            mostrarExito(
                    "Árbitro modificado correctamente."
            );

        } else {

            mostrarError(
                    "No fue posible modificar el árbitro."
            );
        }

        pausarPantalla();
    }

    private void listarArbitros() {

        List<Arbitro> arbitros =
                arbitroControlador
                        .listarArbitros();

        mostrarListaArbitros(arbitros);
    }

    private void volverMenuPrincipal() {

        mostrarExito(
                "Regresando al menú principal..."
        );
    }
}

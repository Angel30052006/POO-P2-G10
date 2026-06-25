package liga_deportiva;


import java.util.Scanner;

import liga_deportiva.controlador.ArbitroControlador;
import liga_deportiva.controlador.ConsultaReporteControlador;
import liga_deportiva.controlador.EntrenadorControlador;
import liga_deportiva.controlador.EquipoControlador;
import liga_deportiva.controlador.JugadorControlador;
import liga_deportiva.controlador.PartidoControlador;
import liga_deportiva.controlador.ResultadoControlador;
import liga_deportiva.controlador.TablaPosicionesControlador;
import liga_deportiva.controlador.TorneoControlador;

import liga_deportiva.vista.MenuArbitro;
import liga_deportiva.vista.MenuConsultaReporte;
import liga_deportiva.vista.MenuEntrenador;
import liga_deportiva.vista.MenuEquipo;
import liga_deportiva.vista.MenuJugador;
import liga_deportiva.vista.MenuPartido;
import liga_deportiva.vista.MenuResultado;
import liga_deportiva.vista.MenuTablaPosiciones;
import liga_deportiva.vista.MenuTorneo;

public class Main {

    public static void main(String[] args) {
        

        Scanner scanner = new Scanner(System.in);
       

        // x
        // CONTROLADORES
        // ==========================================

        TorneoControlador torneoControlador =
                new TorneoControlador();

        EquipoControlador equipoControlador =
                new EquipoControlador();

        JugadorControlador jugadorControlador =
                new JugadorControlador();

        EntrenadorControlador entrenadorControlador =
                new EntrenadorControlador();

        ArbitroControlador arbitroControlador =
                new ArbitroControlador();

        PartidoControlador partidoControlador =
                new PartidoControlador();

        ResultadoControlador resultadoControlador =
                new ResultadoControlador();

        TablaPosicionesControlador tablaPosicionesControlador =
                new TablaPosicionesControlador();

        ConsultaReporteControlador consultaReporteControlador =
                new ConsultaReporteControlador(
                        equipoControlador,
                        jugadorControlador,
                        entrenadorControlador,
                        arbitroControlador,
                        torneoControlador,
                        partidoControlador,
                        resultadoControlador,
                        tablaPosicionesControlador
                );

        // ==========================================
        // MENÚS
        // ==========================================

        MenuTorneo menuTorneo =
                new MenuTorneo(
                        torneoControlador,
                        equipoControlador,
                        partidoControlador
                );

        MenuEquipo menuEquipo =
                new MenuEquipo(
                        equipoControlador,
                        entrenadorControlador,
                        jugadorControlador
                );

        MenuJugador menuJugador =
                new MenuJugador(
                        jugadorControlador
                );

        MenuEntrenador menuEntrenador =
                new MenuEntrenador(
                        entrenadorControlador
                );

        MenuArbitro menuArbitro =
                new MenuArbitro(
                        arbitroControlador
                );

        MenuPartido menuPartido =
                new MenuPartido(
                        partidoControlador,
                        equipoControlador,
                        arbitroControlador,
                        torneoControlador
                );

        MenuResultado menuResultado =
                new MenuResultado(
                        resultadoControlador,
                        partidoControlador,
                        jugadorControlador
                );

        MenuTablaPosiciones menuTablaPosiciones =
                new MenuTablaPosiciones(
                        tablaPosicionesControlador,
                        torneoControlador
                );

        MenuConsultaReporte menuConsultaReporte =
                new MenuConsultaReporte(
                        consultaReporteControlador
                );

        // ==========================================
        // MENÚ PRINCIPAL
        // ==========================================

        int opcion;

        do {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("SISTEMA DE GESTIÓN DE LIGA DEPORTIVA");
            System.out.println("==================================================");
            System.out.println();
            System.out.println("1. Administración de Entrenadores");
            System.out.println("2. Administración de Jugadores");
            System.out.println("3. Administración de Árbitros");
            System.out.println("4. Administración de Equipos");
            System.out.println("5. Organización de Torneos");
            System.out.println("6. Programación de Partidos");
            System.out.println("7. Gestión de Resultados");
            System.out.println("8. Tabla de Posiciones");
            System.out.println("9. Consultas y Estadísticas");
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println("0. Salir del sistema");
            System.out.println("==================================================");

            opcion = leerEntero(
                    scanner,
                    "Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    menuEntrenador.mostrarMenuEntrenadores();
                    break;

                case 2:
                    menuJugador.mostrarMenuJugadores();
                    break;

                case 3:
                    menuArbitro.mostrarMenuArbitros();
                    break;

                case 4:
                    menuEquipo.mostrarMenuEquipos();
                    break;

                case 5:
                    menuTorneo.mostrarMenuTorneos();
                    break;

                case 6:
                    menuPartido.mostrarMenuPartidos();
                    break;

                case 7:
                    menuResultado.mostrarMenuResultados();
                    break;

                case 8:
                    menuTablaPosiciones
                            .mostrarMenuTablaPosiciones();
                    break;

                case 9:
                    menuConsultaReporte
                            .mostrarMenuConsultaReporte();
                    break;

                case 0:
                    System.out.println();
                    System.out.println(
                            "[SUCCESS] Gracias por utilizar el sistema.");
                    break;

                default:
                    System.out.println(
                            "[ERROR] Opción no válida.");
                    System.out.println( "Presione ENTER para continuar...");
                    scanner.nextLine();
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static int leerEntero(
            Scanner scanner,
            String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Integer.parseInt(
                        scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "[ERROR] Debe ingresar un número entero válido.");
            }
        }
    }
}

package liga_deportiva.controlador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import liga_deportiva.modelo.Arbitro;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.modelo.Estadio;
import liga_deportiva.modelo.EstadoPartido;
import liga_deportiva.modelo.EstadoTorneo;
import liga_deportiva.modelo.EventoJugador;
import liga_deportiva.modelo.Jugador;
import liga_deportiva.modelo.Partido;
import liga_deportiva.modelo.ResultadoPartido;
import liga_deportiva.modelo.Torneo;

public class ResultadoControlador {

    private List<ResultadoPartido> resultados;

    public ResultadoControlador() {

        this.resultados = new ArrayList<>();

        // DATOS DE APOYO

        Torneo torneo =
                new Torneo(
                        "T001",
                        "Liga Pro Serie A 2026",
                        LocalDate.of(2026, 1, 15),
                        LocalDate.of(2026, 12, 15),
                        EstadoTorneo.ACTIVO
                );

        Estadio estadio =
                new Estadio(
                        "EST001",
                        "Monumental Banco Pichincha",
                        59000
                );

        Equipo local1 =
                new Equipo(
                        "EQ001",
                        "Barcelona SC",
                        "Guayaquil"
                );

        Equipo visita1 =
                new Equipo(
                        "EQ002",
                        "Emelec",
                        "Guayaquil"
                );

        Equipo local2 =
                new Equipo(
                        "EQ003",
                        "Liga de Quito",
                        "Quito"
                );

        Equipo visita2 =
                new Equipo(
                        "EQ004",
                        "Aucas",
                        "Quito"
                );

        Equipo local3 =
                new Equipo(
                        "EQ005",
                        "Independiente del Valle",
                        "Sangolquí"
                );

        Equipo visita3 =
                new Equipo(
                        "EQ006",
                        "Delfín",
                        "Manta"
                );

        Arbitro arbitro =
                new Arbitro(
                        "A001",
                        "José Pérez",
                        45,
                        10
                );

        Partido partido1 =
                new Partido(
                        "P001",
                        LocalDate.of(2026, 7, 10),
                        LocalTime.of(19, 0),
                        EstadoPartido.FINALIZADO,
                        torneo,
                        estadio,
                        local1,
                        visita1,
                        arbitro
                );

        Partido partido2 =
                new Partido(
                        "P002",
                        LocalDate.of(2026, 7, 12),
                        LocalTime.of(16, 0),
                        EstadoPartido.FINALIZADO,
                        torneo,
                        estadio,
                        local2,
                        visita2,
                        arbitro
                );

        Partido partido3 =
                new Partido(
                        "P003",
                        LocalDate.of(2026, 6, 20),
                        LocalTime.of(18, 0),
                        EstadoPartido.FINALIZADO,
                        torneo,
                        estadio,
                        local3,
                        visita3,
                        arbitro
                );

        resultados.add(
                new ResultadoPartido(
                        partido1,
                        2,
                        1
                )
        );

        resultados.add(
                new ResultadoPartido(
                        partido2,
                        0,
                        0
                )
        );

        resultados.add(
                new ResultadoPartido(
                        partido3,
                        3,
                        2
                )
        );
    }

    public boolean registrarResultado(ResultadoPartido resultado) {

        if (resultado == null) {
            return false;
        }

        if (resultado.getPartido() == null) {
            return false;
        }

        if (existeResultado(resultado.getPartido())) {
            return false;
        }

        if (!validarPuntajeNoNegativo(
                resultado.obtenerGolesEquipoLocal())) {
            return false;
        }

        if (!validarPuntajeNoNegativo(
                resultado.obtenerGolesEquipoVisitante())) {
            return false;
        }

        return resultados.add(resultado);
    }

    public boolean corregirResultado(
            ResultadoPartido resultadoCorregido) {

        if (resultadoCorregido == null) {
            return false;
        }

        if (resultadoCorregido.getPartido() == null) {
            return false;
        }

        if (!validarPuntajeNoNegativo(
                resultadoCorregido.obtenerGolesEquipoLocal())) {
            return false;
        }

        if (!validarPuntajeNoNegativo(
                resultadoCorregido.obtenerGolesEquipoVisitante())) {
            return false;
        }

        for (int i = 0; i < resultados.size(); i++) {

            if (resultados.get(i).getPartido()
                    .equals(
                            resultadoCorregido.getPartido())) {

                resultados.set(i, resultadoCorregido);
                return true;
            }
        }

        return false;
    }

    public ResultadoPartido buscarResultadoPorPartido(
            Partido partido) {

        if (partido == null) {

        return null;
        }
        
        for (ResultadoPartido resultado : resultados) {

            if (resultado.getPartido()
                    .equals(partido)) {

                return resultado;
            }
        }

        return null;
    }

    public boolean agregarEventoJugador(
            Partido partido,
            EventoJugador eventoJugador) {

        if (partido == null) {

        return false;
        }
        
        ResultadoPartido resultado =
                buscarResultadoPorPartido(partido);

        if (resultado == null
                || eventoJugador == null) {
            return false;
        }

        if (eventoJugador.getJugador() == null) {
            return false;
        }

        resultado.agregarEvento(eventoJugador);
        return true;
    }

    public List<ResultadoPartido> listarResultados() {
        return new ArrayList<>(resultados);
    }

    public boolean existeResultado(Partido partido) {
        return buscarResultadoPorPartido(partido) != null;
    }

    public boolean validarJugadorExistente(
            Jugador jugador) {

        return jugador != null;
    }

    public boolean validarPuntajeNoNegativo(
            int puntaje) {

        return puntaje >= 0;
    }

    public boolean validarConsistenciaGoles(
            ResultadoPartido resultado) {

        if (resultado == null) {
            return false;
        }

        return resultado.validarConsistenciaGoles();
    }

    public ResultadoPartido obtenerResultado(
            Partido partido) {

        if (partido == null) {

        return null;
        }
        
        return buscarResultadoPorPartido(partido);
    }
}
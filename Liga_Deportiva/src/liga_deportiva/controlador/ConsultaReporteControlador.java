package liga_deportiva.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liga_deportiva.modelo.Arbitro;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.modelo.Entrenador;
import liga_deportiva.modelo.EventoJugador;
import liga_deportiva.modelo.Jugador;
import liga_deportiva.modelo.Partido;
import liga_deportiva.modelo.PosicionEquipo;
import liga_deportiva.modelo.ResultadoPartido;
import liga_deportiva.modelo.TablaPosiciones;
import liga_deportiva.modelo.Torneo;

public class ConsultaReporteControlador {

    private EquipoControlador equipoControlador;
    private JugadorControlador jugadorControlador;
    private EntrenadorControlador entrenadorControlador;
    private ArbitroControlador arbitroControlador;
    private TorneoControlador torneoControlador;
    private PartidoControlador partidoControlador;
    private ResultadoControlador resultadoControlador;
    private TablaPosicionesControlador tablaPosicionesControlador;

    public ConsultaReporteControlador(
            EquipoControlador equipoControlador,
            JugadorControlador jugadorControlador,
            EntrenadorControlador entrenadorControlador,
            ArbitroControlador arbitroControlador,
            TorneoControlador torneoControlador,
            PartidoControlador partidoControlador,
            ResultadoControlador resultadoControlador,
            TablaPosicionesControlador tablaPosicionesControlador) {

        this.equipoControlador = equipoControlador;
        this.jugadorControlador = jugadorControlador;
        this.entrenadorControlador = entrenadorControlador;
        this.arbitroControlador = arbitroControlador;
        this.torneoControlador = torneoControlador;
        this.partidoControlador = partidoControlador;
        this.resultadoControlador = resultadoControlador;
        this.tablaPosicionesControlador = tablaPosicionesControlador;
    }

    // =========================
    // CONSULTAS
    // =========================

    public List<Equipo> listarEquipos() {
        return equipoControlador.listarEquipos();
    }

    public List<Jugador> listarJugadoresPorEquipo(String codigoEquipo) {

        if (codigoEquipo == null || codigoEquipo.trim().isEmpty()) {
        return new ArrayList<>();
        }
        
        Equipo equipo = equipoControlador.obtenerEquipo(codigoEquipo);
        
        if (equipo == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(equipo.getJugadores());
    }

    public List<Entrenador> listarEntrenadores() {
        return entrenadorControlador.listarEntrenadores();
    }

    public List<Arbitro> listarArbitros() {
        return arbitroControlador.listarArbitros();
    }

    public List<Torneo> listarTorneos() {
        return torneoControlador.listarTorneos();
    }

    public List<Partido> listarPartidosProgramados() {
        return partidoControlador.listarPartidosProgramados();
    }

    public List<Partido> listarPartidosJugados() {
        return partidoControlador.listarPartidosFinalizados();
    }

    public List<Partido> obtenerHistorialPartidosEquipo(String codigoEquipo) {

        if (codigoEquipo == null
        || codigoEquipo.trim().isEmpty()) {

        return new ArrayList<>();
        }
        
        List<Partido> historial = new ArrayList<>();

        Equipo equipo = equipoControlador.obtenerEquipo(codigoEquipo);

        if (equipo == null) {
            return historial;
        }

        for (Partido partido : partidoControlador.listarPartidos()) {

            if (partido.getEquipoLocal().equals(equipo)
                    || partido.getEquipoVisitante().equals(equipo)) {

                historial.add(partido);
            }
        }

        return historial;
    }

    public TablaPosiciones obtenerTablaPosiciones(String codigoTorneo) {

        
        if (codigoTorneo == null
        || codigoTorneo.trim().isEmpty()) {

        return null;
        }
        
        Torneo torneo = torneoControlador.obtenerTorneo(codigoTorneo);

        if (torneo == null) {
            return null;
        }

        return tablaPosicionesControlador.obtenerTabla(torneo);
    }

    // =========================
    // ESTADÍSTICAS
    // =========================

    public String obtenerEstadisticasJugador(String codigoJugador) {

        if (codigoJugador == null
        || codigoJugador.trim().isEmpty()) {
        return "Jugador no encontrado.";
        }
        
        Jugador jugador = jugadorControlador.obtenerJugador(codigoJugador);

        if (jugador == null) {
            return "Jugador no encontrado.";
        }

        int goles = 0;
        int amarillas = 0;
        int rojas = 0;
        int faltas = 0;

        for (ResultadoPartido resultado
                : resultadoControlador.listarResultados()) {

            for (EventoJugador evento
                    : resultado.obtenerEventos()) {

                if (evento.getJugador().equals(jugador)) {

                    goles += evento.getGoles();
                    amarillas += evento.getTarjetasAmarillas();
                    rojas += evento.getTarjetasRojas();
                    faltas += evento.getFaltas();
                }
            }
        }

        return "Jugador: " + jugador.getNombre()
                + "\nGoles: " + goles
                + "\nTarjetas Amarillas: " + amarillas
                + "\nTarjetas Rojas: " + rojas
                + "\nFaltas: " + faltas;
    }

    public Equipo obtenerEquipoMasVictorias() {

        Map<Equipo, Integer> victorias = new HashMap<>();

        for (ResultadoPartido resultado
                : resultadoControlador.listarResultados()) {

            Partido partido = resultado.getPartido();

            int golesLocal = resultado.obtenerGolesEquipoLocal();
            int golesVisitante = resultado.obtenerGolesEquipoVisitante();

            if (golesLocal > golesVisitante) {

                Equipo equipo = partido.getEquipoLocal();

                victorias.put(
                        equipo,
                        victorias.getOrDefault(equipo, 0) + 1
                );

            } else if (golesVisitante > golesLocal) {

                Equipo equipo = partido.getEquipoVisitante();

                victorias.put(
                        equipo,
                        victorias.getOrDefault(equipo, 0) + 1
                );
            }
        }

        Equipo mejorEquipo = null;
        int maxVictorias = -1;

        for (Map.Entry<Equipo, Integer> entry
                : victorias.entrySet()) {

            if (entry.getValue() > maxVictorias) {

                maxVictorias = entry.getValue();
                mejorEquipo = entry.getKey();
            }
        }

        return mejorEquipo;
    }

    public Equipo obtenerEquipoMasGoles() {

        Map<Equipo, Integer> golesPorEquipo = new HashMap<>();

        for (ResultadoPartido resultado
                : resultadoControlador.listarResultados()) {

            Partido partido = resultado.getPartido();

            Equipo local = partido.getEquipoLocal();
            Equipo visitante = partido.getEquipoVisitante();

            golesPorEquipo.put(
                    local,
                    golesPorEquipo.getOrDefault(local, 0)
                    + resultado.obtenerGolesEquipoLocal()
            );

            golesPorEquipo.put(
                    visitante,
                    golesPorEquipo.getOrDefault(visitante, 0)
                    + resultado.obtenerGolesEquipoVisitante()
            );
        }

        Equipo mejorEquipo = null;
        int maxGoles = -1;

        for (Map.Entry<Equipo, Integer> entry
                : golesPorEquipo.entrySet()) {

            if (entry.getValue() > maxGoles) {

                maxGoles = entry.getValue();
                mejorEquipo = entry.getKey();
            }
        }

        return mejorEquipo;
    }

    public Jugador obtenerMaximoGoleador() {

        Map<Jugador, Integer> golesJugador = new HashMap<>();

        for (ResultadoPartido resultado
                : resultadoControlador.listarResultados()) {

            for (EventoJugador evento
                    : resultado.obtenerEventos()) {

                Jugador jugador = evento.getJugador();

                golesJugador.put(
                        jugador,
                        golesJugador.getOrDefault(jugador, 0)
                        + evento.getGoles()
                );
            }
        }

        Jugador goleador = null;
        int maxGoles = -1;

        for (Map.Entry<Jugador, Integer> entry
                : golesJugador.entrySet()) {

            if (entry.getValue() > maxGoles) {

                maxGoles = entry.getValue();
                goleador = entry.getKey();
            }
        }

        return goleador;
    }

    public int obtenerTotalGolesTorneo(String codigoTorneo) {

        if (codigoTorneo == null
        || codigoTorneo.trim().isEmpty()) {

        return 0;
        }
        
        Torneo torneo = torneoControlador.obtenerTorneo(codigoTorneo);

        if (torneo == null) {
            return 0;
        }

        int total = 0;

        for (ResultadoPartido resultado
                : resultadoControlador.listarResultados()) {

            if (resultado.getPartido()
                    .getTorneo()
                    .equals(torneo)) {

                total += resultado.obtenerGolesEquipoLocal();
                total += resultado.obtenerGolesEquipoVisitante();
            }
        }

        return total;
    }

    // =========================
    // REPORTES
    // =========================

    public String generarReporteEquipoMasVictorias() {

        Equipo equipo = obtenerEquipoMasVictorias();

        if (equipo == null) {
            return "No existen datos registrados.";
        }

        return "Equipo con más victorias: "
                + equipo.getNombre();
    }

    public String generarReporteTablaPosiciones(String codigoTorneo) {

        TablaPosiciones tabla =
                obtenerTablaPosiciones(codigoTorneo);

        if (tabla == null) {
            return "Tabla no encontrada.";
        }

        StringBuilder reporte = new StringBuilder();

        reporte.append("TABLA DE POSICIONES\n");
        reporte.append("-------------------\n");

        for (PosicionEquipo posicion
                : tabla.obtenerPosiciones()) {

            reporte.append(posicion)
                    .append("\n");
        }

        return reporte.toString();
    }

    public String generarReporteEstadisticasEquipos() {

        StringBuilder reporte = new StringBuilder();

        reporte.append("ESTADÍSTICAS DE EQUIPOS\n");
        reporte.append("-----------------------\n");

        Equipo masVictorias = obtenerEquipoMasVictorias();
        Equipo masGoles = obtenerEquipoMasGoles();

        reporte.append("Equipo con más victorias: ")
                .append(masVictorias != null
                        ? masVictorias.getNombre()
                        : "N/D")
                .append("\n");

        reporte.append("Equipo con más goles: ")
                .append(masGoles != null
                        ? masGoles.getNombre()
                        : "N/D")
                .append("\n");

        return reporte.toString();
    }

    public String generarReporteEstadisticasJugadores() {

        StringBuilder reporte = new StringBuilder();

        reporte.append("ESTADÍSTICAS DE JUGADORES\n");
        reporte.append("-------------------------\n");

        Jugador goleador = obtenerMaximoGoleador();

        if (goleador != null) {

            reporte.append("Máximo goleador: ")
                    .append(goleador.getNombre());
        } else {

            reporte.append("No existen datos registrados.");
        }

        return reporte.toString();
    }
}

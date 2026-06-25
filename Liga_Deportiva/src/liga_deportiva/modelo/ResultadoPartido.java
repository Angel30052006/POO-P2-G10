package liga_deportiva.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultadoPartido {

    private Partido partido;
    private int golesEquipoLocal;
    private int golesEquipoVisitante;
    private List<EventoJugador> eventosJugador;

    public ResultadoPartido(Partido partido,
                            int golesEquipoLocal,
                            int golesEquipoVisitante) {
        
        if (partido == null) {
        throw new IllegalArgumentException(
            "El partido es obligatorio.");
        }

        if (golesEquipoLocal < 0) {
        throw new IllegalArgumentException(
            "Los goles del equipo local no pueden ser negativos.");
        }

        if (golesEquipoVisitante < 0) {
        throw new IllegalArgumentException(
            "Los goles del equipo visitante no pueden ser negativos.");
        }

        this.partido = partido;
        this.golesEquipoLocal = golesEquipoLocal;
        this.golesEquipoVisitante = golesEquipoVisitante;
        this.eventosJugador = new ArrayList<>();
    }

    public Partido getPartido() {
        return partido;
    }

    public int obtenerGolesEquipoLocal() {
        return golesEquipoLocal;
    }

    public int obtenerGolesEquipoVisitante() {
        return golesEquipoVisitante;
    }

    public List<EventoJugador> obtenerEventos() {
        return eventosJugador;
    }

    public void agregarEvento(EventoJugador evento) {

        if (evento != null) {
            eventosJugador.add(evento);
        }
    }

    public boolean validarConsistenciaGoles() {

        int totalGolesRegistrados = 0;

        for (EventoJugador evento : eventosJugador) {
            totalGolesRegistrados += evento.getGoles();
        }

        return totalGolesRegistrados ==
                (golesEquipoLocal + golesEquipoVisitante);
    }

    public String determinarGanador() {

        if (golesEquipoLocal > golesEquipoVisitante) {
            return "LOCAL";
        }

        if (golesEquipoVisitante > golesEquipoLocal) {
            return "VISITANTE";
        }

        return "EMPATE";
    }

    @Override
    public String toString() {
        return "ResultadoPartido{" +
                "partido=" + partido +
                ", golesEquipoLocal=" + golesEquipoLocal +
                ", golesEquipoVisitante=" + golesEquipoVisitante +
                ", cantidadEventos=" + eventosJugador.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ResultadoPartido)) {
            return false;
        }

        ResultadoPartido otro = (ResultadoPartido) obj;

        return Objects.equals(partido, otro.partido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partido);
    }
}

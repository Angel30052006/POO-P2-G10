package liga_deportiva.modelo;


import java.util.Objects;

public class PosicionEquipo {

    private Equipo equipo;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesFavor;
    private int golesContra;
    private int diferenciaGoles;
    private int puntos;

    public PosicionEquipo(Equipo equipo,
                          int partidosJugados,
                          int partidosGanados,
                          int partidosEmpatados,
                          int partidosPerdidos,
                          int golesFavor,
                          int golesContra,
                          int puntos) {
        
        if (equipo == null) {
        throw new IllegalArgumentException(
                "El equipo es obligatorio.");
        }

        if (partidosJugados < 0) {
        throw new IllegalArgumentException(
             "Los partidos jugados no pueden ser negativos.");
        }

        if (partidosGanados < 0) {
        throw new IllegalArgumentException(
                "Los partidos ganados no pueden ser negativos.");
        }

        if (partidosEmpatados < 0) {
        throw new IllegalArgumentException(
                "Los partidos empatados no pueden ser negativos.");
        }

        if (partidosPerdidos < 0) {
        throw new IllegalArgumentException(
                "Los partidos perdidos no pueden ser negativos.");
        }

        if (golesFavor < 0) {
        throw new IllegalArgumentException(
                "Los goles a favor no pueden ser negativos.");
        }

        if (golesContra < 0) {
        throw new IllegalArgumentException(
                "Los goles en contra no pueden ser negativos.");
        }

        if (puntos < 0) {
        throw new IllegalArgumentException(
                "Los puntos no pueden ser negativos.");
        }

        if (partidosGanados
            + partidosEmpatados
            + partidosPerdidos
            > partidosJugados) {

        throw new IllegalArgumentException(
                "La suma de resultados no puede superar los partidos jugados.");
        }
        

        this.equipo = equipo;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosEmpatados = partidosEmpatados;
        this.partidosPerdidos = partidosPerdidos;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
        this.puntos = puntos;

        actualizarDiferenciaGoles();
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public int getPuntos() {
        return puntos;
    }

    public void actualizarDiferenciaGoles() {
        this.diferenciaGoles = golesFavor - golesContra;
    }

    public int obtenerPuntos() {
        return puntos;
    }

    @Override
    public String toString() {
        return "PosicionEquipo{" +
                "equipo=" + equipo +
                ", partidosJugados=" + partidosJugados +
                ", partidosGanados=" + partidosGanados +
                ", partidosEmpatados=" + partidosEmpatados +
                ", partidosPerdidos=" + partidosPerdidos +
                ", golesFavor=" + golesFavor +
                ", golesContra=" + golesContra +
                ", diferenciaGoles=" + diferenciaGoles +
                ", puntos=" + puntos +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PosicionEquipo)) {
            return false;
        }

        PosicionEquipo otra = (PosicionEquipo) obj;

        return Objects.equals(equipo, otra.equipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipo);
    }
}


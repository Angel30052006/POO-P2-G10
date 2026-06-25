package liga_deportiva.modelo;

import java.util.Objects;

public class EventoJugador {

    private Jugador jugador;
    private int goles;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int faltas;

    public EventoJugador(Jugador jugador) {
        
        
        if (jugador == null) {
        throw new IllegalArgumentException(
                "El jugador es obligatorio.");
        }
        
        this.jugador = jugador;
        this.goles = 0;
        this.tarjetasAmarillas = 0;
        this.tarjetasRojas = 0;
        this.faltas = 0;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getGoles() {
        return goles;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public int getFaltas() {
        return faltas;
    }

    public void registrarGol() {
        goles++;
    }

    public void registrarAmarilla() {
        tarjetasAmarillas++;
    }

    public void registrarRoja() {
        tarjetasRojas++;
    }

    public void registrarFalta() {
        faltas++;
    }

    @Override
    public String toString() {
        return "EventoJugador{" +
                "jugador=" + jugador +
                ", goles=" + goles +
                ", tarjetasAmarillas=" + tarjetasAmarillas +
                ", tarjetasRojas=" + tarjetasRojas +
                ", faltas=" + faltas +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EventoJugador)) {
            return false;
        }

        EventoJugador otro = (EventoJugador) obj;

        return Objects.equals(jugador, otro.jugador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jugador);
    }
}


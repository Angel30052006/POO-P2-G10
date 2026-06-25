package liga_deportiva.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TablaPosiciones {

    private Torneo torneo;
    private List<PosicionEquipo> posiciones;

    public TablaPosiciones(Torneo torneo) {
        
        if (torneo == null) {

        throw new IllegalArgumentException(
                "El torneo es obligatorio.");
        }
        this.torneo = torneo;
        this.posiciones = new ArrayList<>();
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public List<PosicionEquipo> obtenerPosiciones() {
        return posiciones;
    }

    public void ordenarTabla() {

        posiciones.sort((p1, p2) -> {

            if (p2.getPuntos() != p1.getPuntos()) {
                return Integer.compare(
                        p2.getPuntos(),
                        p1.getPuntos());
            }

            if (p2.getDiferenciaGoles()
                    != p1.getDiferenciaGoles()) {

                return Integer.compare(
                        p2.getDiferenciaGoles(),
                        p1.getDiferenciaGoles());
            }

            return Integer.compare(
                    p2.getGolesFavor(),
                    p1.getGolesFavor());
        });
    }

    public PosicionEquipo buscarPosicionEquipo(Equipo equipo) {
        
        if (equipo == null) {

            throw new IllegalArgumentException(
                    "El equipo es obligatorio.");
        }
        
        for (PosicionEquipo posicion : posiciones) {

            if (posicion.getEquipo().equals(equipo)) {
                return posicion;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "TablaPosiciones{" +
                "torneo=" + torneo +
                ", posiciones=" + posiciones +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TablaPosiciones)) {
            return false;
        }

        TablaPosiciones otra = (TablaPosiciones) obj;

        return Objects.equals(torneo, otra.torneo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(torneo);
    }
}

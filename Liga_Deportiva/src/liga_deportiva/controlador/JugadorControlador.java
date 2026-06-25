package liga_deportiva.controlador;

import java.util.ArrayList;
import java.util.List;
import liga_deportiva.modelo.Jugador;

public class JugadorControlador {

    private List<Jugador> jugadores;

    public JugadorControlador() {

        this.jugadores = new ArrayList<>();

        // DATOS PRECARGADOS

        jugadores.add(
                new Jugador(
                        "J001",
                        "Pedro Gómez",
                        24,
                        10,
                        "Delantero",
                        false
                )
        );

        jugadores.add(
                new Jugador(
                        "J002",
                        "Carlos Vera",
                        22,
                        9,
                        "Delantero",
                        false
                )
        );

        jugadores.add(
                new Jugador(
                        "J003",
                        "Luis Andrade",
                        28,
                        8,
                        "Mediocampista",
                        false
                )
        );

        jugadores.add(
                new Jugador(
                        "J004",
                        "Andrés Molina",
                        21,
                        7,
                        "Extremo",
                        false
                )
        );

        jugadores.add(
                new Jugador(
                        "J005",
                        "Diego Suárez",
                        26,
                        1,
                        "Portero",
                        false
                )
        );

        jugadores.add(
                new Jugador(
                        "J006",
                        "Kevin Torres",
                        17,
                        11,
                        "Delantero",
                        true
                )
        );

        jugadores.add(
                new Jugador(
                        "J007",
                        "José Castillo",
                        19,
                        5,
                        "Defensa",
                        false
                )
        );

        jugadores.add(
                new Jugador(
                        "J008",
                        "Marco Flores",
                        23,
                        4,
                        "Defensa",
                        false
                )
        );
    }

    public boolean registrarJugador(Jugador jugador) {

        if (jugador == null) {
            return false;
        }

        if (existeJugador(jugador.getCodigo())) {
            return false;
        }

        if (jugador.getEdad() < 15
        || jugador.getEdad() > 70) {
        return false;
        }

        if (jugador.getNumeroCamiseta() <= 0) {
            return false;
        }

        if (jugador.getPosicion() == null
                || jugador.getPosicion().trim().isEmpty()) {
            return false;
        }

        if (jugador.getEdad() < 18
                && !jugador.isAutorizacionEspecial()) {
            return false;
        }

        return jugadores.add(jugador);
    }

    public Jugador buscarJugadorPorCodigo(String codigo) {

        if (codigo == null
        || codigo.trim().isEmpty()) {

        return null;
        }
        
        for (Jugador jugador : jugadores) {

            if (jugador.getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return jugador;
            }
        }

        return null;
    }

    public Jugador buscarJugadorPorNombre(String nombre) {

        if (nombre == null
        || nombre.trim().isEmpty()) {

        return null;
        }
        
        for (Jugador jugador : jugadores) {

            if (jugador.getNombre()
                    .equalsIgnoreCase(nombre)) {

                return jugador;
            }
        }

        return null;
    }

    public boolean modificarJugador(
            Jugador jugadorModificado) {

        if (jugadorModificado == null) {
            return false;
        }
        
        if (jugadorModificado.getCodigo() == null
        || jugadorModificado.getCodigo()
                .trim().isEmpty()) {

        return false;
        }

        if (jugadorModificado.getEdad() < 15
        || jugadorModificado.getEdad() > 70) {
        return false;
        }

        if (jugadorModificado.getNumeroCamiseta() <= 0) {
            return false;
        }

        if (jugadorModificado.getPosicion() == null
                || jugadorModificado.getPosicion()
                .trim().isEmpty()) {
            return false;
        }

        if (jugadorModificado.getEdad() < 18
                && !jugadorModificado.isAutorizacionEspecial()) {
            return false;
        }

        for (int i = 0; i < jugadores.size(); i++) {

            if (jugadores.get(i).getCodigo()
                    .equalsIgnoreCase(
                            jugadorModificado.getCodigo())) {

                jugadores.set(i, jugadorModificado);
                return true;
            }
        }

        return false;
    }

    public List<Jugador> listarJugadores() {
        return new ArrayList<>(jugadores);
    }

    public boolean existeJugador(String codigo) {
        return buscarJugadorPorCodigo(codigo) != null;
    }

    public Jugador obtenerJugador(String codigo) {
        return buscarJugadorPorCodigo(codigo);
    }
}
package liga_deportiva.controlador;

import java.util.ArrayList;
import java.util.List;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.modelo.Entrenador;
import liga_deportiva.modelo.Jugador;

public class EquipoControlador {

    private List<Equipo> equipos;

    public EquipoControlador() {

        this.equipos = new ArrayList<>();

        // DATOS PRECARGADOS

        equipos.add(
                new Equipo(
                        "EQ001",
                        "Barcelona SC",
                        "Guayaquil"
                )
        );

        equipos.add(
                new Equipo(
                        "EQ002",
                        "Emelec",
                        "Guayaquil"
                )
        );

        equipos.add(
                new Equipo(
                        "EQ003",
                        "Liga de Quito",
                        "Quito"
                )
        );

        equipos.add(
                new Equipo(
                        "EQ004",
                        "Independiente del Valle",
                        "Sangolquí"
                )
        );

        equipos.add(
                new Equipo(
                        "EQ005",
                        "Aucas",
                        "Quito"
                )
        );

        equipos.add(
                new Equipo(
                        "EQ006",
                        "Delfín",
                        "Manta"
                )
        );
    }

    public boolean registrarEquipo(Equipo equipo) {

        if (equipo == null) {
            return false;
        }

        if (existeEquipo(equipo.getCodigo())) {
            return false;
        }

        if (equipo.getCodigo() == null
                || equipo.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (equipo.getNombre() == null
                || equipo.getNombre().trim().isEmpty()) {
            return false;
        }

        if (equipo.getCiudadOrigen() == null
                || equipo.getCiudadOrigen().trim().isEmpty()) {
            return false;
        }

        return equipos.add(equipo);
    }

    public Equipo buscarEquipoPorCodigo(String codigo) {

        if (codigo == null
        || codigo.trim().isEmpty()) {

        return null;
        }
        
        for (Equipo equipo : equipos) {

            if (equipo.getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return equipo;
            }
        }

        return null;
    }

    public Equipo buscarEquipoPorNombre(String nombre) {

        
        if (nombre == null
        || nombre.trim().isEmpty()) {

        return null;
        }
        
        for (Equipo equipo : equipos) {

            if (equipo.getNombre()
                    .equalsIgnoreCase(nombre)) {

                return equipo;
            }
        }

        return null;
    }

    public boolean modificarEquipo(
            Equipo equipoModificado) {

        if (equipoModificado == null) {
            return false;
        }

        if (equipoModificado.getCodigo() == null
                || equipoModificado.getCodigo()
                .trim().isEmpty()) {
            return false;
        }

        if (equipoModificado.getNombre() == null
                || equipoModificado.getNombre()
                .trim().isEmpty()) {
            return false;
        }

        if (equipoModificado.getCiudadOrigen() == null
                || equipoModificado.getCiudadOrigen()
                .trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < equipos.size(); i++) {

            if (equipos.get(i).getCodigo()
                    .equalsIgnoreCase(
                            equipoModificado.getCodigo())) {

                equipos.set(i, equipoModificado);
                return true;
            }
        }

        return false;
    }

    public List<Equipo> listarEquipos() {
        return new ArrayList<>(equipos);
    }

    public boolean existeEquipo(String codigo) {
        return buscarEquipoPorCodigo(codigo) != null;
    }

    public Equipo obtenerEquipo(String codigo) {
        return buscarEquipoPorCodigo(codigo);
    }

    public boolean asignarEntrenador(
            String codigoEquipo,
            Entrenador entrenador) {

        
        if (codigoEquipo == null
        || codigoEquipo.trim().isEmpty()) {

        return false;
        }
        
        
        Equipo equipo =
                buscarEquipoPorCodigo(codigoEquipo);

        if (equipo == null || entrenador == null) {
            return false;
        }

        if (equipo.getEntrenador() != null
                && equipo.getEntrenador()
                .equals(entrenador)) {
            return false;
        }

        equipo.setEntrenador(entrenador);
        return true;
    }

    public boolean removerEntrenador(
            String codigoEquipo) {

        if (codigoEquipo == null
        || codigoEquipo.trim().isEmpty()) {

        return false;
        }
        
        Equipo equipo =
                buscarEquipoPorCodigo(codigoEquipo);

        if (equipo == null) {
            return false;
        }

        equipo.setEntrenador(null);
        return true;
    }

    public boolean agregarJugador(
            String codigoEquipo,
            Jugador jugador) {

        if (codigoEquipo == null
        || codigoEquipo.trim().isEmpty()) {

        return false;
        }
        
        Equipo equipo =
                buscarEquipoPorCodigo(codigoEquipo);

        if (equipo == null || jugador == null) {
            return false;
        }

        if (!equipo.getJugadores().contains(jugador)) {

            equipo.getJugadores().add(jugador);
            return true;
        }

        return false;
    }

    public boolean quitarJugador(
            String codigoEquipo,
            Jugador jugador) {

        if (codigoEquipo == null
        || codigoEquipo.trim().isEmpty()) {

        return false;
        }
        
        Equipo equipo =
                buscarEquipoPorCodigo(codigoEquipo);

        if (equipo == null || jugador == null) {
            return false;
        }

        return equipo.getJugadores().remove(jugador);
    }
}

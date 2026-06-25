package liga_deportiva.controlador;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import liga_deportiva.modelo.EstadoTorneo;
import liga_deportiva.modelo.Torneo;
import liga_deportiva.modelo.Partido;
import liga_deportiva.modelo.Equipo;

public class TorneoControlador {

    private List<Torneo> torneos;

    public TorneoControlador() {

        this.torneos = new ArrayList<>();

        // DATOS PRECARGADOS

        torneos.add(
                new Torneo(
                        "T001",
                        "Liga Pro Serie A 2026",
                        LocalDate.of(2026, 1, 15),
                        LocalDate.of(2026, 12, 15),
                        EstadoTorneo.ACTIVO
                )
        );

        torneos.add(
                new Torneo(
                        "T002",
                        "Copa Ecuador 2026",
                        LocalDate.of(2026, 2, 1),
                        LocalDate.of(2026, 11, 30),
                        EstadoTorneo.ACTIVO
                )
        );

        torneos.add(
                new Torneo(
                        "T003",
                        "Torneo Juvenil Sub-18",
                        LocalDate.of(2026, 3, 1),
                        LocalDate.of(2026, 8, 31),
                        EstadoTorneo.ACTIVO
                )
        );
    }

    public boolean registrarTorneo(Torneo torneo) {

        if (torneo == null) {
            return false;
        }

        if (existeTorneo(torneo.getCodigo())) {
            return false;
        }

        if (torneo.getCodigo() == null
                || torneo.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (torneo.getNombre() == null
                || torneo.getNombre().trim().isEmpty()) {
            return false;
        }

        if (torneo.getEstado() == null) {
            return false;
        }

        if (!torneo.validarFechas()) {
            return false;
        }

        return torneos.add(torneo);
    }

    public Torneo buscarTorneoPorCodigo(String codigo) {

        if (codigo == null
        || codigo.trim().isEmpty()) {
        return null;
        }
        
        for (Torneo torneo : torneos) {

            if (torneo.getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return torneo;
            }
        }

        return null;
    }

    public Torneo buscarTorneoPorNombre(String nombre) {
        
        if (nombre == null
        || nombre.trim().isEmpty()) {
        return null;
        }

        for (Torneo torneo : torneos) {

            if (torneo.getNombre()
                    .equalsIgnoreCase(nombre)) {

                return torneo;
            }
        }

        return null;
    }

    public boolean modificarTorneo(Torneo torneoModificado) {

        if (torneoModificado == null) {
            return false;
        }

        if (torneoModificado.getCodigo() == null
                || torneoModificado.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (torneoModificado.getNombre() == null
                || torneoModificado.getNombre().trim().isEmpty()) {
            return false;
        }

        if (torneoModificado.getEstado() == null) {
            return false;
        }

        if (!torneoModificado.validarFechas()) {
            return false;
        }
        
        if (!existeTorneo(
        torneoModificado.getCodigo())) {

        return false;
        }

        for (int i = 0; i < torneos.size(); i++) {

            if (torneos.get(i).getCodigo()
                    .equalsIgnoreCase(
                            torneoModificado.getCodigo())) {

                torneos.set(i, torneoModificado);
                return true;
            }
        }

        return false;
    }

    public boolean finalizarTorneo(String codigo) {

        if (codigo == null
        || codigo.trim().isEmpty()) {

        return false;
        }
        
        Torneo torneo =
                buscarTorneoPorCodigo(codigo);

        if (torneo == null) {
            return false;
        }

        if (!torneo.estaActivo()) {
            return false;
        }

        torneo.finalizarTorneo();
        return true;
    }

    public List<Torneo> listarTorneos() {
        return new ArrayList<>(torneos);
    }

    public boolean existeTorneo(String codigo) {
        return buscarTorneoPorCodigo(codigo) != null;
    }

    public Torneo obtenerTorneo(String codigo) {
        return buscarTorneoPorCodigo(codigo);
    }

    public boolean agregarEquipoATorneo(
            String codigoTorneo,
            Equipo equipo) {

        if (codigoTorneo == null
        || codigoTorneo.trim().isEmpty()) {

        return false;
        }
        
        Torneo torneo =
                buscarTorneoPorCodigo(codigoTorneo);

        if (torneo == null || equipo == null) {
            return false;
        }

        if (!torneo.estaActivo()) {
            return false;
        }

        if (!torneo.getEquiposParticipantes()
                .contains(equipo)) {

            torneo.getEquiposParticipantes()
                    .add(equipo);

            return true;
        }

        return false;
    }

    public boolean quitarEquipoDeTorneo(
            String codigoTorneo,
            Equipo equipo) {

        if (codigoTorneo == null
        || codigoTorneo.trim().isEmpty()) {
        return false;
        }
        
        Torneo torneo =
                buscarTorneoPorCodigo(codigoTorneo);

        if (torneo == null || equipo == null) {
            return false;
        }

        if (!torneo.estaActivo()) {
            return false;
        }

        return torneo.getEquiposParticipantes()
                .remove(equipo);
    }

    public boolean agregarPartidoATorneo(
            String codigoTorneo,
            Partido partido) {

        Torneo torneo =
                buscarTorneoPorCodigo(codigoTorneo);

        if (torneo == null || partido == null) {
            return false;
        }

        if (!torneo.estaActivo()) {
            return false;
        }

        if (!torneo.getPartidos()
                .contains(partido)) {

            torneo.getPartidos()
                    .add(partido);

            return true;
        }

        return false;
    }
}



package liga_deportiva.controlador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import liga_deportiva.modelo.Partido;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.modelo.Arbitro;
import liga_deportiva.modelo.Estadio;
import liga_deportiva.modelo.Torneo;
import liga_deportiva.modelo.EstadoPartido;
import liga_deportiva.modelo.EstadoTorneo;



public class PartidoControlador {

    private List<Partido> partidos;
    private List<Estadio> estadios;

    public PartidoControlador() {
        this.partidos = new ArrayList<>();
        this.estadios = new ArrayList<>();

   

        // ESTADIOS DE PRUEBA

        Estadio estadio1 =
                new Estadio(
                        "EST001",
                        "Monumental Banco Pichincha",
                        59000
                );

        Estadio estadio2 =
                new Estadio(
                        "EST002",
                        "George Capwell",
                        40000
                );

        Estadio estadio3 =
                new Estadio(
                        "EST003",
                        "Rodrigo Paz Delgado",
                        41000
                );
        
        estadios.add(estadio1);
        estadios.add(estadio2);
        estadios.add(estadio3);

        // EQUIPOS DE PRUEBA

        Equipo equipo1 =
                new Equipo(
                        "EQ001",
                        "Barcelona SC",
                        "Guayaquil"
                );

        Equipo equipo2 =
                new Equipo(
                        "EQ002",
                        "Emelec",
                        "Guayaquil"
                );

        Equipo equipo3 =
                new Equipo(
                        "EQ003",
                        "Liga de Quito",
                        "Quito"
                );

        Equipo equipo4 =
                new Equipo(
                        "EQ004",
                        "Aucas",
                        "Quito"
                );

        Equipo equipo5 =
                new Equipo(
                        "EQ005",
                        "Independiente del Valle",
                        "Sangolquí"
                );

        Equipo equipo6 =
                new Equipo(
                        "EQ006",
                        "Delfín",
                        "Manta"
                );

        // ÁRBITROS DE PRUEBA

        Arbitro arbitro1 =
                new Arbitro(
                        "A001",
                        "José Pérez",
                        45,
                        10
                );

        Arbitro arbitro2 =
                new Arbitro(
                        "A002",
                        "Carlos Sánchez",
                        39,
                        8
                );

        Arbitro arbitro3 =
                new Arbitro(
                        "A003",
                        "Diego Morales",
                        42,
                        12
                );

        // TORNEO DE PRUEBA

        Torneo torneo =
                new Torneo(
                        "T001",
                        "Liga Pro Serie A 2026",
                        LocalDate.of(2026, 1, 15),
                        LocalDate.of(2026, 12, 15),
                        EstadoTorneo.ACTIVO
                );

        // PARTIDOS PRECARGADOS

        partidos.add(
                new Partido(
                        "P001",
                        LocalDate.of(2026, 7, 10),
                        LocalTime.of(19, 0),
                        EstadoPartido.PROGRAMADO,
                        torneo,
                        estadio1,
                        equipo1,
                        equipo2,
                        arbitro1
                )
        );

        partidos.add(
                new Partido(
                        "P002",
                        LocalDate.of(2026, 7, 12),
                        LocalTime.of(16, 0),
                        EstadoPartido.PROGRAMADO,
                        torneo,
                        estadio3,
                        equipo3,
                        equipo4,
                        arbitro2
                )
        );

        partidos.add(
                new Partido(
                        "P003",
                        LocalDate.of(2026, 6, 20),
                        LocalTime.of(18, 0),
                        EstadoPartido.FINALIZADO,
                        torneo,
                        estadio2,
                        equipo5,
                        equipo6,
                        arbitro3
                )
        );
    }

    public boolean registrarPartido(Partido partido) {

        if (partido == null) {
            return false;
        }

        if (existePartido(partido.getCodigo())) {
            return false;
        }

        if (partido.getCodigo() == null
                || partido.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (partido.getFecha() == null) {
            return false;
        }

        if (partido.getHora() == null) {
            return false;
        }

        if (partido.getEstado() == null) {
            return false;
        }

        if (partido.getTorneo() == null) {
            return false;
        }

        if (partido.getEstadio() == null) {
            return false;
        }

        if (partido.getArbitro() == null) {
            return false;
        }

        if (partido.getEquipoLocal() == null
                || partido.getEquipoVisitante() == null) {
            return false;
        }

        if (!validarEquiposDistintos(
                partido.getEquipoLocal(),
                partido.getEquipoVisitante())) {
            return false;
        }

        return partidos.add(partido);
    }

    public Partido buscarPartidoPorCodigo(String codigo) {

        
        if (codigo == null
        || codigo.trim().isEmpty()) {

        return null;
        }
        
        for (Partido partido : partidos) {

            if (partido.getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return partido;
            }
        }

        return null;
    }
    
    public Estadio buscarEstadioPorCodigo(String codigo) {

        if (codigo == null
        || codigo.trim().isEmpty()) {

        return null;
        }
        
        for (Estadio estadio : estadios) {

            if (estadio.getCodigo()
                .equalsIgnoreCase(codigo)) {

            return estadio;
            }
        }

        return null;
    }

    public boolean modificarPartido(Partido partidoModificado) {

        if (partidoModificado == null) {
            return false;
        }
        
        if (partidoModificado.getCodigo() == null
        || partidoModificado.getCodigo()
                .trim().isEmpty()) {

        return false;
        }

        Partido existente =
                buscarPartidoPorCodigo(
                        partidoModificado.getCodigo());

        if (existente == null) {
            return false;
        }

        if (existente.estaFinalizado()) {
            return false;
        }

        for (int i = 0; i < partidos.size(); i++) {

            if (partidos.get(i).getCodigo()
                    .equalsIgnoreCase(
                            partidoModificado.getCodigo())) {

                partidos.set(i, partidoModificado);
                return true;
            }
        }

        return false;
    }

    public boolean cambiarEstado(
            String codigo,
            EstadoPartido nuevoEstado) {

        if (codigo == null
        || codigo.trim().isEmpty()) {

        return false;
        }
        
        Partido partido =
                buscarPartidoPorCodigo(codigo);

        if (partido == null || nuevoEstado == null) {
            return false;
        }

        if (partido.estaFinalizado()) {
            return false;
        }

        partido.cambiarEstado(nuevoEstado);
        return true;
    }

    public List<Partido> listarPartidos() {
        return new ArrayList<>(partidos);
    }

    public List<Partido> listarPartidosProgramados() {

        List<Partido> programados =
                new ArrayList<>();

        for (Partido partido : partidos) {

            if (partido.getEstado()
                    == EstadoPartido.PROGRAMADO) {

                programados.add(partido);
            }
        }

        return programados;
    }

    public List<Partido> listarPartidosFinalizados() {

        List<Partido> finalizados =
                new ArrayList<>();

        for (Partido partido : partidos) {

            if (partido.getEstado()
                    == EstadoPartido.FINALIZADO) {

                finalizados.add(partido);
            }
        }

        return finalizados;
    }

    public Partido obtenerPartido(String codigo) {
        return buscarPartidoPorCodigo(codigo);
    }
    
    public Estadio obtenerEstadio(String codigo) {
        return buscarEstadioPorCodigo(codigo);
    }

    public boolean existePartido(String codigo) {
        return buscarPartidoPorCodigo(codigo) != null;
    }

    public boolean validarEquiposDistintos(
            Equipo equipoLocal,
            Equipo equipoVisitante) {

        if (equipoLocal == null
                || equipoVisitante == null) {
            return false;
        }

        return !equipoLocal.equals(equipoVisitante);
    }

    public boolean validarArbitroDisponible(
            Arbitro arbitro) {

        return arbitro != null;
    }

    public boolean validarEstadioDisponible(
            Estadio estadio) {

        return estadio != null;
    }

    public boolean validarTorneoExistente(
            Torneo torneo) {

        return torneo != null;
    }
}
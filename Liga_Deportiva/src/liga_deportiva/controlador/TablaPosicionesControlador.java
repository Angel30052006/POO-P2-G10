package liga_deportiva.controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import liga_deportiva.modelo.EstadoTorneo;
import liga_deportiva.modelo.TablaPosiciones;
import liga_deportiva.modelo.Torneo;
import liga_deportiva.modelo.Equipo;
import liga_deportiva.modelo.PosicionEquipo;

public class TablaPosicionesControlador {

    private List<TablaPosiciones> tablas;

    public TablaPosicionesControlador() {

        this.tablas = new ArrayList<>();

        // TORNEOS PRECARGADOS

        Torneo torneo1 =
                new Torneo(
                        "T001",
                        "Liga Pro Serie A 2026",
                        LocalDate.of(2026, 1, 15),
                        LocalDate.of(2026, 12, 15),
                        EstadoTorneo.ACTIVO
                );

        Torneo torneo2 =
                new Torneo(
                        "T002",
                        "Copa Ecuador 2026",
                        LocalDate.of(2026, 2, 1),
                        LocalDate.of(2026, 11, 30),
                        EstadoTorneo.ACTIVO
                );

        Torneo torneo3 =
                new Torneo(
                        "T003",
                        "Torneo Juvenil Sub-18",
                        LocalDate.of(2026, 3, 1),
                        LocalDate.of(2026, 8, 31),
                        EstadoTorneo.ACTIVO
                );

        // TABLAS PRECARGADAS

        // ==========================================
// EQUIPOS PRECARGADOS
// ==========================================

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
                "Independiente del Valle",
                "Sangolquí"
        );

// ==========================================
// TABLA TORNEO 1
// ==========================================

TablaPosiciones tabla1 =
        new TablaPosiciones(torneo1);

tabla1.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo1,
                10,
                7,
                2,
                1,
                20,
                8,
                23
        )
);

tabla1.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo3,
                10,
                6,
                3,
                1,
                18,
                10,
                21
        )
);

tabla1.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo2,
                10,
                5,
                3,
                2,
                16,
                11,
                18
        )
);

tabla1.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo4,
                10,
                4,
                4,
                2,
                15,
                12,
                16
        )
);

tabla1.ordenarTabla();

tablas.add(tabla1);

// ==========================================
// TABLA TORNEO 2
// ==========================================

TablaPosiciones tabla2 =
        new TablaPosiciones(torneo2);

tabla2.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo1,
                5,
                4,
                1,
                0,
                12,
                3,
                13
        )
);

tabla2.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo2,
                5,
                3,
                1,
                1,
                10,
                5,
                10
        )
);

tabla2.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo3,
                5,
                2,
                2,
                1,
                8,
                6,
                8
        )
);

tabla2.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo4,
                5,
                1,
                0,
                4,
                4,
                12,
                3
        )
);

tabla2.ordenarTabla();

tablas.add(tabla2);

// ==========================================
// TABLA TORNEO 3
// ==========================================

TablaPosiciones tabla3 =
        new TablaPosiciones(torneo3);

tabla3.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo3,
                6,
                5,
                1,
                0,
                15,
                4,
                16
        )
);

tabla3.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo4,
                6,
                4,
                1,
                1,
                12,
                5,
                13
        )
);

tabla3.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo1,
                6,
                3,
                2,
                1,
                10,
                7,
                11
        )
);

tabla3.obtenerPosiciones().add(
        new PosicionEquipo(
                equipo2,
                6,
                1,
                2,
                3,
                6,
                12,
                5
        )
);

tabla3.ordenarTabla();

tablas.add(tabla3);
    }

    public boolean crearTabla(TablaPosiciones tabla) {

        if (tabla == null) {
            return false;
        }

        if (tabla.getTorneo() == null) {
            return false;
        }

        if (existeTabla(tabla.getTorneo())) {
            return false;
        }

        return tablas.add(tabla);
    }

    public TablaPosiciones buscarTablaPorTorneo(
            Torneo torneo) {
        
        if (torneo == null) {
            return null;
        }

        for (TablaPosiciones tabla : tablas) {

            if (tabla.getTorneo()
                    .equals(torneo)) {

                return tabla;
            }
        }

        return null;
    }

    public boolean actualizarTabla(
            TablaPosiciones tabla) {

        if (tabla == null) {
            return false;
        }

        if (tabla.getTorneo() == null) {
            return false;
        }
        
        if (!existeTabla(tabla.getTorneo())) {

        return false;
        }

        for (int i = 0; i < tablas.size(); i++) {

            if (tablas.get(i).getTorneo()
                    .equals(tabla.getTorneo())) {

                tablas.set(i, tabla);
                return true;
            }
        }

        return false;
    }

    public boolean recalcularTabla(
            TablaPosiciones tabla) {

        if (tabla == null) {
            return false;
        }
        
        if (tabla.getTorneo() == null) {
            return false;
        }

        tabla.ordenarTabla();
        return true;
    }

    public TablaPosiciones obtenerTabla(
            Torneo torneo) {
        
        if (torneo == null) {
            return null;
        }

        return buscarTablaPorTorneo(torneo);
    }

    public List<TablaPosiciones> listarTablas() {
        return new ArrayList<>(tablas);
    }

    public boolean existeTabla(Torneo torneo) {
        return buscarTablaPorTorneo(torneo) != null;
    }
}

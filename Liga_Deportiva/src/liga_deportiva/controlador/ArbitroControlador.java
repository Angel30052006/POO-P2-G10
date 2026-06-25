package liga_deportiva.controlador;

import java.util.ArrayList;
import java.util.List;
import liga_deportiva.modelo.Arbitro;

public class ArbitroControlador {

    private List<Arbitro> arbitros;

    public ArbitroControlador() {

        this.arbitros = new ArrayList<>();

        // DATOS PRECARGADOS

        arbitros.add(
                new Arbitro(
                        "A001",
                        "José Pérez",
                        45,
                        10
                )
        );

        arbitros.add(
                new Arbitro(
                        "A002",
                        "Carlos Sánchez",
                        39,
                        8
                )
        );

        arbitros.add(
                new Arbitro(
                        "A003",
                        "Diego Morales",
                        42,
                        12
                )
        );

        arbitros.add(
                new Arbitro(
                        "A004",
                        "Roberto Castillo",
                        37,
                        6
                )
        );
    }

    public boolean registrarArbitro(Arbitro arbitro) {

        if (arbitro == null) {
            return false;
        }

        if (existeArbitro(arbitro.getCodigo())) {
            return false;
        }

        if (arbitro.getEdad() <= 0) {
            return false;
        }

        if (arbitro.getAniosExperiencia() < 0) {
            return false;
        }

        if (arbitro.getAniosExperiencia()
                > arbitro.getEdad()) {
            return false;
        }

        return arbitros.add(arbitro);
    }

    public Arbitro buscarArbitroPorCodigo(String codigo) {
        
        if (codigo == null
        || codigo.trim().isEmpty()) {

        return null;
        }

        for (Arbitro arbitro : arbitros) {

            if (arbitro.getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return arbitro;
            }
        }

        return null;
    }

    public Arbitro buscarArbitroPorNombre(String nombre) {
        
        if (nombre == null
        || nombre.trim().isEmpty()) {

        return null;
        }

        for (Arbitro arbitro : arbitros) {

            if (arbitro.getNombre()
                    .equalsIgnoreCase(nombre)) {

                return arbitro;
            }
        }

        return null;
    }

    public boolean modificarArbitro(
            Arbitro arbitroModificado) {

        if (arbitroModificado == null) {
            return false;
        }

        if (arbitroModificado.getEdad() <= 0) {
            return false;
        }

        if (arbitroModificado.getAniosExperiencia() < 0) {
            return false;
        }

        if (arbitroModificado.getAniosExperiencia()
                > arbitroModificado.getEdad()) {

            return false;
        }
        
        if (arbitroModificado.getCodigo() == null || arbitroModificado.getCodigo() .trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < arbitros.size(); i++) {

            if (arbitros.get(i).getCodigo()
                    .equalsIgnoreCase(
                            arbitroModificado.getCodigo())) {

                arbitros.set(i, arbitroModificado);
                return true;
            }
        }

        return false;
    }

    public List<Arbitro> listarArbitros() {
        return new ArrayList<>(arbitros);
    }

    public boolean existeArbitro(String codigo) {
        return buscarArbitroPorCodigo(codigo) != null;
    }

    public Arbitro obtenerArbitro(String codigo) {
        return buscarArbitroPorCodigo(codigo);
    }
}

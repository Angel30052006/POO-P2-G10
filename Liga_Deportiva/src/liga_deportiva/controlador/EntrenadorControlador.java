package liga_deportiva.controlador;


import java.util.ArrayList;
import java.util.List;
import liga_deportiva.modelo.Entrenador;

public class EntrenadorControlador {

    private List<Entrenador> entrenadores;

    public EntrenadorControlador() {

        this.entrenadores = new ArrayList<>();

        // DATOS PRECARGADOS

        entrenadores.add(
                new Entrenador(
                        "E001",
                        "Fabián Bustos",
                        55,
                        "Argentina",
                        20
                )
        );

        entrenadores.add(
                new Entrenador(
                        "E002",
                        "Luis Zubeldía",
                        44,
                        "Argentina",
                        15
                )
        );

        entrenadores.add(
                new Entrenador(
                        "E003",
                        "Javier Rabanal",
                        49,
                        "España",
                        18
                )
        );

        entrenadores.add(
                new Entrenador(
                        "E004",
                        "César Farías",
                        52,
                        "Venezuela",
                        22
                )
        );
    }

    public boolean registrarEntrenador(Entrenador entrenador) {

        if (entrenador == null) {
            return false;
        }

        if (existeEntrenador(entrenador.getCodigo())) {
            return false;
        }

        if (entrenador.getEdad() <= 0) {
            return false;
        }

        if (entrenador.getNacionalidad() == null
                || entrenador.getNacionalidad().trim().isEmpty()) {
            return false;
        }

        if (entrenador.getAniosExperiencia() < 0) {
            return false;
        }

        if (entrenador.getAniosExperiencia()
                > entrenador.getEdad()) {
            return false;
        }

        return entrenadores.add(entrenador);
    }

    public Entrenador buscarEntrenadorPorCodigo(String codigo) {

        if (codigo == null
        || codigo.trim().isEmpty()) {

        return null;
        }
        
        for (Entrenador entrenador : entrenadores) {

            if (entrenador.getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return entrenador;
            }
        }

        return null;
    }

    public Entrenador buscarEntrenadorPorNombre(String nombre) {

        
        if (nombre == null
        || nombre.trim().isEmpty()) {

        return null;
        }
        
        for (Entrenador entrenador : entrenadores) {

            if (entrenador.getNombre()
                    .equalsIgnoreCase(nombre)) {

                return entrenador;
            }
        }

        return null;
    }

    public boolean modificarEntrenador(
            Entrenador entrenadorModificado) {

        if (entrenadorModificado == null) {
            return false;
        }
        
        if (entrenadorModificado.getCodigo() == null
        || entrenadorModificado.getCodigo()
                .trim().isEmpty()) {

        return false;
        }

        if (entrenadorModificado.getEdad() <= 0) {
            return false;
        }

        if (entrenadorModificado.getNacionalidad() == null
                || entrenadorModificado.getNacionalidad()
                .trim().isEmpty()) {
            return false;
        }

        if (entrenadorModificado.getAniosExperiencia() < 0) {
            return false;
        }

        if (entrenadorModificado.getAniosExperiencia()
                > entrenadorModificado.getEdad()) {
            return false;
        }

        for (int i = 0; i < entrenadores.size(); i++) {

            if (entrenadores.get(i).getCodigo()
                    .equalsIgnoreCase(
                            entrenadorModificado.getCodigo())) {

                entrenadores.set(i, entrenadorModificado);
                return true;
            }
        }

        return false;
    }

    public List<Entrenador> listarEntrenadores() {
        return new ArrayList<>(entrenadores);
    }

    public boolean existeEntrenador(String codigo) {
        return buscarEntrenadorPorCodigo(codigo) != null;
    }

    public Entrenador obtenerEntrenador(String codigo) {
        return buscarEntrenadorPorCodigo(codigo);
    }
}
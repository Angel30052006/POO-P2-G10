package liga_deportiva.modelo;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import liga_deportiva.modelo.Entrenador;

public class Equipo implements Estadistico {

    private String codigo;
    private String nombre;
    private String ciudadOrigen;
    private Entrenador entrenador;
    private List<Jugador> jugadores;

    public Equipo(String codigo, String nombre, String ciudadOrigen) {
        
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El código es obligatorio.");
        }
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre es obligatorio.");
        }
        
        if (ciudadOrigen == null || ciudadOrigen.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La ciudad es obligatoria.");
        }
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudadOrigen = ciudadOrigen;
        this.jugadores = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El código es obligatorio.");
        }

        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

    if (nombre == null
            || nombre.trim().isEmpty()) {

        throw new IllegalArgumentException(
                "El nombre es obligatorio.");
    }

    this.nombre = nombre;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(
        String ciudadOrigen) {

    if (ciudadOrigen == null
            || ciudadOrigen.trim().isEmpty()) {

        throw new IllegalArgumentException(
                "La ciudad es obligatoria.");
    }

    this.ciudadOrigen = ciudadOrigen;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public int obtenerCantidadJugadores() {
        return jugadores.size();
    }

    public boolean tieneEntrenador() {
        return entrenador != null;
    }

    public List<Jugador> obtenerJugadoresDisponibles() {
        return new ArrayList<>(jugadores);
    }

    @Override
    public String obtenerResumenEstadistico() {
        return "Equipo: " + nombre;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ciudadOrigen='" + ciudadOrigen + '\'' +
                ", entrenador=" +
                (entrenador != null ? entrenador.getNombre() : "Sin entrenador") +
                ", cantidadJugadores=" + jugadores.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Equipo)) {
            return false;
        }

        Equipo otro = (Equipo) obj;

        return Objects.equals(codigo, otro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

}



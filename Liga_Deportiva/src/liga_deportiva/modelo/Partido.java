package liga_deportiva.modelo;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Partido {

    private String codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoPartido estado;
    private Torneo torneo;
    private Estadio estadio;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Arbitro arbitro;

    public Partido(String codigo,
                   LocalDate fecha,
                   LocalTime hora,
                   EstadoPartido estado,
                   Torneo torneo,
                   Estadio estadio,
                   Equipo equipoLocal,
                   Equipo equipoVisitante,
                   Arbitro arbitro) {
        
        
        if (codigo == null || codigo.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "El código es obligatorio.");
        }

        if (fecha == null) {
            throw new IllegalArgumentException(
            "La fecha es obligatoria.");
        }

        if (hora == null) {
        throw new IllegalArgumentException(
            "La hora es obligatoria.");
        }   

        if (estado == null) {
            throw new IllegalArgumentException(
            "El estado es obligatorio.");
        }

        if (torneo == null) {
        throw new IllegalArgumentException(
            "El torneo es obligatorio.");
        }

        if (estadio == null) {
        throw new IllegalArgumentException(
            "El estadio es obligatorio.");
        }

        if (equipoLocal == null) {
        throw new IllegalArgumentException(
            "El equipo local es obligatorio.");
        }

        if (equipoVisitante == null) {
        throw new IllegalArgumentException(
            "El equipo visitante es obligatorio.");
        }

        if (arbitro == null) {
         throw new IllegalArgumentException(
            "El árbitro es obligatorio.");
        }
        
        if (equipoLocal.equals(equipoVisitante)) {

        throw new IllegalArgumentException(
            "Un equipo no puede jugar contra sí mismo.");
        }

        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.torneo = torneo;
        this.estadio = estadio;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.arbitro = arbitro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {

    if (codigo == null
            || codigo.trim().isEmpty()) {

        throw new IllegalArgumentException(
                "El código es obligatorio.");
    }

    this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public EstadoPartido getEstado() {
        return estado;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void cambiarFecha(LocalDate fecha) {

        if (fecha == null) {

        throw new IllegalArgumentException(
                "La fecha es obligatoria.");
    }

    this.fecha = fecha;
    }

    public void cambiarHora(LocalTime hora) {

    if (hora == null) {

        throw new IllegalArgumentException(
                "La hora es obligatoria.");
    }

    this.hora = hora;
    }

    public void cambiarEstadio(Estadio estadio) {

    if (estadio == null) {

        throw new IllegalArgumentException(
                "El estadio es obligatorio.");
    }

    this.estadio = estadio;
    }

    public void cambiarArbitro(Arbitro arbitro) {

    if (arbitro == null) {

        throw new IllegalArgumentException(
                "El árbitro es obligatorio.");
    }

    this.arbitro = arbitro;
    }

    public void cambiarEstado(
        EstadoPartido estado) {

    if (estado == null) {

        throw new IllegalArgumentException(
                "El estado es obligatorio.");
    }

    this.estado = estado;
    }

    public boolean estaFinalizado() {
        return estado == EstadoPartido.FINALIZADO;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", estado=" + estado +
                ", torneo=" +
                (torneo != null ? torneo.getNombre() : "Sin torneo") +
                ", estadio=" +
                (estadio != null ? estadio.getNombre() : "Sin estadio") +
                ", equipoLocal=" +
                (equipoLocal != null ? equipoLocal.getNombre() : "Sin equipo") +
                ", equipoVisitante=" +
                (equipoVisitante != null ? equipoVisitante.getNombre() : "Sin equipo") +
                ", arbitro=" +
                (arbitro != null ? arbitro.getNombre() : "Sin árbitro") +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Partido)) {
            return false;
        }

        Partido otro = (Partido) obj;

        return Objects.equals(codigo, otro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}

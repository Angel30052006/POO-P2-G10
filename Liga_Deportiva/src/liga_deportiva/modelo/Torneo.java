package liga_deportiva.modelo;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Torneo {

    private String codigo;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoTorneo estado;
    private List<Equipo> equiposParticipantes;
    private List<Partido> partidos;

    public Torneo(String codigo, String nombre, LocalDate fechaInicio,
                  LocalDate fechaFin, EstadoTorneo estado) {
        
        if (codigo == null
        || codigo.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "El código es obligatorio.");
        }
        
        if (nombre == null
        || nombre.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "El nombre es obligatorio.");
        }
        
        if (fechaInicio == null) {
        throw new IllegalArgumentException(
            "La fecha de inicio es obligatoria.");
        }
        
        if (fechaFin == null) {
        throw new IllegalArgumentException(
            "La fecha de fin es obligatoria.");
        }
        
        if (estado == null) {
        throw new IllegalArgumentException(
            "El estado es obligatorio.");
        }
        
        
        if (fechaFin.isBefore(fechaInicio)) {
        throw new IllegalArgumentException(
            "La fecha fin no puede ser anterior a la fecha inicio.");
        }
         
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.equiposParticipantes = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }


    public String getNombre() {
        return nombre;
    }


    public LocalDate getFechaInicio() {
        return fechaInicio;
    }


    public LocalDate getFechaFin() {
        return fechaFin;
    }


    public EstadoTorneo getEstado() {
        return estado;
    }
    
    public void setCodigo(String codigo) {

    if (codigo == null || codigo.trim().isEmpty()) {
        throw new IllegalArgumentException(
                "El código es obligatorio.");
    }

    this.codigo = codigo;
    }

    public void setNombre(String nombre) {

    if (nombre == null || nombre.trim().isEmpty()) {
        throw new IllegalArgumentException(
                "El nombre es obligatorio.");
    }

    this.nombre = nombre;
    }

    public void setFechaInicio(LocalDate fechaInicio) {

    if (fechaInicio == null) {
        throw new IllegalArgumentException(
                "La fecha de inicio es obligatoria.");
    }

    if (this.fechaFin != null
            && fechaInicio.isAfter(this.fechaFin)) {

        throw new IllegalArgumentException(
                "La fecha de inicio no puede ser posterior a la fecha fin.");
    }

    this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {

    if (fechaFin == null) {
        throw new IllegalArgumentException(
                "La fecha de fin es obligatoria.");
    }

    if (this.fechaInicio != null
            && fechaFin.isBefore(this.fechaInicio)) {

        throw new IllegalArgumentException(
                "La fecha fin no puede ser anterior a la fecha inicio.");
    }

    this.fechaFin = fechaFin;
    }

    public void setEstado(EstadoTorneo estado) {

    if (estado == null) {
        throw new IllegalArgumentException(
                "El estado es obligatorio.");
    }

    this.estado = estado;
    }


    public List<Equipo> getEquiposParticipantes() {
        return equiposParticipantes;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public int obtenerCantidadEquipos() {
        return equiposParticipantes.size();
    }

    public void finalizarTorneo() {
        this.estado = EstadoTorneo.FINALIZADO;
    }

    public boolean estaActivo() {
        return estado == EstadoTorneo.ACTIVO;
    }

    public boolean validarFechas() {
        return fechaInicio != null
                && fechaFin != null
                && !fechaFin.isBefore(fechaInicio);
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado=" + estado +
                ", cantidadEquipos=" + obtenerCantidadEquipos() +
                ", cantidadPartidos=" + partidos.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Torneo)) {
            return false;
        }

        Torneo otro = (Torneo) obj;
        return Objects.equals(codigo, otro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}


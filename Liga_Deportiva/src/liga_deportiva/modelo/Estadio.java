package liga_deportiva.modelo;

import java.util.Objects;

public class Estadio {

    private String codigo;
    private String nombre;
    private int capacidad;

    public Estadio(String codigo, String nombre, int capacidad) {
        
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
    
    if (capacidad <= 0) {

        throw new IllegalArgumentException(
                "La capacidad debe ser mayor que cero.");
        }
        
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {

    if (capacidad <= 0) {

        throw new IllegalArgumentException(
                "La capacidad debe ser mayor que cero.");
    }

    this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Estadio)) {
            return false;
        }

        Estadio otro = (Estadio) obj;

        return Objects.equals(codigo, otro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}


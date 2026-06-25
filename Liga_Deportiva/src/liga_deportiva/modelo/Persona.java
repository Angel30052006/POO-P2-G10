package liga_deportiva.modelo;

import java.util.Objects;

public abstract class Persona {

    private String codigo;
    private String nombre;
    private int edad;

    public Persona(String codigo, String nombre, int edad) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El código es obligatorio.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre es obligatorio.");
        }

        if (edad <= 0) {
            throw new IllegalArgumentException(
                "La edad debe ser mayor que cero.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
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

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre es obligatorio.");
        }

        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {

        if (edad <= 0) {
            throw new IllegalArgumentException(
                "La edad debe ser mayor que cero.");
        }

        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Persona persona = (Persona) obj;
        return Objects.equals(codigo, persona.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
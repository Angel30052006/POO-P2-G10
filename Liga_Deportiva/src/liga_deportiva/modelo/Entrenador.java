
package liga_deportiva.modelo;

import java.util.Objects;

public class Entrenador extends Persona {

    private String nacionalidad;
    private int aniosExperiencia;

    public Entrenador(String codigo,
                      String nombre,
                      int edad,
                      String nacionalidad,
                      int aniosExperiencia) {

        super(codigo, nombre, edad);
        
        if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La nacionalidad es obligatoria.");
        }
        
        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException(
                "Los años de experiencia no pueden ser negativos.");
        }
        
        if (aniosExperiencia > edad) {
            throw new IllegalArgumentException(
                "La experiencia no puede superar la edad.");
        }
        
        
        this.nacionalidad = nacionalidad;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {

        if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La nacionalidad es obligatoria.");
        }

        this.nacionalidad = nacionalidad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {

        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException(
                "Los años de experiencia no pueden ser negativos.");
        }

        if (aniosExperiencia > getEdad()) {
            throw new IllegalArgumentException(
                "La experiencia no puede superar la edad.");
        }

        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "codigo='" + getCodigo() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", aniosExperiencia=" + aniosExperiencia +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Entrenador)) {
            return false;
        }

        Entrenador otro = (Entrenador) obj;

        return Objects.equals(getCodigo(), otro.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}


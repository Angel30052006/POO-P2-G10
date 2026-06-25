package liga_deportiva.modelo;

import java.util.Objects;

public class Arbitro extends Persona {

    private int aniosExperiencia;

    public Arbitro(String codigo,
                   String nombre,
                   int edad,
                   int aniosExperiencia) {

        super(codigo, nombre, edad);
        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException(
                "Los años de experiencia no pueden ser negativos.");
        }
        
        if (aniosExperiencia > edad) {
            throw new IllegalArgumentException(
                "La experiencia no puede superar la edad.");
        }
        
        this.aniosExperiencia = aniosExperiencia;
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
        return "Arbitro{" +
                "codigo='" + getCodigo() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                ", aniosExperiencia=" + aniosExperiencia +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Arbitro)) {
            return false;
        }

        Arbitro otro = (Arbitro) obj;

        return Objects.equals(getCodigo(), otro.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}

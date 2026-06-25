package liga_deportiva.modelo;


import java.util.Objects;

public class Jugador extends Persona implements Estadistico {

    private int numeroCamiseta;
    private String posicion;
    private boolean autorizacionEspecial;

    public Jugador(String codigo,
                   String nombre,
                   int edad,
                   int numeroCamiseta,
                   String posicion,
                   boolean autorizacionEspecial) {

        super(codigo, nombre, edad);
        
        if (numeroCamiseta <= 0) {
            throw new IllegalArgumentException(
                "El número de camiseta debe ser mayor que cero.");
        }
        
        if (posicion == null || posicion.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La posición es obligatoria.");
        }
        
        
        this.numeroCamiseta = numeroCamiseta;
        this.posicion = posicion;
        this.autorizacionEspecial = autorizacionEspecial;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(int numeroCamiseta) {

        if (numeroCamiseta <= 0) {
            throw new IllegalArgumentException(
                "El número de camiseta debe ser mayor que cero.");
        }

        this.numeroCamiseta = numeroCamiseta;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {

        if (posicion == null  || posicion.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La posición es obligatoria.");
        }

        this.posicion = posicion;
    }

    public boolean isAutorizacionEspecial() {
        return autorizacionEspecial;
    }

    public void setAutorizacionEspecial(boolean autorizacionEspecial) {
        this.autorizacionEspecial = autorizacionEspecial;
    }

    public boolean esMenorConAutorizacion() {
        return getEdad() < 18 && autorizacionEspecial;
    }

    @Override
    public String obtenerResumenEstadistico() {
        return "Jugador: " + getNombre();
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "codigo='" + getCodigo() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                ", numeroCamiseta=" + numeroCamiseta +
                ", posicion='" + posicion + '\'' +
                ", autorizacionEspecial=" + autorizacionEspecial +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Jugador)) {
            return false;
        }

        Jugador otro = (Jugador) obj;

        return Objects.equals(getCodigo(), otro.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}


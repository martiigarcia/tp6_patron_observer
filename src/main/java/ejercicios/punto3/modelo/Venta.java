package ejercicios.punto3.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Venta {

    private final double litrosCargados;
    private final Combustible combustible;

    public Venta(String litrosCargados, Combustible combustible) {

        if (esDatoNulo(combustible) || esDatoVacio(combustible))
            throw new RuntimeException("El combustible no se ingreso correctamente.");
        this.combustible = combustible;

        if (esDatoNulo(litrosCargados) || esDatoVacio(litrosCargados))
            throw new RuntimeException("La cantidad de litros cargados no se ingreso correctamente.");

        try {
            this.litrosCargados = Double.parseDouble(litrosCargados);
            if(this.litrosCargados <1)
                throw new RuntimeException("La cantidad de litros debe ser mayor a cero.");
        }catch (NumberFormatException e){
            throw new RuntimeException("Los litros ingresados no estan en un formato valido.", e);
        }
    }
    public Venta(double litrosCargados, Combustible combustible) {

        if (esDatoNulo(combustible) || esDatoVacio(combustible))
            throw new RuntimeException("El combustible no se ingreso correctamente.");
        this.combustible = combustible;

        if (esDatoNulo(litrosCargados) || esDatoVacio(litrosCargados))
            throw new RuntimeException("La cantidad de litros cargados no se ingreso correctamente.");
        if(litrosCargados <1)
            throw new RuntimeException("La cantidad de litros debe ser mayor a cero.");
        this.litrosCargados = litrosCargados;

    }

    private boolean esDatoVacio(Object dato) {
        return dato.equals("");
    }
    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }


    public double litrosCargados(){
        return this.litrosCargados;
    }
    public Combustible combustible(){return this.combustible;}

    protected double calcularMonto(LocalDateTime fecha){
        return this.combustible.obtenerDescuentoPorFechaYLitros(this.litrosCargados, fecha);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Double.compare(venta.litrosCargados, litrosCargados) == 0 && combustible.equals(venta.combustible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(litrosCargados, combustible);
    }




}

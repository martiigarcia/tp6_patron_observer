package ejercicios.punto3.modelo;

import java.time.LocalDateTime;

public class Comun implements Combustible {

    private final double PRECIO = 70;
    private final int DESCUENTO = 5;


    @Override
    public double obtenerPrecio() {
        return PRECIO;
    }

    @Override
    public double obtenerDescuentoPorFechaYLitros(double litro, LocalDateTime fecha) {
        double monto = litro * PRECIO;

        if (compararFechaDescuento(fecha))
            monto = monto - hacerDescuento(monto);

        return monto;
    }

    private double hacerDescuento(double precioSinDescuento) {
        return (DESCUENTO * precioSinDescuento / 100);

    }
    @Override
    public String toString(){
        return "Combustible Comun";
    }

    private boolean compararFechaDescuento(LocalDateTime fecha){
        return (8 <= fecha.getHour() && fecha.getHour() <= 10);
    }

    @Override
    public boolean equals(Object o) {
        return (this == o) || (o != null && getClass() == o.getClass());
    }
}

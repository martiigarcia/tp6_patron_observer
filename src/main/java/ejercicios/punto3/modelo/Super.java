package ejercicios.punto3.modelo;

import java.time.LocalDateTime;

public class Super implements Combustible {

    private final double PRECIO = 90;
    private final int DESCUENTO_SABADO_20_LITROS = 10;
    private final int DESCUENTO_DOMINGO = 12;


    @Override
    public double obtenerPrecio() {
        return PRECIO;
    }

    @Override
    public double obtenerDescuentoPorFechaYLitros(double litro, LocalDateTime fecha) {
        double monto = litro * PRECIO;

        if (compararSegundaFechaDescuentoSabado(litro, fecha)) {
            monto = monto - hacerDescuento(monto, DESCUENTO_SABADO_20_LITROS);
        }
        if (compararPrimerFechaDescuentoDomingo(fecha)) {
            monto = monto - hacerDescuento(monto, DESCUENTO_DOMINGO);
        }

        return monto;
    }

    @Override
    public String toString() {
        return "Combustible Super";
    }

    private double hacerDescuento(double precioSinDescuento, int descuento) {
        return descuento * precioSinDescuento / 100;

    }

    private boolean compararPrimerFechaDescuentoDomingo(LocalDateTime fecha) {
        return (fecha.getDayOfWeek().getValue() == 7);
    }

    private boolean compararSegundaFechaDescuentoSabado(double litro, LocalDateTime fecha) {
        return (fecha.getDayOfWeek().getValue() == 6 && 20 <= litro);
    }

    @Override
    public boolean equals(Object o) {
        return (this == o) || (o != null && getClass() == o.getClass());
    }
}

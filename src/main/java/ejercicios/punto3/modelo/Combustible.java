package ejercicios.punto3.modelo;

import java.time.LocalDateTime;

public interface Combustible {

    double obtenerPrecio ();

    double obtenerDescuentoPorFechaYLitros (double litro, LocalDateTime fechaCarga);

}

package ejercicios.punto3.modelo;

import java.time.LocalDateTime;

public class PagoDeUnaVenta extends Venta {

    private String email;
    private double monto;
    private final LocalDateTime fecha;

    public PagoDeUnaVenta(LocalDateTime fecha, String litrosCargados, Combustible combustible, String email) {
        super(litrosCargados, combustible);
        if (esDatoNulo(fecha) || esDatoVacio(fecha))
            throw new RuntimeException("La fecha no se ingreso correctamente.");
        if (esDatoNulo(email) || esDatoVacio(email))
            throw new RuntimeException("El email no se ingreso correctamente.");
        this.fecha = fecha;
        this.email = email;
    }

    public PagoDeUnaVenta(LocalDateTime fecha, double litrosCargados, Combustible combustible, double montoRecuperado, String email) {
        super(litrosCargados, combustible);
        if (esDatoNulo(fecha) || esDatoVacio(fecha))
            throw new RuntimeException("La fecha no se ingreso correctamente.");
        this.fecha = fecha;

        if (esDatoNulo(montoRecuperado) || esDatoVacio(montoRecuperado))
            throw new RuntimeException("El monto no es valido.");
        this.monto = montoRecuperado;

        if (esDatoNulo(email) || esDatoVacio(email))
            throw new RuntimeException("El email no se ingreso correctamente.");
        this.email = email;
    }

    private boolean esDatoVacio(Object dato) {
        return dato.equals("");
    }

    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }

    public LocalDateTime fecha() {
        return this.fecha;
    }

    public String email() {
        return this.email;
    }

    public double calcularMontoAbonado() {
        return (this.monto = super.calcularMonto(this.fecha));
    }


}

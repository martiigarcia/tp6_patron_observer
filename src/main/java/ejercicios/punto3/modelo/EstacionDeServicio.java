package ejercicios.punto3.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class EstacionDeServicio extends Observable{

    private final RegistroDeVentas registroDeVentas;

    public EstacionDeServicio(RegistroDeVentas registroDeVentas, ObservadorInterface...observadores) {
        super(observadores);
        this.registroDeVentas = registroDeVentas;
    }

    public void agregarVenta(LocalDateTime fecha, String litrosCargados, Combustible combustible, String email) {
        PagoDeUnaVenta pagoDeUnaVenta = new PagoDeUnaVenta(fecha, litrosCargados, combustible, email);
        registroDeVentas.cargarVenta(pagoDeUnaVenta);
        notificarObservadores(pagoDeUnaVenta);
    }

    public double calcularMontoDeLaVenta(LocalDateTime fecha, String litrosCargados, Combustible combustible){
        return new Venta(litrosCargados, combustible).calcularMonto(fecha);
    }

    public List<PagoDeUnaVenta> recuperarListaDeVentasPagadasEntreFechas(String fecha1, String fecha2){
        if(fecha1 == null ||fecha1.isEmpty() || fecha2 == null || fecha2.isEmpty())
            throw new RuntimeException("Las fechas no fueron ingresadas correctamente.");
        return registroDeVentas.obtenerListaVentasPagadasEntreFechas(fecha1, fecha2);
    }

    public List<PagoDeUnaVenta> recuperarListaDeVentas(){
        return registroDeVentas.obtenerListaVentasPagadas();
    }

}

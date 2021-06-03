package ejercicios.punto3.modelo;

import java.util.List;

public interface RegistroDeVentas {

    void cargarVenta (PagoDeUnaVenta pagoDeUnaVenta);

    List<PagoDeUnaVenta> obtenerListaVentasPagadas();

    List<PagoDeUnaVenta> obtenerListaVentasPagadasEntreFechas(String fecha1, String fecha2);

}

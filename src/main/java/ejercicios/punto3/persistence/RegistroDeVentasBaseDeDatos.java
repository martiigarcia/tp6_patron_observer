package ejercicios.punto3.persistence;

import ejercicios.punto3.modelo.*;
import ejercicios.punto3.persistence.jdbc.JDBCVenta;

import java.util.List;

public class RegistroDeVentasBaseDeDatos implements RegistroDeVentas {

    JDBCVenta ventaJDBC = new JDBCVenta();

    @Override
    public void cargarVenta(PagoDeUnaVenta venta) {
        ventaJDBC.cargarVenta(venta);
    }

    @Override
    public List<PagoDeUnaVenta> obtenerListaVentasPagadas() {
        return ventaJDBC.recuperarListaVentas();
    }

    @Override
    public List<PagoDeUnaVenta> obtenerListaVentasPagadasEntreFechas(String fecha1, String fecha2) {
        return ventaJDBC.recuperarListaVentasEntreFechas(fecha1, fecha2);
    }


}

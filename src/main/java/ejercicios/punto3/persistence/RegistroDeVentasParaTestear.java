package ejercicios.punto3.persistence;

import ejercicios.punto3.modelo.PagoDeUnaVenta;
import ejercicios.punto3.modelo.RegistroDeVentas;
import ejercicios.punto3.modelo.Venta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistroDeVentasParaTestear implements RegistroDeVentas {

    private final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final List<PagoDeUnaVenta> listaVentas = new ArrayList<>();

    @Override
    public void cargarVenta(PagoDeUnaVenta venta) {
        listaVentas.add(venta);
    }

    @Override
    public List<PagoDeUnaVenta> obtenerListaVentasPagadas() {
        return listaVentas;
    }

    @Override
    public List<PagoDeUnaVenta> obtenerListaVentasPagadasEntreFechas(String fecha1, String fecha2) {

        List<PagoDeUnaVenta> filtro = new ArrayList<>();

        LocalDate f1 = LocalDate.parse(fecha1, FORMATO);
        LocalDate f2 = LocalDate.parse(fecha2, FORMATO);

        for (PagoDeUnaVenta ve : listaVentas) {
            if (compararFechaValida(LocalDate.from(ve.fecha()), f1, f2)) ;
            filtro.add(ve);
        }

        return filtro;
    }

    public boolean seRegistroLaVenta(Venta venta) {
        return (listaVentas.contains(venta));
    }

    private boolean compararFechaValida(LocalDate fechaActual, LocalDate fecha1, LocalDate fecha2) {
        return ((
                fechaActual.isAfter(fecha1) && fechaActual.isBefore(fecha2))
                || fechaActual.equals(fecha1) || fechaActual.equals(fecha2));
    }

}

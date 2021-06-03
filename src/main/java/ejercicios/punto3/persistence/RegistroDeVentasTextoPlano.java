package ejercicios.punto3.persistence;

import ejercicios.punto3.modelo.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistroDeVentasTextoPlano implements RegistroDeVentas {

    private final File file;
    private final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public RegistroDeVentasTextoPlano(String path) {
        this.file = new File(path);
    }

    @Override
    public void cargarVenta(PagoDeUnaVenta pagoDeUnaVenta) {
        try {

            Writer writer = new FileWriter(file, true);
            writer.write(pagoDeUnaVenta.fecha().format(FORMATO) + ", "
                    + pagoDeUnaVenta.litrosCargados() + ", "
                    + (pagoDeUnaVenta.combustible() instanceof Comun ? 0 : 1) + ", "
                    + pagoDeUnaVenta.calcularMontoAbonado()+ ", "
                    + pagoDeUnaVenta.email() + "\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("No pudo registrarse la venta.", e);
        }

    }

    @Override
    public List<PagoDeUnaVenta> obtenerListaVentasPagadas() {

        List<PagoDeUnaVenta> ventas = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (String venta : reader.lines().collect(Collectors.toList())) {
                String[] partesDeLasVentas = venta.split(", ");
                PagoDeUnaVenta pagoDeUnaVenta = new PagoDeUnaVenta(
                        LocalDateTime.parse(partesDeLasVentas[0], FORMATO),
                            Double.parseDouble(partesDeLasVentas[1]),
                                Integer.parseInt(partesDeLasVentas[2]) == 0 ? new Comun() : new Super(),
                                    Double.parseDouble(partesDeLasVentas[3]),
                                            partesDeLasVentas[4]
                );
                ventas.add(pagoDeUnaVenta);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error.", e);
        }
        return ventas;
    }

    @Override
    public List<PagoDeUnaVenta> obtenerListaVentasPagadasEntreFechas(String fecha1, String fecha2) {
        List<PagoDeUnaVenta> ventas = new ArrayList<>();

        try {
            LocalDate f1 = LocalDate.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate f2 = LocalDate.parse(fecha2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (String venta : reader.lines().collect(Collectors.toList())) {
                String[] partesDeLasVentas = venta.split(", ");
                PagoDeUnaVenta pagoDeUnaVenta = new PagoDeUnaVenta(
                        LocalDateTime.parse(partesDeLasVentas[0], FORMATO),
                        Double.parseDouble(partesDeLasVentas[1]),
                        Integer.parseInt(partesDeLasVentas[2]) == 0 ? new Comun() : new Super(),
                        Double.parseDouble(partesDeLasVentas[3]),
                        partesDeLasVentas[4]
                );


                if (compararFechaValida(LocalDate.from(pagoDeUnaVenta.fecha()), f1, f2))
                    ventas.add(pagoDeUnaVenta);


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error.", e);
        }catch (DateTimeParseException e){
            throw new RuntimeException("Las fechas no estan en un formato valido.", e);
        }
        return ventas;
    }

    private boolean compararFechaValida(LocalDate fechaActual, LocalDate fecha1, LocalDate fecha2) {
        if(fecha2.isBefore(fecha1))
            throw new RuntimeException("La fecha limite no puede ser previa a la fecha inicial.");
        if (fechaActual == null)
            throw new RuntimeException("La fecha de la venta registrada no puede ser nula.");
        return ((
                fechaActual.isAfter(fecha1) && fechaActual.isBefore(fecha2))
                || fechaActual.equals(fecha1) || fechaActual.equals(fecha2));
    }


}

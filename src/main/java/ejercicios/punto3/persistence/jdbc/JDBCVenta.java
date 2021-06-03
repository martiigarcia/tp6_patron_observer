package ejercicios.punto3.persistence.jdbc;

import ejercicios.punto3.modelo.Comun;
import ejercicios.punto3.modelo.PagoDeUnaVenta;
import ejercicios.punto3.modelo.Super;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class JDBCVenta {

    public void cargarVenta(PagoDeUnaVenta venta) {
        JDBCConexion conexion = new JDBCConexion();

        try {

            PreparedStatement consulta =
                    conexion.conexion().prepareStatement(
                            "INSERT INTO registro_de_ventas " +
                                    "(fecha_de_carga, litros_cargados, monto_abonado, tipo_combustible, email_cliente) " +
                                    "VALUES (?, ?, ?, ?, ?)");

            consulta.setTimestamp(1, Timestamp.valueOf(venta.fecha()));
            consulta.setDouble(2, venta.litrosCargados());
            consulta.setDouble(3, venta.calcularMontoAbonado());
            consulta.setInt(4, venta.combustible() instanceof Comun ? 0 : 1);
            consulta.setString(5, venta.email());


            consulta.executeUpdate();
            consulta.close();
            conexion.conexion().close();

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo registrar la venta.", e);
        }

    }

    public List<PagoDeUnaVenta> recuperarListaVentas() {
        JDBCConexion conexion = new JDBCConexion();
        List<PagoDeUnaVenta> ventas = new ArrayList<>();
        try {
            PreparedStatement consulta =
                    conexion.conexion().prepareStatement(
                            "SELECT * FROM `registro_de_ventas`");

            ResultSet result = consulta.executeQuery();

            while (result.next()) {

                Timestamp time = result.getTimestamp("fecha_de_carga");
                LocalDateTime tiempo = time == null ? null : time.toLocalDateTime();

                ventas.add(new PagoDeUnaVenta(tiempo,
                        result.getDouble("litros_cargados"),
                        result.getInt("tipo_combustible") == 0 ? new Comun() : new Super(),
                        result.getDouble("monto_abonado"),
                        result.getString("email_cliente")));

            }
            result.close();
            consulta.close();
            conexion.conexion().close();

            return ventas;

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo recuperar la lista de ventas.", e);
        }
    }

    public List<PagoDeUnaVenta> recuperarListaVentasEntreFechas(String fecha1, String fecha2) {
        JDBCConexion conexion = new JDBCConexion();
        List<PagoDeUnaVenta> ventas = new ArrayList<>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            PreparedStatement consulta =
                    conexion.conexion().prepareStatement(
                            "SELECT * FROM `registro_de_ventas`");

            ResultSet result = consulta.executeQuery();

            while (result.next()) {

                Timestamp time = result.getTimestamp("fecha_de_carga");
                LocalDateTime fechaActual = time == null ? null : time.toLocalDateTime();

                LocalDate f1 = LocalDate.parse(fecha1, formato);
                LocalDate f2 = LocalDate.parse(fecha2, formato);


                if (compararFechaValida(LocalDate.from(fechaActual), f1, f2))

                    ventas.add(new PagoDeUnaVenta(fechaActual,
                            result.getDouble("litros_cargados"),
                                    result.getInt("tipo_combustible") == 0 ? new Comun() : new Super(),
                                    result.getDouble("monto_abonado"),
                                    result.getString("email_cliente"))

                            );


            }
            result.close();
            consulta.close();
            conexion.conexion().close();

            return ventas;

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo recuperar la lista de ventas.", e);
        }catch (DateTimeParseException e){
            throw new RuntimeException("Las fechas no estan en un formato valido.", e);
        }
    }

    private boolean compararFechaValida(LocalDate fechaActual, LocalDate fecha1, LocalDate fecha2){
        if(fecha2.isBefore(fecha1))
            throw new RuntimeException("La fecha limite no puede ser previa a la fecha inicial.");
        if (fechaActual == null)
            throw new RuntimeException("La fecha de la venta registrada no puede ser nula.");
        return (fechaActual.isAfter(fecha1) && fechaActual.isBefore(fecha2)
                || fechaActual.equals(fecha1) || fechaActual.equals(fecha2));
    }


}

package ejercicios.punto3.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConexion {

    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bd_parcial_garcia";

        return DriverManager.getConnection(url, "root", "");
    }

}

package ejercicios.punto3.main;

import ejercicios.punto3.graficos.PantallaPrincipal;
import ejercicios.punto3.modelo.EstacionDeServicio;
import ejercicios.punto3.modelo.ObservadorEmail;
import ejercicios.punto3.modelo.RegistroDeVentas;
import ejercicios.punto3.persistence.*;
import ejercicios.punto3.persistence.correos.ServicioEmail;

public class PrimerMain {

    public static void main(String[] args) {

        RegistroDeVentas persistencia = new RegistroDeVentasBaseDeDatos();
        EstacionDeServicio estacionDeServicio = new EstacionDeServicio(persistencia, new ObservadorEmail(new ServicioEmail()));
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(estacionDeServicio);
        pantallaPrincipal.setVisible(true);
    }


}

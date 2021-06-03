package ejercicios.punto3.modelo;

import java.util.Arrays;
import java.util.List;

public class Observable {

    private List<ObservadorInterface> observadorInterface;

    public Observable(ObservadorInterface ... observadores) {
        this.observadorInterface = Arrays.asList(observadores);
    }

    protected void notificarObservadores(PagoDeUnaVenta pagoDeUnaVenta){
        for (ObservadorInterface anInterface : observadorInterface) {
            anInterface.notificar(pagoDeUnaVenta);
        }
    }

}

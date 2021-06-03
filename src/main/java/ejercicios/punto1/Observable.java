package ejercicios.punto1;

import java.util.Arrays;
import java.util.List;

public class Observable {

    private List<ObservadorInterface> observadorInterface;

    public Observable(ObservadorInterface ... observadores) {
        this.observadorInterface = Arrays.asList(observadores);
    }
    protected void notificarObservadores(String temperatura){
        for (ObservadorInterface anInterface : observadorInterface) {
            anInterface.notificar(temperatura);
        }
    }
}

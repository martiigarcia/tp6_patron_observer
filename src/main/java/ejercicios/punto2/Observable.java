package ejercicios.punto2;

import java.util.Arrays;
import java.util.List;

public class Observable implements InterfazMedidor{

    private List<ObservadorInterface> observadorInterface;
    private InterfazMedidor interfazMedidor;

    public Observable(InterfazMedidor medidor, ObservadorInterface... observadores) {
        this.interfazMedidor = medidor;
        this.observadorInterface = Arrays.asList(observadores);
    }

    private void notificarObservadores(String temperatura){
        for (ObservadorInterface anInterface : observadorInterface) {
            anInterface.notificar(temperatura);
        }
    }
    @Override
    public String leerTemperatura(){
        String string = interfazMedidor.leerTemperatura();
        notificarObservadores(string);
        return string;
    }
}

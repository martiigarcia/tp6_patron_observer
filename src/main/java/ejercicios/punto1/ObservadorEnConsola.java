package ejercicios.punto1;

import java.lang.NumberFormatException;

public class ObservadorEnConsola implements ObservadorInterface {

    @Override
    public void notificar(String temperatura) {
        try {
            temperatura = temperatura.replace(" c", "");
            int temperaturaP = Integer.parseInt(temperatura);

            if (temperaturaP < 12)
                System.out.println("Hace frio, se encenderá la caldera");
            if (temperaturaP > 17)
                System.out.println("Hace calor, se encenderá el aire acondicionado");

        } catch (NumberFormatException e) {
            throw new RuntimeException("Formato de temperatura no valido.", e);
        }
    }
}

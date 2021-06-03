package ejercicios.punto1;

public class Main {

    public static void main(String[] args) {
        Medidor medidor = new Medidor(new WeatherChannelService(),
                new ObservadorArchivoTexto("archivo.txt"),
                new ObservadorEnConsola());
        medidor.leerTemperatura();
    }
}

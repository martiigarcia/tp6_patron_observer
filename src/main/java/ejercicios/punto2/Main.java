package ejercicios.punto2;

public class Main {

    public static void main(String[] args) {

        Observable observable = new Observable(
                new Medidor(new WeatherChannelService()),
                new ObservadorArchivoTexto("archivo.txt"),
                new ObservadorEnConsola());

        observable.leerTemperatura();

    }
}

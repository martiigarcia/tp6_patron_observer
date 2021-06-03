package ejercicios.punto1;

public class Medidor extends Observable {
    private String temperatura;
    private ClimaOnline clima;

    public Medidor(ClimaOnline clima, ObservadorInterface ... observadores) {
        super(observadores);
        this.clima = clima;
    }

    public String leerTemperatura() {
            //leo la temperatura del servicio web
        this.temperatura = this.clima.temperatura();
        super.notificarObservadores(temperatura);
        return this.temperatura;
    }

}

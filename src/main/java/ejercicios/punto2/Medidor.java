package ejercicios.punto2;

public class Medidor implements InterfazMedidor {
    private String temperatura;
    private ClimaOnline clima;

    public Medidor(ClimaOnline clima) {
        this.clima = clima;
    }

    public String leerTemperatura() {
        this.temperatura = this.clima.temperatura();
        return this.temperatura;
    }

}

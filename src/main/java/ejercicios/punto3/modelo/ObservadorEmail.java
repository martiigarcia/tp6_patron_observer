package ejercicios.punto3.modelo;

import ejercicios.punto3.persistence.correos.ServicioEmail;

public class ObservadorEmail implements ObservadorInterface{

    private  ServicioEmailInterface servicioEmailInterface;

    public ObservadorEmail(ServicioEmailInterface servicioEmailInterface){
        this.servicioEmailInterface = servicioEmailInterface;
    }

    @Override
    public void notificar(PagoDeUnaVenta pagoDeUnaVenta) {
        this.servicioEmailInterface.enviarMensaje(pagoDeUnaVenta);
    }
}

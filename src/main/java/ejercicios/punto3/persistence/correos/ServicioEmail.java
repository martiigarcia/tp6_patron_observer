package ejercicios.punto3.persistence.correos;

import ejercicios.punto3.modelo.PagoDeUnaVenta;
import ejercicios.punto3.modelo.ServicioEmailInterface;

import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServicioEmail implements ServicioEmailInterface {

    private String para, titulo, cuerpo;
    private final String de = "YPF@example.com";
    private final String USERNAME = "8d3f825fd8d1b6";
    private final String PASSWORD = "4298670297fb84";
    private final String HOST = "smtp.mailtrap.io";
    private Properties props = new Properties();;


    public ServicioEmail() {

            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
            props.put("mail.smtp.host", HOST);
            props.put("mail.smtp.port", "2525");

    }

    public void enviarMensaje(PagoDeUnaVenta pagoDeUnaVenta){
        // Get the Session object.

        this.para = pagoDeUnaVenta.email();
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(de));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));
            message.setSubject("La operacion de carga de cumbustible se realizo exitosamente.");
            message
                    .setText("Usted ha cargado "+ pagoDeUnaVenta.litrosCargados() +
                            " litros de " + pagoDeUnaVenta.combustible().toString() +
                            ", el dia: "+ pagoDeUnaVenta.fecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm")) +
                            ", por el monto de: "+ pagoDeUnaVenta.calcularMontoAbonado() + "$");
            // Send message
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }




}

package ejercicios.punto3;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {
    /*public static void main(String[] args) {
        // remitente
        //String to = "test@example.com";
        // destinatario
        //String from = "from@example.com";

        /*
        Username: 8d3f825fd8d1b6
        Password: 4298670297fb84
        * *

        //usuario y clave que se obtiene desde Mailtrap
        final String username = "8d3f825fd8d1b6";
        final String password = "4298670297fb84";
        String host = "smtp.mailtrap.io";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "2525");
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Titulo del email...");
            message
                    .setText("Cuerpo del email...");
            // Send message
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }*/

    private static final String para = "martina@gmail.com";
    private static final String de = "YPF@example.com";
    private static final String TITULO = "¡Carga de nafta realizada exitosamente!";
    private static final String CUERPO =
            "Su compra de nafta se realizo correctamente.\n Gracias por elegirnos!";
    private static final String username = "8d3f825fd8d1b6";
    private static final String password = "4298670297fb84";
    private static final String host = "smtp.mailtrap.io";
    private static final Properties props = new Properties();

    public static void main(String[] args) {

       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
       props.put("mail.smtp.host", host);
       props.put("mail.smtp.port", "2525");


            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(de));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(para));
                message.setSubject(TITULO);
                message
                        .setText(CUERPO);
                // Send message
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }



}
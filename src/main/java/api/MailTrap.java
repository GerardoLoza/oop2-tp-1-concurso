package api;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailTrap implements NotificacionService {
    private final String USERNAME = "4d4ad4081c22c6";
    private final String PASSWORD = "1602d032d7d98b";
    private static final String HOST = "sandbox.smtp.mailtrap.io";
    private static final int PORT = 2525;
    private static final String FROM_EMAIL = "lozagerardo1@gmail.com";

    private final Session session;

    public MailTrap() {
        this.session = createSession();
    }

    @Override
    public void enviarNotificacion(String mensaje, String destinatario) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Inscripción a concurso");
            message.setText(mensaje);

            Transport.send(message);

            System.out.println("¡Correo enviado con éxito!");
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo" + e);
        }
    }

    private Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }
}

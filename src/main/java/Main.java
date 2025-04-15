import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
//        Almacenamamiento almacenamamientoEnDisco = new PersistenciaDisco("C:/Users/lozag/OneDrive/Documentos/test/rutaDisco.txt");
//        LocalDate fechaInicio = LocalDate.of(2025, 1, 1);
//        LocalDate fechaFin = LocalDate.of(2025, 2, 1);
//        LocalDate fechaInscripcion = LocalDate.of(2025, 1, 13);
//        Concurso concurso = new Concurso("10", "Bingo", fechaInicio, fechaFin, almacenamamientoEnDisco);
//        var participante = new Participante("1", "Juana Perez", "12345678");
//        concurso.nuevaInscripcion(new Inscripcion(concurso, participante, fechaInscripcion));
//
//        Almacenamamiento almacenamamientoEnBD = new PersistenciaBD();
//        Concurso concurso1 = new Concurso("2", "Tejo", fechaInicio, fechaFin, almacenamamientoEnBD);
//        var participante1 = new Participante("20", "Leo Messi", "12345678");
//        concurso1.nuevaInscripcion(new Inscripcion(concurso1, participante1, fechaInscripcion));

        // Datos privados de la clase que va a implementar la interfaz
        final String username = "4d4ad4081c22c6"; // Obtenido de tu inbox
        final String password = "1602d032d7d98b"; // Obtenido de tu inbox

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");

        // Crear una sesión con autenticación
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        // Metodo que envia el mensaje
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lozagerardo1@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("holamundo@gmail.com"));
            message.setSubject("Prueba de Mailtrap");
            message.setText("Hola pulpo y masi deaaaa");

            Transport.send(message);

            System.out.println("¡Correo enviado con éxito!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

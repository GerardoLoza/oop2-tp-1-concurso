package modelo;

import api.MailTrap;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    @Test
    public void inscribirParticipante() {
        var fechaInicio = LocalDate.of(2025, 1, 1);
        var fechaFin = LocalDate.of(2025, 2, 1);
        var fechaInscripcion = LocalDate.of(2025, 1, 10);

        var almacenamientoFake = new FakeRegistro();
        var notificacionFake = new FakeEnvioDeMail();

        var concurso = new Concurso("1", "Concurso de programación", fechaInicio, fechaFin, almacenamientoFake, notificacionFake);
        var participante = new Participante("1", "Juana Perez", "12345678", "@pepito@gmail.com");
        concurso.nuevaInscripcion(new Inscripcion(concurso, participante, fechaInscripcion));

        assertTrue(concurso.participanteInscripto(participante));
    }

    @Test
    public void inscripcionPrimerDia() {
        var fechaInicio = LocalDate.of(2025, 1, 1);
        var fechaFin = LocalDate.of(2025, 2, 1);
        var fechaInscripcion = LocalDate.of(2025, 1, 1);

        var almacenamientoFake = new FakeRegistro();
        var notificacionFake = new FakeEnvioDeMail();

        var concurso = new Concurso("1", "Concurso de programación", fechaInicio, fechaFin, almacenamientoFake, notificacionFake);
        var participante = new Participante("1", "Juana Perez", "12345678", "pepito@gmail.com");
        var inscripcion = new Inscripcion(concurso, participante, fechaInscripcion);
        concurso.nuevaInscripcion(inscripcion);

        assertEquals(10, inscripcion.getPuntos());
    }

    @Test
    public void inscripcionFueraDeRango() {
        var fechaInicio = LocalDate.of(2025, 1, 1);
        var fechaFin = LocalDate.of(2025, 2, 1);
        var fechaInscripcion = LocalDate.of(2026, 1, 1);

        var almacenamientoFake = new FakeRegistro();
        var notificacionFake = new FakeEnvioDeMail();

        var concurso = new Concurso("1", "Concurso de programación", fechaInicio, fechaFin, almacenamientoFake, notificacionFake);
        var participante = new Participante("1", "Juana Perez", "12345678", "pepito@gmail.com");
        var inscripcion = new Inscripcion(concurso, participante, fechaInscripcion);

        assertThrows(RuntimeException.class, () -> {
            concurso.nuevaInscripcion(inscripcion);
        });
    }

    @Test
    public void registroFake() {
        var almacenamientoFalso = new FakeRegistro();
        var notificacion = new MailTrap();

        var fechaInicio = LocalDate.of(2025, 1, 1);
        var fechaFin = LocalDate.of(2025, 2, 1);
        var fechaInscripcion = LocalDate.of(2025, 1, 1);

        var concurso = new Concurso("1", "Concurso de programación", fechaInicio, fechaFin, almacenamientoFalso, notificacion);
        var participante = new Participante("100", "Tomas Acosta", "45209340", "pepito@gmail.com");
        var inscripcion = new Inscripcion(concurso, participante, fechaInscripcion);
        concurso.nuevaInscripcion(inscripcion);
        assertEquals("2025-01-01,100,1", almacenamientoFalso.obtenerRegistro());
    }

    @Test
    public void envioDeMailFake() {
        var almacenamientoFalso = new FakeRegistro();
        var notificacion = new FakeEnvioDeMail();

        var fechaInicio = LocalDate.of(2025, 1, 1);
        var fechaFin = LocalDate.of(2025, 2, 1);
        var fechaInscripcion = LocalDate.of(2025, 1, 1);

        var concurso = new Concurso("1", "Concurso de programación", fechaInicio, fechaFin, almacenamientoFalso, notificacion);
        var participante = new Participante("100", "Tomas Acosta", "45209340", "pepito@gmail.com");
        var inscripcion = new Inscripcion(concurso, participante, fechaInscripcion);
        concurso.nuevaInscripcion(inscripcion);
        assertEquals("Usted se ha registrado correctamente al concurso", notificacion.obtenerMensajeEnviado());
    }
}

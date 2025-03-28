import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    @Test
    public void inscribirParticipante() {
        var fechaInicio = LocalDate.of(2025, 1, 1);
        var fechaFin = LocalDate.of(2025, 2, 1);
        var concurso = new Concurso("Concurso de programación", fechaInicio, fechaFin);
        var participante = new Participante("Juana Perez", "12345678");
        var fechaInscripcion = LocalDate.of(2025, 2, 1);
        concurso.nuevaInscripcion(new Inscripcion(concurso, participante, fechaInscripcion));
        assertTrue(concurso.participanteInscripto(participante));
    }

    @Test
    public void inscripcionPrimerDia() {
        var fechaInicio = LocalDate.of(2025, 1, 1);
        var fechaFin = LocalDate.of(2025, 2, 1);
        var concurso = new Concurso("Concurso de programación", fechaInicio, fechaFin);
        var participante = new Participante("Juana Perez", "12345678");
        var fechaInscripcion = LocalDate.of(2025, 1, 1);
        var inscripcion = new Inscripcion(concurso, participante, fechaInscripcion);
        concurso.nuevaInscripcion(inscripcion);
        assertEquals(10, inscripcion.getPuntos());
    }

    @Test
    public void inscripcionFueraDeRango() {
        var fechaInicio = LocalDate.of(2025, 1, 1);
        var fechaFin = LocalDate.of(2025, 2, 1);
        var concurso = new Concurso("Concurso de programación", fechaInicio, fechaFin);
        var participante = new Participante("Juana Perez", "12345678");
        var fechaInscripcion = LocalDate.of(2026, 1, 1);
        var inscripcion = new Inscripcion(concurso, participante, fechaInscripcion);
        assertThrows(RuntimeException.class, () -> {
            concurso.nuevaInscripcion(inscripcion);
        });
    }
}

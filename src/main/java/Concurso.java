import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {

    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Inscripcion> inscriptos;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inscriptos = new ArrayList<>();
    }

    public void nuevaInscripcion(Inscripcion inscripcion) {
        if (!inscripcion.getConcurso().equals(this)) {
            throw new IllegalArgumentException("La inscripción no pertenece a este concurso.");
        }
        if (inscripcion.getFechaInscripcion().isBefore(fechaInicio) || inscripcion.getFechaInscripcion().isAfter(fechaFin)) {
            throw new RuntimeException("La fecha de inscripción no está dentro del rango permitido.");
        }
        this.inscriptos.add(inscripcion);
    }

    public boolean participanteInscripto(Participante participante) {
        return inscriptos.stream().anyMatch(inscripcion -> inscripcion.estaInscripto(participante));
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
}

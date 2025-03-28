import java.time.LocalDate;

public class Inscripcion {
    private Concurso concurso;
    private Participante participante;
    private LocalDate fechaInscripcion;
    private int puntos;

    public Inscripcion(Concurso concurso, Participante participante, LocalDate fechaInscripcion) {
        this.concurso = concurso;
        this.participante = participante;
        this.fechaInscripcion = fechaInscripcion;
        cargarPuntos();
    }

    private void cargarPuntos() {
        if (concurso.getFechaInicio().isEqual(fechaInscripcion))
            puntos = 10;
    }

    public int getPuntos() {
        return puntos;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public boolean estaInscripto(Participante participante) {
        return this.participante.equals(participante);
    }
}

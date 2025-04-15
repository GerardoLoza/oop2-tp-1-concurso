package modelo;

import api.NotificacionService;
import persistencia.Almacenamamiento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private String id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Inscripcion> inscriptos;
    private Almacenamamiento almacenamamiento;
    private NotificacionService notificacion;
    private final String MENSAJE = "Usted se ha registrado correctamente al concurso";

    public Concurso(String id, String nombre, LocalDate fechaInicio, LocalDate fechaFin, Almacenamamiento almacenamamiento, NotificacionService notificacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inscriptos = new ArrayList<>();
        this.almacenamamiento = almacenamamiento;
        this.notificacion = notificacion;
    }

    public void nuevaInscripcion(Inscripcion inscripcion) {
        if (!inscripcion.getConcurso().equals(this)) {
            throw new IllegalArgumentException("La inscripción no pertenece a este concurso.");
        }
        if (inscripcion.getFechaInscripcion().isBefore(fechaInicio) || inscripcion.getFechaInscripcion().isAfter(fechaFin)) {
            throw new RuntimeException("La fecha de inscripción no está dentro del rango permitido.");
        }
        inscriptos.add(inscripcion);
        notificacion.enviarNotificacion(MENSAJE, inscripcion.getParticipante().obtenerEmail());
        almacenamamiento.registrar(inscripcion.obtenerRegistro());
    }

    public boolean participanteInscripto(Participante participante) {
        return inscriptos.stream().anyMatch(inscripcion -> inscripcion.estaInscripto(participante));
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

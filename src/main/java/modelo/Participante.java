package modelo;

import java.util.Objects;

public class Participante {
    private String id;
    private String nombre;
    private String dni;
    private String email;

    public Participante(String id, String nombre, String dni, String email) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participante that)) return false;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String obtenerEmail() {
        return email;
    }
}

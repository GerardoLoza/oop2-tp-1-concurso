package modelo;

import persistencia.Almacenamamiento;

public class FakeRegistro implements Almacenamamiento {
    private String contenido;

    @Override
    public void registrar(String registro) {
        this.contenido = registro;
    }

    public String obtenerRegistro() {
        return contenido;
    }
}

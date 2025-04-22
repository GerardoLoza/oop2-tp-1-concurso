package modelo;

import persistencia.Almacenamiento;

public class FakeRegistro implements Almacenamiento {
    private String contenido;

    @Override
    public void registrar(String registro) {
        this.contenido = registro;
    }

    public String obtenerRegistro() {
        return contenido;
    }
}

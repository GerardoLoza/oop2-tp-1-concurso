package persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaDisco implements Almacenamiento {
    private String ruta;

    public PersistenciaDisco(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void registrar(String registro) {
        File archivo = new File(ruta);
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(registro + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error al registrar en disco: " + e.getMessage(), e);
        }
    }
}

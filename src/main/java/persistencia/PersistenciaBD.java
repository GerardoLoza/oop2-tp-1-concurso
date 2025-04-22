package persistencia;

import conexion.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersistenciaBD implements Almacenamiento {
    @Override
    public void registrar(String registro) {
        String sql = "INSERT INTO registro (fecha_inscripcion, id_participante, id_concurso) VALUES (?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            String[] partes = registro.split(",");
            statement.setString(1, partes[0]); //fecha
            statement.setString(2, partes[1]); //id participante
            statement.setString(3, partes[2]); //id concurso

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

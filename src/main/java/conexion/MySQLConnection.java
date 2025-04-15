package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final String URL_DB = "jdbc:mysql://localhost:3306/";
    private static final String DB = "baseobjetos";
    private static final String USER = "root";
    private static final String PASS = "Elbananero123";
    private static Connection conn = null;

    public static void connect() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL_DB + DB, USER, PASS);
                System.out.println("Conexión exitosa a la base de datos " + DB);
            }
        } catch (SQLException sqlEx) {
            System.err.println("No se ha podido conectar a " + URL_DB + DB + ". Error: " + sqlEx.getMessage());
        }
    }

    public static void disconnect() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Conexión cerrada exitosamente.");
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            } finally {
                conn = null;
            }
        }
    }

    public static Connection getConnection() {
        connect();  // Asegura que se haya establecido una conexión si es necesario
        return conn;
    }
}
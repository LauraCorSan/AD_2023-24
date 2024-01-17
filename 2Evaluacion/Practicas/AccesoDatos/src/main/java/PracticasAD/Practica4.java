package PracticasAD;

/**
 *
 * @author laura
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Practica4 {

    private static final String URL = "jdbc:mysql://localhost:3306/bd_alumnos";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String args[]) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();

            // Ejecutar el comando show tables
            String query = "SHOW TABLES";
            ResultSet resultSet = statement.executeQuery(query);

            // Imprimir los nombres de las tablas
            System.out.println("Tablas en la base de datos bd_alumnos:");
            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                System.out.println(tableName);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


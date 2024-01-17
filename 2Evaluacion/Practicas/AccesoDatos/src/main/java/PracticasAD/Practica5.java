
package PracticasAD;

/**
 *
 * @author laura
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Practica5 {

    private static final String URL = "jdbc:mysql://localhost:3306/bd_alumnos";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String args[]) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // Obtener metadatos de la base de datos
            DatabaseMetaData metaData = connection.getMetaData();
            // Obtener el nombre de la base de datos actual
            String dbName = connection.getCatalog();
            ResultSet resultSet = metaData.getTables(dbName, null, null, new String[]{"TABLE"});

            // Imprimir los nombres de las tablas
            System.out.println("Tablas en la base de datos " + dbName + ":");
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName);
            }
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


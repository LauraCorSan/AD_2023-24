
package PracticasAD;

/**
 *
 * @author laura
 */
import java.sql.*;
import java.util.Scanner;

public class Practica6 {

    private static final String URL = "jdbc:mysql://localhost:3306/BD_Alumnos";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});

            System.out.println("Tablas en la base de datos BD_Alumnos:");
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");

                // Filtrar tablas que no pertenecen a la base de datos BD_Alumnos
                if (tableName.startsWith("bd_alumnos")) {
                    System.out.println(tableName);
                }
            }

            int opcion = leerEntero("Selecciona una tabla (1, 2 o 3): ");

            switch (opcion) {
                case 1:
                    mostrarTabla(connection, "alumnos");
                    break;
                case 2:
                    mostrarTabla(connection, "alumnos_asignaturas");
                    break;
                case 3:
                    mostrarTabla(connection, "asignaturas");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(new Scanner(System.in).nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Por favor, ingresa un número válido.");
            return leerEntero(mensaje);
        }
    }

    private static void mostrarTabla(Connection connection, String tabla) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tabla);

            // Obtener información sobre las columnas
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Imprimir los nombres de las columnas
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Imprimir los datos de la tabla
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



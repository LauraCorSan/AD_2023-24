import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class actividad_1_2 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("No ha introducido ningun nombre de fichero");
            return;
        }

        String nombreFichero = args[0];

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            System.out.println("Contenido del fichero " + nombreFichero + ":");
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}

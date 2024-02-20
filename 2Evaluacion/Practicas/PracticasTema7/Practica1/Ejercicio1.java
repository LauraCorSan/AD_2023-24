import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar el comando
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd.exe", "/c", "mysql -u root Bdemp -e \"SELECT * FROM departamentos;\"");
            processBuilder.directory(new File("C:\\xampp\\mysql\\bin")); // Establecer el directorio de trabajo

            // Iniciar el proceso
            Process process = processBuilder.start();

            // Leer la salida del proceso
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("\nComando ejecutado con código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

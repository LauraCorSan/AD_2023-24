import java.io.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar el comando
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd.exe", "/c", "mysql -u root Bdemp -e \"SELECT * FROM departamentos;\"");
            processBuilder.directory(new File("C:\\xampp\\mysql\\bin")); // Establecer el directorio de trabajo

            // Redirigir la salida al archivo departamentos.xml
            File outputFile = new File("departamentos.xml");
            processBuilder.redirectOutput(outputFile);

            // Iniciar el proceso
            Process process = processBuilder.start();

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("\nComando ejecutado con código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

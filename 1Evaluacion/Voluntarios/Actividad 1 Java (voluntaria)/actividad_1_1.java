import java.io.File;

public class actividad_1_1 {
    public static void main(String[] args) {
        File directorioActual = new File(".");
        
        if (directorioActual.exists() && directorioActual.isDirectory()) {
            File[] archivos = directorioActual.listFiles();
            
            System.out.println("Archivos en el directorio actual:");
            for (File unArchivo : archivos) {
                System.out.println(unArchivo.getName());
            }
        } else {
            System.out.println("El directorio actual no existe o no es un directorio válido.");
        }
    }
}

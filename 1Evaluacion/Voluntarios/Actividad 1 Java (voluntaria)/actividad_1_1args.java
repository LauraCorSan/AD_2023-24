import java.io.File;
public class actividad_1_1args {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ListarArchivosDirectorioEspecifico <nombre_del_directorio>");
            return;
        }
        
        String nombreDirectorio = args[0];
        File directorio = new File(nombreDirectorio);
        
        if (directorio.exists() && directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            
            System.out.println("Archivos en el directorio " + nombreDirectorio + ":");
            for (File unArchivo : archivos) {
                System.out.println(unArchivo.getName());
            }
        } else {
            System.out.println("El directorio " + nombreDirectorio + " no existe o no es un directorio válido.");
        }
    }
}

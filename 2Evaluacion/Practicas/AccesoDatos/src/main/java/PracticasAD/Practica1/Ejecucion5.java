/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticasAD.Practica1;


import alumno.FactoriaAlumnos;
import alumno.AlumnoBean;
import alumno.AlumnoInterface;
import java.util.Collection;

/**
 *
 * @author laura
 */
public class Ejecucion5 {
    public static void main(String[] args) {
        // Obtener la implementación de la interfaz AlumnoInterface
        String nombre="Juan";
        AlumnoInterface objeto = FactoriaAlumnos.getAlumnoDao();

        // Obtener la colección de alumnos que se llaman "Juan"
        Collection<AlumnoBean> alumnosJuan = objeto.getAlumnoPorNombre(nombre);

        // Borrar los alumnos encontrados
        for (AlumnoBean alumno : alumnosJuan) {
            if (alumno != null) {
                System.out.println("Borrando alumno con NumExpediente: " + alumno.getNumexpdte()+" llamado "+nombre);
                alumno.delete();
                System.out.println("SUCCESS: Alumno borrado correctamente.");
            }
        }
    }
}

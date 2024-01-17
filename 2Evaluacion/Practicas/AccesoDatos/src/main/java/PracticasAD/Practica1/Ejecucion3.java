/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticasAD.Practica1;

import alumno.AlumnoInterface;
import alumno.FactoriaAlumnos;
import java.util.Collection;

/**
 *
 * @author laura
 */
public class Ejecucion3 {
    public static void main(String[] args) {
        String nombre="Juan";
        // Obtener la implementación de la interfaz AlumnoInterface
        AlumnoInterface alumnoDao = FactoriaAlumnos.getAlumnoDao();

        // Listar todos los alumnos que se llamen "Juan"
        Collection alumnosJuan = alumnoDao.getAlumnoPorNombre(nombre);

        // Mostrar los resultados
        if (alumnosJuan == null || alumnosJuan.isEmpty()) {
            System.out.println("WARNING: No se encontraron alumnos con el nombre "+nombre);
        } else {
            System.out.println("LISTADO ALUMNOS QUE SE LLAMAN "+nombre);
            for (Object alumno : alumnosJuan) {
                if (alumno instanceof AlumnoInterface) {
                    AlumnoInterface alumnoJuan = (AlumnoInterface) alumno;
                    System.out.println("Datos de "+alumnoJuan.getNombre()+":");
                    System.out.println("DNI: " + alumnoJuan.getDni());
                    System.out.println("NumExpdte: " + alumnoJuan.getNumexpdte());
                    System.out.println("Ciclo: " + alumnoJuan.getCiclo());
                    System.out.println("---------------------------");
                }
            }
        }
    }
}

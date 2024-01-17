/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticasAD.Practica1;

import alumno.AlumnoInterface;
import alumno.FactoriaAlumnos;

/**
 *
 * @author laura
 */
public class Ejecucion4 {
    public static void main(String[] args) {
        int numExpdnt=1;
        // Obtener la implementación de la interfaz AlumnoInterface
        AlumnoInterface objeto = FactoriaAlumnos.getAlumnoDao();

        // Obtener el alumno con número de expediente 1
        AlumnoInterface alumno = objeto.getAlumnoPorNUMEXPDTE(Integer.toString(numExpdnt));

        // Mostrar los resultados
        if (alumno == null) {
            System.out.println("WARNING: No se encontró ningún alumno con NumExpediente 1.");
        } else {
            System.out.println("LISTADO ALUMNOS CON NUMEXPDNT :"+Integer.toString(numExpdnt));
            System.out.println("Ciclo: " + alumno.getCiclo());
            System.out.println("DNI: " + alumno.getDni());
            System.out.println("NumExpdnt: " + alumno.getNumexpdte());
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("---------------------------");
        }
    }
}

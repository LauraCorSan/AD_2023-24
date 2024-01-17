/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticasAD.Practica1;

/**
 *
 * @author laura
 */
import alumno.AlumnoInterface;
import alumno.FactoriaAlumnos;
import java.sql.Connection;

public class Ejecucion1 {
    public static Connection connection = null;
    public static void main(String[] args) {
        AlumnoInterface objeto = FactoriaAlumnos.getAlumnoDao();

        //Crear el alumno nuevo
        AlumnoInterface alumnoNuevo = objeto.getNuevoAlumno("105", "ENRIQUETA", "ASIR", "3434343");
        
        //Modificar alumnoNUEVO: Nombre BLANCA, dni 55555555, ciclo DAM.
        alumnoNuevo.setNombre("BLANCA");
        alumnoNuevo.setDni("55555555");
        alumnoNuevo.setCiclo("DAM");
        
    }
}
